import os
import subprocess
import shutil
import tempfile
import yaml
from typing import List, Optional

from keynote2slidev.core.domain import Presentation, Slide
from keynote2slidev.ports.parser import KeynoteParserPort

class KeynoteParserAdapter(KeynoteParserPort):
    def __init__(self):
        pass

    def parse(self, keynote_path: str) -> Presentation:
        if not os.path.exists(keynote_path):
            raise FileNotFoundError(f"Keynote file not found: {keynote_path}")

        temp_dir = tempfile.mkdtemp()
        try:
            self._extract(keynote_path, temp_dir)
            slide_ids = self._parse_document(temp_dir)

            slides = []
            for slide_id in slide_ids:
                texts = self._parse_slide(temp_dir, slide_id)
                if texts is not None:
                    slides.append(Slide(id=slide_id, texts=texts))

            return Presentation(title=os.path.basename(keynote_path), slides=slides)
        finally:
            shutil.rmtree(temp_dir)

    def _extract(self, keynote_path: str, extract_dir: str):
        # We use subprocess because keynote-parser is primarily a CLI tool
        # and has complex internal state / dependencies to setup manually.
        subprocess.run(["keynote-parser", "unpack", keynote_path, "--output", extract_dir], check=True, capture_output=True)

    def _parse_document(self, extract_dir: str) -> List[str]:
        doc_path = os.path.join(extract_dir, 'Index', 'Document.iwa.yaml')
        if not os.path.exists(doc_path):
            raise FileNotFoundError("Could not find Document.iwa.yaml. Is this a valid Keynote file?")

        with open(doc_path, 'r') as f:
            data = yaml.safe_load(f)

        slide_node_ids = []
        def traverse_doc(obj):
            if isinstance(obj, dict):
                for k, v in obj.items():
                    if k == 'slideTree' and isinstance(v, dict) and 'slides' in v:
                        for slide in v['slides']:
                            if 'identifier' in slide:
                                slide_node_ids.append(slide['identifier'])
                    else:
                        traverse_doc(v)
            elif isinstance(obj, list):
                for item in obj:
                    traverse_doc(item)

        traverse_doc(data)

        # Mapping of node identifier -> slide identifier
        mapping = {}
        if isinstance(data, dict) and 'chunks' in data:
            for chunk in data.get('chunks', []):
                for archive in chunk.get('archives', []):
                    header = archive.get('header', {})
                    objects = archive.get('objects', [])
                    node_id = header.get('identifier')

                    for obj in objects:
                        if obj.get('_pbtype') == 'KN.SlideNodeArchive':
                            slide_id = obj.get('slide', {}).get('identifier')
                            if node_id and slide_id:
                                mapping[node_id] = slide_id

        # Return slides in order they appear in the presentation tree
        if slide_node_ids and mapping:
            ordered_slide_ids = [mapping.get(nid) for nid in slide_node_ids if mapping.get(nid)]
            if ordered_slide_ids:
                return ordered_slide_ids

        # Fallback: Just grab all slide identifiers if we couldn't figure out the order
        slide_ids = []
        def traverse_all_slides(obj):
            if isinstance(obj, dict):
                for k, v in obj.items():
                    if k == '_pbtype' and v == 'KN.SlideNodeArchive':
                        slide_id = obj.get('slide', {}).get('identifier')
                        if slide_id:
                            slide_ids.append(slide_id)
                    else:
                        traverse_all_slides(v)
            elif isinstance(obj, list):
                for item in obj:
                    traverse_all_slides(item)

        traverse_all_slides(data)
        return slide_ids

    def _parse_slide(self, extract_dir: str, slide_id: str) -> Optional[List[str]]:
        slide_path = os.path.join(extract_dir, 'Index', f'Slide-{slide_id}.iwa.yaml')
        if not os.path.exists(slide_path):
            return None

        with open(slide_path, 'r') as f:
            data = yaml.safe_load(f)

        texts = []
        def traverse(obj):
            if isinstance(obj, dict):
                for k, v in obj.items():
                    if k == 'text' and isinstance(v, list):
                        texts.extend(v)
                    else:
                        traverse(v)
            elif isinstance(obj, list):
                for item in obj:
                    traverse(item)

        traverse(data)

        # Clean text
        texts = [t for t in texts if t != '\ufffc']
        return texts

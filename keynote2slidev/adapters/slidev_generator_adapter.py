import os

from keynote2slidev.core.domain import Presentation
from keynote2slidev.ports.generator import SlidevGeneratorPort

class SlidevGeneratorAdapter(SlidevGeneratorPort):
    def generate(self, presentation: Presentation, output_dir: str) -> str:
        os.makedirs(output_dir, exist_ok=True)
        output_md = os.path.join(output_dir, 'slides.md')

        with open(output_md, 'w', encoding='utf-8') as f:
            # Write Slidev frontmatter
            f.write("---\n")
            f.write("theme: default\n")
            f.write("class: text-center\n")
            f.write("highlighter: shiki\n")
            f.write("lineNumbers: false\n")
            f.write("info: |\n")
            f.write(f"  Presentation: {presentation.title}\n")
            f.write("drawings:\n")
            f.write("  persist: false\n")
            f.write("transition: slide-left\n")
            f.write(f"title: {presentation.title}\n")
            f.write("---\n\n")

            for i, slide in enumerate(presentation.slides):
                if i > 0:
                    f.write("---\n\n")

                if not slide.texts:
                    f.write("\n")
                    continue

                # First text element becomes H1
                f.write(f"# {slide.texts[0]}\n\n")

                # Remaining elements become bullets
                for text in slide.texts[1:]:
                    f.write(f"- {text}\n")

        return output_md

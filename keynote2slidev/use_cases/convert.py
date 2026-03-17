from keynote2slidev.ports.parser import KeynoteParserPort
from keynote2slidev.ports.generator import SlidevGeneratorPort

class ConvertKeynoteToSlidevUseCase:
    def __init__(self, parser: KeynoteParserPort, generator: SlidevGeneratorPort):
        self.parser = parser
        self.generator = generator

    def execute(self, keynote_path: str, output_dir: str):
        presentation = self.parser.parse(keynote_path)
        output_file = self.generator.generate(presentation, output_dir)
        return output_file

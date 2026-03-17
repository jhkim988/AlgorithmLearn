from abc import ABC, abstractmethod
from keynote2slidev.core.domain import Presentation

class SlidevGeneratorPort(ABC):
    @abstractmethod
    def generate(self, presentation: Presentation, output_dir: str) -> str:
        """Generates a slidev markdown presentation in the output directory."""
        pass

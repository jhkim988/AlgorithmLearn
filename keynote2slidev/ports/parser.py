from abc import ABC, abstractmethod
from typing import Optional
from keynote2slidev.core.domain import Presentation

class KeynoteParserPort(ABC):
    @abstractmethod
    def parse(self, keynote_path: str) -> Presentation:
        """Parses a keynote file into a Presentation object."""
        pass

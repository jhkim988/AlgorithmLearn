from dataclasses import dataclass, field
from typing import List

@dataclass
class Slide:
    id: str
    texts: List[str] = field(default_factory=list)

@dataclass
class Presentation:
    title: str
    slides: List[Slide] = field(default_factory=list)

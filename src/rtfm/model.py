import re
from collections import OrderedDict
from dataclasses import dataclass, field
from pathlib import Path


class Properties:
    """
    Common properties
    """

    CORE_DATE = "core.date"
    CORE_TYPE = "core.type"
    EYED3 = "eyed3"
    EYED3_TAGGED = "eyed3.tagged"
    EYED3_ARTIST = "eyed3.artist"
    EYED3_ALBUM = "eyed3.album"
    EYED3_YEAR = "eyed3.year"
    EYED3_TRACK = "eyed3.track"
    EYED3_TITLE = "eyed3.title"
    EYED3_COMMENT = "eyed3.comment"


@dataclass
class Item:
    path: Path
    root_folder: Path
    properties: dict = field(default_factory=OrderedDict)

    def __str__(self):
        return self.vpath

    @property
    def filtername(self):
        return self.properties.get(Properties.CORE_TYPE)

    @filtername.setter
    def filtername(self, value):
        self.properties[Properties.CORE_TYPE] = value

    @property
    def vpath(self):
        out = None
        if self.root_folder is None:
            out = str(self.path)
        else:
            out = str(self.path.resolve().relative_to(self.root_folder.resolve()))

        if self.path.is_dir():
            out += "/"

        return out


class Filter:
    def __init__(self, name: str, payload: dict):
        self.name = name
        self.__json = payload

    def __str__(self):
        return self.name

    @property
    def pattern(self):
        return self.__json["pattern"]

    @property
    def properties(self):
        return self.__json.get("properties", {})

    def accept(self, item: Item):
        return re.compile(self.pattern).fullmatch(item.vpath)

    def update(self, item: Item):
        item.filtername = self.name
        for name, prop in self.properties.items():
            value = None
            if isinstance(prop, dict):
                if "group" in prop:
                    m = re.compile(prop.get("pattern", self.pattern)).fullmatch(
                        item.vpath
                    )
                    if m is not None:
                        value = m.group(prop["group"])
                if value is None:
                    value = prop.get("default")
            elif isinstance(prop, (str, int, bool)):
                value = prop
            # Default value
            if value is not None:
                item.properties[name] = value

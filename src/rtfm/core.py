import json
import sys
from collections import OrderedDict
from pathlib import Path
from pytput import print_red, tput_print
from rtfm.model import Filter, Item, Properties
from rtfm.tagger import EyeD3ApiTagger


class Library:
    def __init__(self):
        self.items = {}

    def load(self, file: Path):
        with file.open() as fp:
            self.items = json.load(fp, object_pairs_hook=OrderedDict)

    def save(self, file: Path):
        with file.open("w") as fp:
            fp.write(json.dumps(self.items, indent=4, separators=(",", ": ")))


class Configuration:
    def __init__(self):
        self.__json = {}

    def load(self, file: Path):
        with file.open() as fp:
            self.__json = json.load(fp, object_pairs_hook=OrderedDict)

    def save(self, file: Path):
        with file.open("w") as fp:
            fp.write(json.dumps(self.__json, indent=4, separators=(",", ": ")))

    @property
    def filters(self):
        if "filters" not in self.__json:
            self.__json["filters"] = OrderedDict()
        return OrderedDict(
            [
                (name, Filter(name, payload))
                for name, payload in self.__json["filters"].items()
            ]
        )


class RtfmApp:
    def __init__(self, verbose=False):
        self.verbose = verbose
        self.configuration = Configuration()
        self.library = Library()
        self.tagger = EyeD3ApiTagger()

    def __find_filter(self, item: Item):
        for flt in self.configuration.filters.values():
            if flt.accept(item):
                return flt

    def __create_item(self, path: Path, root_folder: Path = None) -> Item:
        out = Item(path, root_folder)
        if out.vpath in self.library.items:
            out.properties.update(self.library.items[out.vpath])
        flt = self.__find_filter(out)
        if flt is not None:
            flt.update(out)
        self.library.items[out.vpath] = out.properties
        return out

    def visit(
        self,
        root_folder: Path,
        *paths: Path,
        recursive=True,
        sort=True,
        process_folders=False
    ):
        for p in paths:
            if process_folders or not p.is_dir():
                yield self.__create_item(p, root_folder=root_folder)
            if recursive and p.is_dir():
                for f in sorted(p.rglob("*"), key=str) if sort else p.rglob("*"):
                    if process_folders or not f.is_dir():
                        yield self.__create_item(f, root_folder=root_folder)

    def dump(self, item: Item, **kwargs):
        tcolor = "green" if item.filtername else "red"
        pcolor = "blue" if item.path.is_dir() else "purple"
        tput_print(
            "[{type:bold," + tcolor + "}] {file:bold," + pcolor + "}",
            type=item.filtername or "unknown",
            file=item,
        )
        if self.verbose:
            for k, v in item.properties.items():
                tput_print("  {k:}: {v:dim}", k=k, v=v)

    def new(self, item: Item, **kwargs):
        if item.properties.get(Properties.EYED3_TAGGED) is not True:
            self.clean(item, **kwargs)
            self.tag(item, **kwargs)

    def tag(self, item: Item, **kwargs):
        self.__tagger_exec(EyeD3ApiTagger.tag, item, **kwargs)

    def clean(self, item: Item, **kwargs):
        self.__tagger_exec(EyeD3ApiTagger.clean, item, **kwargs)

    def check(self, item: Item, **kwargs):
        kwargs["stop"] = False
        self.__tagger_exec(EyeD3ApiTagger.check, item, **kwargs)

    def __tagger_exec(self, fnc: callable, item: Item, dryrun=False, stop=False):
        if not self.tagger.is_supported(item):
            if self.verbose:
                tput_print("[{result:dim}] {file}", result="skipped", file=item)
        else:
            action = fnc.__name__
            if dryrun:
                tput_print(
                    "[{result:green}{action:bold,green}] {file}",
                    result="dryrun:",
                    file=item,
                    action=action,
                )
            else:
                try:
                    fnc(self.tagger, item)
                    tput_print("[{result:bold,green}] {file}", result=action, file=item)
                except KeyboardInterrupt:
                    print_red("Operation cancelled")
                    sys.exit(1)
                except BaseException as e:
                    tput_print(
                        "[{result:bold,red}] {file} ({msg:dim})",
                        result="ERROR",
                        file=item,
                        msg=str(e),
                    )
                    if stop:
                        print_red("Operation cancelled")
                        sys.exit(2)

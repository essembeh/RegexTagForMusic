from argparse import ArgumentParser
from pathlib import Path

from pytput import tput_print
from rtfm.core import RtfmApp

DEFAULT_CONFIG = Path.home() / ".config/rtfm.json"
ACTIONS = {
    "list": RtfmApp.dump,
    "tag": RtfmApp.tag,
    "clean": RtfmApp.clean,
    "check": RtfmApp.check,
    "new": RtfmApp.new,
}


def main(*altargs):
    parser = ArgumentParser(prog="rtfm", description="Simple hello world python sample")
    parser.add_argument("-v", "--verbose", action="store_true", help="be more verbose")
    parser.add_argument(
        "-c",
        "--config",
        type=Path,
        default=DEFAULT_CONFIG,
        help="configuration file (default: {0}".format(DEFAULT_CONFIG),
    )
    parser.add_argument("-l", "--library", type=Path, help="library file")
    parser.add_argument(
        "-r",
        "--root",
        type=Path,
        default=Path.cwd(),
        help="make all paths relative to given folder (default is current folder, $PWD)",
    )
    parser.add_argument("--folders", action="store_true", help="also process folders")
    parser.add_argument(
        "-x",
        "--execute",
        dest="actions",
        action="append",
        metavar="ACTION",
        choices=ACTIONS.keys(),
        help="action to perform",
    )
    parser.add_argument(
        "-n", "--dryrun", action="store_true", help="dryrun mode, do not modify files"
    )
    parser.add_argument(
        "--continue",
        action="store_false",
        dest="stop",
        help="continue in case of error",
    )
    parser.add_argument(
        "input_files", metavar="FILE", type=Path, nargs="*", help="files to process"
    )

    args = parser.parse_args(args=altargs or None)
    root_folder = args.root.resolve()
    files_to_process = args.input_files or [root_folder]
    actions = args.actions or ["list"]
    app = RtfmApp(verbose=args.verbose)
    if args.library and args.library.exists():
        app.library.load(args.library)
    app.configuration.load(args.config)

    if args.verbose:
        tput_print(
            "Execute {actions:bold,yellow} on {files:cyan}",
            actions=", ".join(actions),
            files=", ".join(map(str, files_to_process)),
        )
    for item in app.visit(root_folder, *files_to_process, process_folders=args.folders):
        for action in actions:
            ACTIONS[action](app, item, dryrun=args.dryrun, stop=args.stop)

    if args.library:
        app.library.save(args.library)

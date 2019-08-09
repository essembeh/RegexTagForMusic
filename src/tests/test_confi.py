import subprocess
import unittest
from pathlib import Path
from shutil import copyfile, copytree
from tempfile import TemporaryDirectory

from rtfm.cli import main
from rtfm.core import RtfmApp

SAMPLES_FOLDER = Path(__file__).parent / "samples"


class TestItem(unittest.TestCase):
    def test_seb(self):
        self.__check_config("seb")

    def test_default(self):
        self.__check_config("default")

    def __assert_md5(self, folder, md5file):
        subprocess.run(
            ["md5sum", "-c", str(md5file.resolve())], check=True, cwd=folder.resolve()
        )

    def __check_config(self, config):
        with TemporaryDirectory() as tempdirname:
            config_file = Path(tempdirname) / "config.json"
            root_folder = Path(tempdirname) / "files"
            copyfile("{0}/config/{1}.json".format(SAMPLES_FOLDER, config), config_file)
            copytree(
                "{0}/config/{1}".format(SAMPLES_FOLDER, config),
                "{0}/files".format(tempdirname),
            )
            app = RtfmApp()
            app.configuration.load(config_file)
            md5clean = Path(__file__).parent / "{0}-clean.md5".format(config)
            md5tagged = Path(__file__).parent / "{0}-tagged.md5".format(config)

            def execute(*args):
                main(
                    "--config",
                    str(config_file),
                    "--root",
                    str(root_folder),
                    "--verbose",
                    *args
                )

            execute()
            execute("-x", "clean")
            self.__assert_md5(root_folder, md5clean)

            execute("-x", "check")
            execute("-x", "tag")
            execute("-x", "check")
            self.__assert_md5(root_folder, md5tagged)
            execute("-x", "clean")
            self.__assert_md5(root_folder, md5clean)

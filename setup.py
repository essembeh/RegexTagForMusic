#!/usr/bin/env python3

from setuptools import setup


def readfile(file):
    with open(file) as f:
        return f.read()


setup(
    name="RegexTagForMusic",
    license="GNU General Public License v3 (GPLv3)",
    author="Sébastien MB",
    author_email="seb@essembeh.org",
    description="Simple python3 hello world",
    long_description=readfile("README.md"),
    long_description_content_type="text/markdown",
    url="https://github.com/essembeh/RegexTagForMusic",
    setup_requires=["setuptools_scm"],
    use_scm_version={"version_scheme": "post-release"},
    install_requires=["pytput", "eyed3"],
    package_dir={"": "src"},
    packages=["rtfm"],
    entry_points={"console_scripts": ["rtfm = rtfm.cli:main"]},
    classifiers=[
        "Development Status :: 4 - Beta",
        "Topic :: Utilities",
        "License :: OSI Approved :: GNU General Public License v3 (GPLv3)",
        "Programming Language :: Python :: 3.5",
        "Programming Language :: Python :: 3.6",
        "Programming Language :: Python :: 3.7",
    ],
    project_urls={  # Optional
        "Bug Reports": "https://github.com/essembeh/RegexTagForMusic/issues",
        "Source": "https://github.com/essembeh/RegexTagForMusic",
    },
)

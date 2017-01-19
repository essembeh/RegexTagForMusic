[![Build Status](https://travis-ci.org/essembeh/RegexTagForMusic.svg?branch=master)](https://travis-ci.org/essembeh/RegexTagForMusic)

# Presentation

RegexTagForMusic is a tool to run *dynamically* built commands on files using *regular expressions*. In other words, if the path matches a regex, you can use any matching group or variables to build & run commands.


I originally developed *RegexTagForMusic* to automatize tagging my music using [eyeD3](http://eyed3.nicfit.net/). ID3 tags are some metadata just like the way you store your files on your filesystem so I wanted a way to synchronize tags *from* the filesystem:
* the *album field* is the parent folder of the file
* the *artist field* is the parent folder of the *album* folder
* the *tracknumber field* is the first digit of the filename
* the *title field* is the rest of the filename

# Using

## Dependencies

First you need Java 8
```shell
$ sudo apt-get install openjdk-8-jre
```
If you want to build it you will need *Maven 3*
```shell
$ sudo apt-get install maven
```
Plus if you want to tag your music using the default configuration file, you will need *eyeD3*
```shell
$ sudo apt-get install eyed3
```


## Build

Clone and build the project
```shell
$ git clone https://github.com/essembeh/RegexTagForMusic
$ cd RegexTagForMusic
$ mvn clean install
```
Run the all-in-one jar
```shell
$ java -jar rtfm-app/target/rtfm-app-*-jar-with-dependencies.jar --help
```
Or the shell script
```
$ ./rtfm-app/target/rtfm.sh --help
```
And if you want to install it on your system
```shell
$ sudo mkdir -p /opt/local/RegexTagForMusic
$ sudo cp rtfm-app/target/rtfm-app-*-jar-with-dependencies.jar tfm-app/target/rtfm.sh /opt/local/RegexTagForMusic
$ sudo chmod +x /opt/local/RegexTagForMusic/rtfm.sh
$ sudo ln -s /opt/local/RegexTagForMusic/rtfm.sh /usr/local/bin/rtfm
```

## Test it

You can try to tag the *default* music folder using the *default* configuration file:
```shell
$ ./rtfm-app/target/rtfm.sh --config samples/config/default.json samples/config/default/
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Bob/The Album/03 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Bob/The Album/01 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Bob/The Album/02 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2000 - First Album/03 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2000 - First Album/01 - Track.mp3
[cover] /home/seb/RegexTagForMusic/samples/config/default/Alice/2000 - First Album/cover.jpg
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2000 - First Album/02 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/201 - Track.mp3
[cover] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/cover.jpg
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/102 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/202 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/101 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/103 - Track.mp3
[mp3] /home/seb/RegexTagForMusic/samples/config/default/Alice/2001 - Second Album/203 - Track.mp3

$ eyeD3 /home/seb/RegexTagForMusic/samples/config/default/Alice/2001\ -\ Second\ Album/102\ -\ Track.mp3
102 - Track.mp3 [ 2.34 KB ]
-------------------------------------------------------------------------------
Time: 00:00     MPEG1, Layer III        [ 192 kb/s @ 44100 Hz - Stereo ]
-------------------------------------------------------------------------------
ID3 v2.4:
title: Track            artist: Alice
album: Second Album             year: 2001
track: 102
```
## Options

```
usage: rtfm
	-c,--config <arg>        Custom configuration file
	-e,--env                 Use env to resolve variables
	-f,--folders             Also process folders (NB: folders path will end with /)
	-h,--help                Display help
	-i,--ignore-list <arg>   Use ignore list
	-n,--dry-run             Dry-run mode, do not execute commands
	-v,--verbose             Display more information
```

### Option: *--folders*

If you use the `--folders` option, the full path on which regex will be tested will end with a *trailing slash*. This is a convenient way to differentiate files from folder when you write your regular expressions.

For example, `/test/foo` folder will match `/test/\\w+/` and *not* `/test/\\w+` (Note the trailing `/`)

### Option: *--ignore-list*

This option takes one argument, a file containing all files to be ignored.

The format is simple, it is a text file, one full path per line.
All files contained in this *ignore list*  won't be processed at all (won't appear in logs).

At the end of the execution, all files processed without error will be written in this file, to increment the list of files already done.

# Configuration

The configuration file contains:
* The *types* of file you want to process
* The commands to execute on files which match patterns
* The way variables will be resolved using *named capturing groups*


This is done with a *json* file, see the [configuration file](samples/config/default.json) for default music *layout* (see [*samples*](#samples)).
The application will search for a configuration file:
* using `--config <FILE>` arguments
* using `RTFM_CONFIG` environment variable
* or try to read `~/.config/rtfm.json`


## Section: *types*
```json
{
  "types": {
    "myType": {
      "pattern": ".*/(?<MYGROUP1>\\d+)/(?<MYGROUP2>\\w+)",
      "variables": {
        "myVar1": "Foo",
        "myVar2": "Bar"
      },
      "workflow": [
        "myCommandId1",
        "myCommandId2"
      ]
    }
  }
}
```
This is a map (key/value) where you define all kind of file you want to process.

A *type* contains:
* A *pattern* which will be compiled using Java `java.util.regex.Pattern` API.
* A map of *variables*
* A *workflow*, an array of *command* identifiers defined in *commands* section.

*Nota Bene:* You will have to escape the backslashes like in *Java*. For example, if you want to match a digit using `\d` you have to write `\\d`.


## Section: *commands*
```json
{
  "commands": {
    "myCommandId1": [
      "echo",
      "a static string",
      "another string with named-capturing group ${MYGROUP1}",
      "and now a type variable ${myVar1}"
      "a builtin variable ${path}",
      "another builtin variable ${extension}"
    ]
  }
}
```
This is a map (key/value) where you declare all commands you will run on files.

A command contains:
* An array of strings, defining the command to be executed using the Java `java.lang.ProcessBuilder` API.

You can use variables like `${path}` or `${FOO}`, they will be resolved at runtime.


# Variable resolution

The key feature is the variable resolution.

If you declare a variable `${FOO}` in a command, it will be resolved at runtime as follow:
* Some builtin variables: `${path}`, `${dirname}`, `${basename}`, `${filename}`, `${extension}`
* Search a *named-capturing group* of the *pattern* named *FOO* ([@see Javadoc]( https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html))
* Try to find a variable named *FOO* in the *variables* section of the *type* in the configuration file
* If the argument `--env` is passed, then try to find an environment variable named *FOO* (using `System.getenv(String)` API)

Builtin variables values for file `~/test/foo.mp3`:
* `path` = `/home/seb/test/foo.mp3`
* `dirname` = `/home/seb/test`
* `basename` = `foo.mp3`
* `filename` = `foo`
* `extension` = `mp3`

If a variable cannot be resolved, it will raise an error, the workflow is aborted and the next file is processed.

# Samples

The [default configuration](samples/config/default.json) will be able to tag music stored like:
```shell
.
├── Alice
│   ├── 2000 - First Album
│   │   ├── 01 - Track.mp3
│   │   ├── 02 - Track.mp3
│   │   ├── 03 - Track.mp3
│   │   └── cover.jpg
│   └── 2001 - Second Album
│       ├── 101 - Track.mp3
│       ├── 102 - Track.mp3
│       ├── 103 - Track.mp3
│       ├── 201 - Track.mp3
│       ├── 202 - Track.mp3
│       ├── 203 - Track.mp3
│       └── cover.jpg
└── Bob
    └── The Album
        ├── 01 - Track.mp3
        ├── 02 - Track.mp3
        └── 03 - Track.mp3
```

The [seb configuration](samples/config/seb.json) corresponds to the way I store my music:
```shell
.
├── Albums
│   ├── Alice
│   │   ├── 2000 - First Album
│   │   │   ├── 01 - Track.mp3
│   │   │   ├── 02 - Track.mp3
│   │   │   ├── 03 - Track.mp3
│   │   │   └── cover.jpg
│   │   └── 2001 - Second Album
│   │       ├── 101 - Track.mp3
│   │       ├── 102 - Track.mp3
│   │       ├── 103 - Track.mp3
│   │       ├── 201 - Track.mp3
│   │       ├── 202 - Track.mp3
│   │       ├── 203 - Track.mp3
│   │       └── cover.jpg
│   └── Bob
│       └── The Album
│           ├── 01 - Track.mp3
│           ├── 02 - Track.mp3
│           └── 03 - Track.mp3
├── Saga
│   └── Foo Bar
│       ├── Episode 01.mp3
│       ├── Episode 02.mp3
│       ├── Episode 03.mp3
│       └── Extra Song.mp3
└── Singles
    ├── Harry (feat. Sally) - Just a Song.mp3
    └── John Doe - Single.mp3
```

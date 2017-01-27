[![Build Status](https://travis-ci.org/essembeh/RegexTagForMusic.svg?branch=master)](https://travis-ci.org/essembeh/RegexTagForMusic)

# Presentation

RegexTagForMusic is a tool to run *dynamically* built commands (*workflows*) on files using *regular expressions*. In other words, if the path matches a regex, you can use any matching group or variables to build & run commands.


I originally developed *RegexTagForMusic* to automatize tagging my music using [eyeD3](http://eyed3.nicfit.net/). ID3 tags are some metadata just like the way you store your files on your filesystem so I wanted a way to synchronize tags *from* the filesystem:
- the *album field* is the parent folder of the file
- the *artist field* is the parent folder of the *album* folder
- the *tracknumber field* is the first digit of the filename
- the *title field* is the rest of the filename

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
 -a,--all              Execute all matching workflows
 -c,--config <arg>     Custom configuration file
 -d,--database <arg>   Use database
 -e,--env              Use env to resolve variables
 -f,--folders          Also process folders (NB: folders path will end with /)
 -h,--help             Display help
 -j,--threads <arg>    use n threads
 -n,--dry-run          Dry-run mode, do not execute commands
 -s,--script <arg>     Generate script
 -v,--verbose          Display more information
```

### *--all*

By default, only the first matching workflow is executed.
For example if you have 3 workflows, *A*, *B* and *C*, if your file matches *B* and *C*, only *B* will be executed.
With `--all`, *B* and *C* will be executed.

### *--config*

To force a custom configuration file.

The application will search for a configuration file:
- using `--config <FILE>` arguments
- using `RTFM_CONFIG` environment variable
- or try to read `~/.config/rtfm.json`

### *--database*

This argument is optional, takes one *file* containing all files to be ignored (Gzipped Json file).

The purpose of the database is to skip some workflows if they have already been executed *sucessfully* on some files.
At the end of the execution, all files processed without error will be written in this file, to increment the list of files already done.

### *--env*

To use *environment variables* to resolve variables.

### *--folders*

By default, if you give a folder as argument, it will be it will be browsed recursively but only files will be processed. *Workflows* are not executed on folders.

Using this option, the *files and folders* will be processed.
And there is a trick in case of folders, the full path on which regex will be tested will end with a *trailing slash*. This is a convenient way to differentiate files from folder when you write your regular expressions.

For example, `/test/foo` folder will match `/test/\\w+/` and *not* `/test/\\w+` (Note the trailing `/`)

### *--thread*

You can process *n* files simultaneously. When used, every log line will be prefixed by an identifier.

### *--dry-run*

No command will be executed, variables will be resolved, but nothing will be executed. This can be usefull using `--script`.

### *--script*

Write a script file containing *every command* resolved by the application. This can be usefull to replay executions without running *rtfm* or to review what will be executed before execution.


# Configuration

The configuration file contains:
- The *commands* you want to execute
- The *workflows* which can customize *commands* and execute them on matching files


This is done with a *json* file, see the [configuration file](samples/config/default.json) for default music *layout* (see [*samples*](#samples)).


## Section: *workflows*
```json
{
  "workflows": {
    "myWorkflow": {
      "pattern": ".*/(?<MYGROUP1>\\d+)/(?<MYGROUP2>\\w+)",
      "variables": {
        "myVar1": "Foo",
        "myVar2": "Bar"
      },
      "execute": [
        "myCommandId1",
        "myCommandId2"
      ]
    }
  }
}
```
This is a `Map<String, Workflow>` where you define all kind of file you want to process.

A `Workflow` contains:
- *pattern*: a `String` which will be compiled using Java `java.util.regex.Pattern` API.
- *variables*: a `Map<String, String>` of workflow specific variables (see *variable resolution*).
- *execute*: an `List<String>` of *command identifiers* defined in *commands* section.

*Nota Bene:* You will have to escape the backslashes like in *Java*. For example, if you want to match a digit using `\d` you have to write `\\d`.


## Section: *commands*
```json
{
  "commands": {
    "myCommandId1": [
      "echo",
      "a static string",
      "another string with named-capturing group ${MYGROUP1}",
      "and now a workflow variable ${myVar1}"
      "a builtin variable ${path}",
      "another builtin variable ${extension}"
    ]
  }
}
```
This is a `Map<String, List<String>`  used to declare all commands you will run on files.

A command is just a list of string  to be executed using the Java `java.lang.ProcessBuilder` API.

You can use variables like `${path}` or `${FOO}`, they will be resolved at runtime.


# Variable resolution

The key feature is the variable resolution.

If you declare a variable `${FOO}` in a command, it will be resolved at runtime as follow:
- Search a *named-capturing group* of the *pattern* named *FOO* ([@see Javadoc]( https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html))
- Search in builtin variables: `${path}`, `${dirname}`, `${basename}`, `${filename}`, `${extension}`
- Try to find a variable named *FOO* in the *variables* section of the *workflow* in the configuration file
- If the option `--env` is passed, then try to find an environment variable named *FOO* (using `System.getenv(String)` API)

## Builtin variables
You can use some of builtin variables.

For example, if the file path is `~/test/foo.mp3`, here are the builtin variables:
- `path` = `/home/seb/test/foo.mp3`
- `dirname` = `/home/seb/test`
- `basename` = `foo.mp3`
- `filename` = `foo`
- `extension` = `mp3`

If a variable cannot be resolved, it will raise an error, the workflow is aborted and the next file is processed.

# Samples

The [default configuration](samples/config/default.json) will be able to tag music stored like ([see tree ](samples/config/default/)):
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

The [seb configuration](samples/config/seb.json) corresponds to the way I store my music ([see tree ](samples/config/seb/)):
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

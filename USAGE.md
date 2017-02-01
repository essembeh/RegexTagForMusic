# Usage

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


## *--all*

By default, only the first matching workflow is executed.
For example if you have 3 workflows, *A*, *B* and *C*, if your file matches *B* and *C*, only *B* will be executed.
With `--all`, *B* and *C* will be executed.


## *--config*

To force a custom configuration file.

The application will search for a configuration file:
- using `--config <FILE>` arguments
- using `RTFM_CONFIG` environment variable
- or try to read `~/.config/rtfm.json`


## *--database*

This argument is optional, takes one *file* containing all files to be ignored.

The purpose of the database is to skip some workflows if they have already been executed *sucessfully* on some files.
At the end of the execution, all files processed without error will be written in this file, to increment the list of files already done.

*Nota:* if the filename has `json` extension, format should be plain JSON. Any other extension, format should be Json+Gzip.

## *--env*

To use *environment variables* to resolve variables.


## *--folders*

By default, if you give a folder as argument, it will be it will be browsed recursively but only files will be processed. *Workflows* are not executed on folders.

Using this option, the *files and folders* will be processed.
And there is a trick in case of folders, the full path on which regex will be tested will end with a *trailing slash*. This is a convenient way to differentiate files from folder when you write your regular expressions.

For example, `/test/foo` folder will match `/test/\\w+/` and *not* `/test/\\w+` (Note the trailing `/`)


## *--thread*

You can process *n* files simultaneously. When used, every log line will be prefixed by an identifier.


## *--dry-run*

No command will be executed, variables will be resolved, but nothing will be executed. This can be usefull using `--script`.


## *--script*

Write a script file containing *every command* resolved by the application. This can be usefull to replay executions without running *rtfm* or to review what will be executed before execution.

#!/bin/bash

# The Tag DATA are in environment variables
# RTFM_ARTIST
# RTFM_ALBUM
# RTFM_YEAR
# RTFM_TRACKNAME
# RTFM_TRACKNUMBER
# RTFM_COMMENT

EYED3_BIN=/usr/bin/eyeD3
EYED3_ARG=--no-color

__doRemove () {
	FILE="$1"
	ARG="$2"
	$EYED3_BIN $EYED3_ARG $ARG "$FILE" > /dev/null 2>&1
}

## Remove tags
__removeTags () {
	FILE="$1"
	__doRemove "$FILE" --remove-v1 
	__doRemove "$FILE" --remove-v2 
	__doRemove "$FILE" --remove-all 
	__doRemove "$FILE" --remove-images 
	__doRemove "$FILE" --remove-lyrics 
	__doRemove "$FILE" --remove-comments 
}

## Main
test -x "$EYED3_BIN" || exit 1
for FILE in "$@"; do
	echo "Removing tags from file: $FILE"
	__removeTags "$FILE" 
done
exit 0

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

## Remove tags
__removeTags () {
	FILE="$1"
	$EYED3_BIN $EYED3_ARG --remove-v1 "$FILE"
	$EYED3_BIN $EYED3_ARG --remove-v2 "$FILE"
	$EYED3_BIN $EYED3_ARG --remove-all "$FILE"
	$EYED3_BIN $EYED3_ARG --remove-images "$FILE"
	$EYED3_BIN $EYED3_ARG --remove-lyrics "$FILE"
	$EYED3_BIN $EYED3_ARG --remove-comments "$FILE"
}

## Main
test -x "$EYED3_BIN" || exit 1
for FILE in "$@"; do
	echo "Removing tags from file: $FILE"
	__removeTags "$FILE" 
done
echo "End of script"

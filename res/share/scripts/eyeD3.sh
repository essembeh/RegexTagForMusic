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
	$EYED3_BIN $EYED3_ARG \
		--remove-v1 \
		--remove-v2 \
		--remove-all \
		--remove-images \
		--remove-lyrics \
		--remove-comments \
		"$FILE"
}

## Tag 
__tag () {
	FILE="$1"
	$EYED3_BIN $EYED3_ARG \
		-v2 \
		--artist  "$RTFM_ARTIST" \
		--album   "$RTFM_ALBUM" \
		--year    "$RTFM_YEAR" \
		--track   "$RTFM_TRACKNUMBER" \
		--title   "$RTFM_TRACKNAME" \
		--comment "::$RTFM_COMMENT" \
		"$FILE"
}

## Version ID3v2.4
__force24 () {
	$EYED3_BIN $EYED3_ARG \
		--to-v2.4 \
		"$FILE"
}

## Version ID3v2.3
__force23 () {
	$EYED3_BIN $EYED3_ARG \
		--to-v2.3 \
		"$FILE"
}

## UTF8
__forceUTF8 () {
	FILE="$1"
	$EYED3_BIN $EYED3_ARG \
		--set-encoding=utf8 \
		--force-update \
		"$FILE"
}

## Main
FILE="$1"
test -x "$EYED3_BIN" || exit 1
test -f "$FILE" || exit 2
__removeTags "$FILE" 
__tag "$FILE" || exit 4
[ "$TAG_VERSION" = "ID3V2_4" ] && (__force24 "$FILE" || exit 5)
[ "$TAG_VERSION" = "ID3V2_3" ] && (__force23 "$FILE" || exit 6)
[ "$TAG_VERSION" = "ID3V2_4" -a "$TAG_FORCEUTF8" = "true" ] && (__forceUTF8 "$FILE" || exit 7)
echo "End of script"

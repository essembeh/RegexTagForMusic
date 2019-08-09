import eyed3.core
from eyed3.id3 import ID3_V2_4, Tag
from rtfm.model import Item, Properties


class EyeD3ApiTagger:
    def is_supported(self, item: Item):
        return item.properties.get(Properties.EYED3) is True

    def __check_supported(self, item: Item):
        if not self.is_supported(item):
            raise ValueError("EyeD3 API is not enabled for {0}".format(item))

    def clean(self, item: Item):
        self.__check_supported(item)
        Tag.remove(str(item.path))
        item.properties[Properties.EYED3_TAGGED] = False

    def tag(self, item: Item):
        self.__check_supported(item)
        t = Tag()
        if Properties.EYED3_ARTIST in item.properties:
            t.artist = item.properties[Properties.EYED3_ARTIST]
        if Properties.EYED3_ALBUM in item.properties:
            t.album = item.properties[Properties.EYED3_ALBUM]
        if Properties.EYED3_YEAR in item.properties:
            t.release_date = int(item.properties[Properties.EYED3_YEAR])
        if Properties.EYED3_TRACK in item.properties:
            t.track_num = int(item.properties[Properties.EYED3_TRACK])
        if Properties.EYED3_TITLE in item.properties:
            t.title = item.properties[Properties.EYED3_TITLE]
        if Properties.EYED3_COMMENT in item.properties:
            t.comments.set(item.properties[Properties.EYED3_COMMENT])
        t.save(str(item.path), version=ID3_V2_4)
        item.properties[Properties.EYED3_TAGGED] = True

    def check(self, item: Item):
        self.__check_supported(item)

        af = eyed3.core.load(str(item.path))
        if af is None:
            raise ValueError("Not a valid mp3 file")
        if af.tag is None:
            raise ValueError("No tag found")

        def throw(k):
            raise ValueError("Need to update {0}".format(k))

        if (
            Properties.EYED3_ARTIST in item.properties
            and af.tag.artist != item.properties[Properties.EYED3_ARTIST]
        ):
            throw(Properties.EYED3_ARTIST)

        if (
            Properties.EYED3_ALBUM in item.properties
            and af.tag.album != item.properties[Properties.EYED3_ALBUM]
        ):
            throw(Properties.EYED3_ALBUM)

        if Properties.EYED3_YEAR in item.properties and (
            af.tag.release_date is None
            or af.tag.release_date.year != int(item.properties[Properties.EYED3_YEAR])
        ):
            throw(Properties.EYED3_YEAR)

        if Properties.EYED3_TRACK in item.properties and af.tag.track_num[0] != int(
            item.properties[Properties.EYED3_TRACK]
        ):
            throw(Properties.EYED3_TRACK)

        if (
            Properties.EYED3_TITLE in item.properties
            and af.tag.title != item.properties[Properties.EYED3_TITLE]
        ):
            throw(Properties.EYED3_TITLE)

        if (
            Properties.EYED3_COMMENT in item.properties
            and af.tag.comments.get("").text
            != item.properties[Properties.EYED3_COMMENT]
        ):
            throw(Properties.EYED3_COMMENT)

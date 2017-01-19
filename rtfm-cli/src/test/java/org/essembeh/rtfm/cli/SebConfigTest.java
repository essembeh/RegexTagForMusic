package org.essembeh.rtfm.cli;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.config.Handler;
import org.essembeh.rtfm.cli.resolver.VariableResolver;
import org.essembeh.rtfm.cli.utils.ConfigurationLoader;
import org.essembeh.rtfm.cli.utils.ResourceUtils;
import org.junit.Assert;
import org.junit.Test;

public class SebConfigTest {
	public static final String ARTIST = "Alice";
	public static final String ALBUM = "First Album";
	public static final String TITLE = "My Track";
	public static final String TRACK = "12";
	public static final String YEAR = "1998";

	public static final String PATH_ALBUM = String.format("/foo/bar/Albums/%s/%s - %s/%s - %s.mp3", ARTIST, YEAR, ALBUM, TRACK, TITLE);
	public static final String PATH_ALBUM_NOYEAR = String.format("/foo/bar/Albums/%s/%s/%s - %s.mp3", ARTIST, ALBUM, TRACK, TITLE);
	public static final String PATH_SINGLE = String.format("/foo/bar/Singles/%s - %s.mp3", ARTIST, TITLE);

	protected Configuration getConfig() throws IOException {
		return ConfigurationLoader.loadConfiguration(Paths.get("../samples/config/seb.json"));
	}

	@Test
	public void testAlbum() throws Exception {
		Handler handler = getConfig().getTypes().get("album");
		Assert.assertNotNull(handler);

		Path path = Paths.get(PATH_ALBUM);
		Matcher matcher = handler.matcher(ResourceUtils.getFullPath(path));
		Assert.assertTrue(matcher.matches());

		VariableResolver resolver = new VariableResolver(path, matcher, handler.getVariables(), false);
		Assert.assertEquals(ARTIST, resolver.lookup("ARTIST"));
		Assert.assertEquals(YEAR + " - " + ALBUM, resolver.lookup("ALBUM"));
		Assert.assertEquals(TITLE, resolver.lookup("TITLE"));
		Assert.assertEquals(TRACK, resolver.lookup("TRACK"));
		Assert.assertEquals(YEAR, resolver.lookup("YEAR"));
	}

	@Test
	public void testAlbumNoYear() throws Exception {
		Handler handler = getConfig().getTypes().get("album");
		Assert.assertNotNull(handler);

		Path path = Paths.get(PATH_ALBUM_NOYEAR);
		Matcher matcher = handler.matcher(ResourceUtils.getFullPath(path));
		Assert.assertTrue(matcher.matches());

		VariableResolver resolver = new VariableResolver(path, matcher, handler.getVariables(), false);
		Assert.assertEquals(ARTIST, resolver.lookup("ARTIST"));
		Assert.assertEquals(ALBUM, resolver.lookup("ALBUM"));
		Assert.assertEquals(TITLE, resolver.lookup("TITLE"));
		Assert.assertEquals(TRACK, resolver.lookup("TRACK"));
		Assert.assertEquals("", resolver.lookup("YEAR"));
	}

	@Test
	public void testSingle() throws Exception {
		Handler handler = getConfig().getTypes().get("single");
		Assert.assertNotNull(handler);

		Path path = Paths.get(PATH_SINGLE);
		Matcher matcher = handler.matcher(ResourceUtils.getFullPath(path));
		Assert.assertTrue(matcher.matches());

		VariableResolver resolver = new VariableResolver(path, matcher, handler.getVariables(), false);
		Assert.assertEquals(ARTIST, resolver.lookup("ARTIST"));
		Assert.assertEquals("#Single", resolver.lookup("ALBUM"));
		Assert.assertEquals(TITLE, resolver.lookup("TITLE"));
		Assert.assertEquals("", resolver.lookup("TRACK"));
		Assert.assertEquals("", resolver.lookup("YEAR"));
	}
}

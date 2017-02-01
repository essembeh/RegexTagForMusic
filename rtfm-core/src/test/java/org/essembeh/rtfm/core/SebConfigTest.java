package org.essembeh.rtfm.core;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

import org.essembeh.rtfm.core.config.Configuration;
import org.essembeh.rtfm.core.config.Workflow;
import org.essembeh.rtfm.core.resolver.VariableResolver;
import org.essembeh.rtfm.core.utils.ResourceUtils;
import org.essembeh.rtfm.core.utils.TestConstants;
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
		return Configuration.load(TestConstants.SEB_CONFIG);
	}

	@Test
	public void testAlbum() throws Exception {
		Workflow workflow = getConfig().getWorkflows().get("album");
		Assert.assertNotNull(workflow);

		Path path = Paths.get(PATH_ALBUM);
		Matcher matcher = workflow.matcher(ResourceUtils.getFullPath(path));
		Assert.assertTrue(matcher.matches());

		VariableResolver resolver = new VariableResolver(path, matcher, workflow.getVariables(), false);
		Assert.assertEquals(ARTIST, resolver.lookup("ARTIST"));
		Assert.assertEquals(YEAR + " - " + ALBUM, resolver.lookup("ALBUM"));
		Assert.assertEquals(TITLE, resolver.lookup("TITLE"));
		Assert.assertEquals(TRACK, resolver.lookup("TRACK"));
		Assert.assertEquals(YEAR, resolver.lookup("YEAR"));
	}

	@Test
	public void testAlbumNoYear() throws Exception {
		Workflow workflow = getConfig().getWorkflows().get("album");
		Assert.assertNotNull(workflow);

		Path path = Paths.get(PATH_ALBUM_NOYEAR);
		Matcher matcher = workflow.matcher(ResourceUtils.getFullPath(path));
		Assert.assertTrue(matcher.matches());

		VariableResolver resolver = new VariableResolver(path, matcher, workflow.getVariables(), false);
		Assert.assertEquals(ARTIST, resolver.lookup("ARTIST"));
		Assert.assertEquals(ALBUM, resolver.lookup("ALBUM"));
		Assert.assertEquals(TITLE, resolver.lookup("TITLE"));
		Assert.assertEquals(TRACK, resolver.lookup("TRACK"));
		Assert.assertEquals("", resolver.lookup("YEAR"));
	}

	@Test
	public void testSingle() throws Exception {
		Workflow workflow = getConfig().getWorkflows().get("single");
		Assert.assertNotNull(workflow);

		Path path = Paths.get(PATH_SINGLE);
		Matcher matcher = workflow.matcher(ResourceUtils.getFullPath(path));
		Assert.assertTrue(matcher.matches());

		VariableResolver resolver = new VariableResolver(path, matcher, workflow.getVariables(), false);
		Assert.assertEquals(ARTIST, resolver.lookup("ARTIST"));
		Assert.assertEquals("#Single", resolver.lookup("ALBUM"));
		Assert.assertEquals(TITLE, resolver.lookup("TITLE"));
		Assert.assertEquals("", resolver.lookup("TRACK"));
		Assert.assertEquals("", resolver.lookup("YEAR"));
	}
}

package org.essembeh.rtfm.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.essembeh.rtfm.cli.resolver.VariableResolver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VariableResolverTest {

	private static final String FULLPATH = "/foo/bar/Album/Alice/2013 - First Album/12 - My Track.mp3";
	private static final String PATTERN = "(?<FILE>.*/(?<ARTIST>[^/]+)/(?:(?<YEAR>[12]\\d{3}) - )?(?<ALBUM>[^/]+)/(?<TRACK>\\d{2,3}) - (?<TITLE>[^/]+)\\.mp3)";
	private static Matcher matcher;

	@BeforeClass
	public static void init() {
		Pattern p = Pattern.compile(PATTERN);
		matcher = p.matcher(FULLPATH);
		Assert.assertTrue(matcher.matches());
	}

	@Test
	public void testMatcher() throws Exception {
		VariableResolver resolver = new VariableResolver(matcher, new HashMap<>(), false);
		Assert.assertEquals(FULLPATH, resolver.lookup("FILE"));
		Assert.assertEquals("Alice", resolver.lookup("ARTIST"));
		Assert.assertEquals("2013", resolver.lookup("YEAR"));
		Assert.assertEquals("First Album", resolver.lookup("ALBUM"));
		Assert.assertEquals("12", resolver.lookup("TRACK"));
		Assert.assertEquals("My Track", resolver.lookup("TITLE"));
		JunitUtils.assertException(() -> resolver.lookup("FOO"), IllegalStateException.class);
	}

	@Test
	public void testExtraVariables() throws Exception {
		Map<String, String> extra = new HashMap<>();
		JunitUtils.assertException(() -> new VariableResolver(matcher, extra, false).lookup("FOO"), IllegalStateException.class);
		extra.put("FOO", "BAR");
		Assert.assertEquals("BAR", new VariableResolver(matcher, extra, false).lookup("FOO"));
	}

	@Test
	public void testEnv() throws Exception {
		Assert.assertNotNull(System.getenv("HOME"));
		Map<String, String> extra = new HashMap<>();
		JunitUtils.assertException(() -> new VariableResolver(matcher, extra, false).lookup("HOME"), IllegalStateException.class);
		Assert.assertEquals(System.getenv("HOME"), new VariableResolver(matcher, extra, true).lookup("HOME"));
	}
}

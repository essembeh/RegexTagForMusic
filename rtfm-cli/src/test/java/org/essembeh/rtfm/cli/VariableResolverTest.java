package org.essembeh.rtfm.cli;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.essembeh.rtfm.cli.resolver.BuiltinVariables;
import org.essembeh.rtfm.cli.resolver.VariableResolver;
import org.essembeh.rtfm.cli.utils.ResourceUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VariableResolverTest {

	private static final String FULLPATH = "/foo/bar/Albums/Alice/2013 - First Album/12 - My Track.mp3";
	private static final Path FILE = Paths.get(FULLPATH);
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
		VariableResolver resolver = new VariableResolver(FILE, matcher, new HashMap<>(), false);
		Assert.assertEquals("Alice", resolver.lookup("ARTIST"));
		Assert.assertEquals("2013", resolver.lookup("YEAR"));
		Assert.assertEquals("First Album", resolver.lookup("ALBUM"));
		Assert.assertEquals("12", resolver.lookup("TRACK"));
		Assert.assertEquals("My Track", resolver.lookup("TITLE"));
		Assert.assertNull(resolver.lookup("FOO"));
	}

	@Test
	public void testExtraVariables() throws Exception {
		Map<String, String> extra = new HashMap<>();
		Assert.assertNull(new VariableResolver(FILE, matcher, extra, false).lookup("FOO"));
		extra.put("FOO", "BAR");
		Assert.assertEquals("BAR", new VariableResolver(FILE, matcher, extra, false).lookup("FOO"));
	}

	@Test
	public void testEnv() throws Exception {
		Assert.assertNotNull(System.getenv("HOME"));
		Map<String, String> extra = new HashMap<>();
		Assert.assertNull(new VariableResolver(FILE, matcher, extra, false).lookup("HOME"));
		Assert.assertEquals(System.getenv("HOME"), new VariableResolver(FILE, matcher, extra, true).lookup("HOME"));
	}

	@Test
	public void testBuiltin() throws Exception {
		VariableResolver resolver = new VariableResolver(FILE, matcher, new HashMap<String, String>(), false);
		Assert.assertEquals(ResourceUtils.getFullPath(FILE), resolver.lookup(BuiltinVariables.PATH.getVarName()));
		Assert.assertEquals("/foo/bar/Albums/Alice/2013 - First Album", resolver.lookup(BuiltinVariables.DIRNAME.getVarName()));
		Assert.assertEquals("12 - My Track.mp3", resolver.lookup(BuiltinVariables.BASENAME.getVarName()));
		Assert.assertEquals("12 - My Track", resolver.lookup(BuiltinVariables.FILENAME.getVarName()));
		Assert.assertEquals("mp3", resolver.lookup(BuiltinVariables.EXTENSION.getVarName()));
	}
}

package org.essembeh.rtfm.junit;

import java.io.File;
import java.util.regex.Pattern;

import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttributeTest {

	VirtualFile file;

	@Before
	public void init() {
		File a = new File("/rootfolder/");
		File b = new File("/rootfolder/Artist/Album/01 - Track.mp3");
		file = new VirtualFile(b, a);
	}

	@Test
	public void testSimple01() {
		String name = "foo";
		String value = "bar";
		boolean hidden = false;
		Attribute a = new Attribute(name, value, hidden);
		Assert.assertEquals(name, a.getName());
		Assert.assertEquals(value, a.getValue());
		Assert.assertEquals(hidden, a.isHidden());
	}

	@Test
	public void testSimple02() {
		String name = "foo";
		String value = "bar";
		boolean hidden = true;
		Attribute a = new Attribute(name, value, hidden);
		Assert.assertEquals(name, a.getName());
		Assert.assertEquals(value, a.getValue());
		Assert.assertEquals(hidden, a.isHidden());
	}

	@Test
	public void testRegex01() throws DynamicAttributeException {
		String name = "foo";
		boolean hidden = true;
		Pattern pattern = Pattern.compile("(.*)");
		int group = 1;
		boolean optional = true;
		IDynamicAttribute dynamicAttribute = new RegexAttribute(name, hidden, pattern, group, optional);
		Attribute a = dynamicAttribute.createAttribute(file);
		Assert.assertNotNull(a);
		Assert.assertEquals(name, a.getName());
		Assert.assertEquals(file.getVirtualPath(), a.getValue());
		Assert.assertEquals(hidden, a.isHidden());
	}

	@Test
	public void testRegex02() throws DynamicAttributeException {
		String name = "foo";
		boolean hidden = true;
		Pattern pattern = Pattern.compile("(.*)(...)");
		int group = 2;
		boolean optional = true;
		IDynamicAttribute dynamicAttribute = new RegexAttribute(name, hidden, pattern, group, optional);
		Attribute a = dynamicAttribute.createAttribute(file);
		Assert.assertNotNull(a);
		Assert.assertEquals(name, a.getName());
		Assert.assertEquals("mp3", a.getValue());
		Assert.assertEquals(hidden, a.isHidden());
	}

	@Test
	public void testRegexNotFound01() {
		String name = "foo";
		boolean hidden = true;
		Pattern pattern = Pattern.compile("(foobar)");
		int group = 1;
		boolean optional = false;
		IDynamicAttribute dynamicAttribute = new RegexAttribute(name, hidden, pattern, group, optional);
		Attribute a = null;
		try {
			a = dynamicAttribute.createAttribute(file);
			Assert.fail();
		} catch (DynamicAttributeException e) {
		}
		Assert.assertNull(a);
	}

	@Test
	public void testRegexNotFound02() throws DynamicAttributeException {
		String name = "foo";
		boolean hidden = true;
		Pattern pattern = Pattern.compile("foobar(....)");
		int group = 1;
		boolean optional = true;
		IDynamicAttribute dynamicAttribute = new RegexAttribute(name, hidden, pattern, group, optional);
		Attribute a = dynamicAttribute.createAttribute(file);
		Assert.assertNull(a);
	}
}

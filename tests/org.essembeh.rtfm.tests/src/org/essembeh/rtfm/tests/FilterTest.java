package org.essembeh.rtfm.tests;

import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.filter.INodeFilter;
import org.essembeh.rtfm.model.filter.NodeFilterFactory;
import org.essembeh.rtfm.model.rtfm.Node;
import org.junit.Assert;
import org.junit.Test;

public class FilterTest {

	private static final String ATTR1 = "test.attr1";
	private static final String ATTR2 = "test.attr2";

	@Test
	public void testExist() throws Exception {
		String line = ATTR1;
		INodeFilter f = NodeFilterFactory.fromString(line);

		Assert.assertNotNull(f);
		Assert.assertEquals(line, f.toString());

		Node a = RtfmCustomFactory.createNode("a", true, true);
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR2, "bar");
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertTrue(f.test(a));

		a.getAttributes().remove(ATTR1);
		Assert.assertFalse(f.test(a));
	}

	@Test
	public void testDoesntExist() throws Exception {
		String line = "!" + ATTR1;
		INodeFilter f = NodeFilterFactory.fromString(line);

		Assert.assertNotNull(f);
		Assert.assertEquals(line, f.toString());

		Node a = RtfmCustomFactory.createNode("a", true, true);
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR2, "bar");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertFalse(f.test(a));

		a.getAttributes().remove(ATTR1);
		Assert.assertTrue(f.test(a));
	}

	@Test
	public void testEquals() throws Exception {
		String line = ATTR1 + "==bar";
		INodeFilter f = NodeFilterFactory.fromString(line);

		Assert.assertNotNull(f);
		Assert.assertEquals(line, f.toString());

		Node a = RtfmCustomFactory.createNode("a", true, true);
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "BaR");
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "BaR0");
		Assert.assertFalse(f.test(a));
	}

	@Test
	public void testEqualsNot() throws Exception {
		String line = ATTR1 + "!==bar";
		INodeFilter f = NodeFilterFactory.fromString(line);

		Assert.assertNotNull(f);
		Assert.assertEquals(line, f.toString());

		Node a = RtfmCustomFactory.createNode("a", true, true);
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "BaR");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "BaR0");
		Assert.assertTrue(f.test(a));

		a.getAttributes().remove(ATTR1);
		Assert.assertFalse(f.test(a));
	}

	@Test
	public void testEqualsIgnoreCase() throws Exception {
		String line = ATTR1 + "=bar";
		INodeFilter f = NodeFilterFactory.fromString(line);

		Assert.assertNotNull(f);
		Assert.assertEquals(line, f.toString());

		Node a = RtfmCustomFactory.createNode("a", true, true);
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "BaR");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "bar");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "BaR0");
		Assert.assertFalse(f.test(a));
	}

	@Test
	public void testMatch() throws Exception {
		String line = ATTR1 + "~^bar[0-1]";
		INodeFilter f = NodeFilterFactory.fromString(line);

		Assert.assertNotNull(f);
		Assert.assertEquals(line, f.toString());

		Node a = RtfmCustomFactory.createNode("a", true, true);
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar0");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, "bar1");
		Assert.assertTrue(f.test(a));

		a.getAttributes().put(ATTR1, " bar0");
		Assert.assertFalse(f.test(a));

		a.getAttributes().put(ATTR1, "bar0 ");
		Assert.assertFalse(f.test(a));
	}

}

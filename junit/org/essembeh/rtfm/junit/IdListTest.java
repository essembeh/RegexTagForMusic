package org.essembeh.rtfm.junit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.junit.Test;

public class IdListTest {

	@Test
	public void testConstructor01() {
		IdList<Integer, Identifier<Integer>> list = new IdList<Integer, Identifier<Integer>>(new Identifier<Integer>() {
			@Override
			public String getId(Integer o) {
				return o.toString();
			}
		});
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		list.add(1);
		Assert.assertEquals(1, list.size());
		list.add(1);
		Assert.assertEquals(1, list.size());
		list.add(2);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testConstructor02() {
		Integer[] init = new Integer[] { 1, 2, 3, 4, 5, 2 };
		IdList<Integer, Identifier<Integer>> list = new IdList<Integer, Identifier<Integer>>(new Identifier<Integer>() {
			@Override
			public String getId(Integer o) {
				return o.toString();
			}
		}, init);
		Assert.assertNotNull(list);
		Assert.assertEquals(init.length - 1, list.size());
	}

	@Test
	public void testConstructor03() {
		List<Integer> init = new ArrayList<Integer>();
		init.add(1);
		init.add(2);
		init.add(3);
		init.add(4);
		init.add(5);
		init.add(2);
		IdList<Integer, Identifier<Integer>> list = new IdList<Integer, Identifier<Integer>>(new Identifier<Integer>() {
			@Override
			public String getId(Integer o) {
				return o.toString();
			}
		}, init);
		Assert.assertNotNull(list);
		Assert.assertEquals(init.size() - 1, list.size());
	}

	@Test
	public void testIterator() {
		IdList<Integer, Identifier<Integer>> list = new IdList<Integer, Identifier<Integer>>(new Identifier<Integer>() {
			@Override
			public String getId(Integer o) {
				return o.toString();
			}
		}, new Integer[] { 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6 });

		Integer previous = null;
		int count = 0;
		for (Integer integer : list) {
			count++;
			Assert.assertNotNull(integer);
			if (previous != null) {
				Assert.assertTrue(previous < integer);
			}
			previous = integer;
		}
		Assert.assertEquals(list.size(), count);
	}

	@Test
	public void testToList() {
		IdList<Integer, Identifier<Integer>> list = new IdList<Integer, Identifier<Integer>>(new Identifier<Integer>() {
			@Override
			public String getId(Integer o) {
				return o.toString();
			}
		}, new Integer[] { 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6 });

		List<Integer> list2 = list.toList();
		Assert.assertNotNull(list2);
		Assert.assertEquals(list.size(), list2.size());
	}

}

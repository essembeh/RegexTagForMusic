package org.essembeh.rtfm.junit;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.essembeh.rtfm.junit.utils.BinaryUtils;
import org.junit.Test;

public class BasicTest {

	@Test
	public void testBinarySearch() throws Exception {
		String buffer = "azertyuiopqsdfghjklmwxcvbn";
		Map<String, Boolean> valuesToTest = new HashMap<String, Boolean>();
		valuesToTest.put("a", true);
		valuesToTest.put("z", true);
		valuesToTest.put("az", true);
		valuesToTest.put("ze", true);
		valuesToTest.put("n", true);
		valuesToTest.put("bn", true);
		valuesToTest.put("bna", false);
		valuesToTest.put("0", false);
		valuesToTest.put("a0", false);
		valuesToTest.put("12", false);
		valuesToTest.put("12a", false);
		for (String motif : valuesToTest.keySet()) {
			Boolean res = valuesToTest.get(motif);
			assertEquals(BinaryUtils.findSequenceInArray(buffer.getBytes(), motif.getBytes()), res);
			System.out.println("Searched motif: " + motif);
		}
	}
}

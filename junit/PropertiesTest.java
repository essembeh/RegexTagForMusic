import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.properties.RTFMPropertiesFromFile;
import org.junit.Test;

public class PropertiesTest {

	@Test
	public void testProperties() throws Throwable {
		RTFMProperties properties = new RTFMPropertiesFromFile("rtfm.properties");
		assertNotNull(properties);
		assertNotNull(properties.getProperty("app.name"));
		assertNotNull(properties.getMandatoryProperty("app.name"));
	}

	@Test
	public void testMandatory() throws Throwable {
		RTFMProperties properties = new RTFMPropertiesFromFile("rtfm.properties");
		assertNotNull(properties);
		assertNull(properties.getProperty("foo.bar"));
		try {
			properties.getMandatoryProperty("foo.bar");
			fail();
		} catch (Exception e) {
			//
		}
	}

	@Test
	public void testUpdate() throws Throwable {
		RTFMProperties properties = new RTFMPropertiesFromFile("rtfm.properties");
		assertNotNull(properties);
		properties.updateProperty("plop", "plop");
		assertEquals(properties.getMandatoryProperty("plop"), "plop");
		properties.updateProperty("plop", "plopy");
		assertEquals(properties.getMandatoryProperty("plop"), "plopy");
	}
}

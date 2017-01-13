package org.essembeh.rtfm.tests;

import java.io.File;

import org.essembeh.rtfm.core.utils.RtfmModelIO;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.FixedAttributeFactory;
import org.essembeh.rtfm.model.rtfm.RtfmFactory;
import org.junit.Assert;
import org.junit.Test;

public class RtfmModelIOTest {

	@Test
	public void testConfigurationIO() throws Exception {
		FixedAttributeFactory fixedAttributeFactory = RtfmCustomFactory.createFixedAttributeFactory("test.foo", "bar");

		FileHandler fileHandler = RtfmCustomFactory.createFileHandler("test", ".*");
		fileHandler.getFactories().add(fixedAttributeFactory);

		Configuration configuration = RtfmFactory.eINSTANCE.createConfiguration();
		configuration.getFileHandlers().add(fileHandler);

		File tmpFile = File.createTempFile("rtfm-", ".config");
		tmpFile.deleteOnExit();
		RtfmModelIO.writeConfiguration(configuration, tmpFile);

		Configuration configuration2 = RtfmModelIO.loadConfiguration(tmpFile);
		Assert.assertNotNull(configuration2);
		Assert.assertEquals(1, configuration2.getFileHandlers().size());

		FileHandler fileHandler2 = configuration2.getFileHandlers().get(0);
		Assert.assertEquals(fileHandler.getPattern(), fileHandler2.getPattern());
		Assert.assertEquals(1, fileHandler2.getFactories().size());
		Assert.assertTrue(fileHandler2.getFactories().get(0) instanceof FixedAttributeFactory);

		FixedAttributeFactory fixedAttributeFactory2 = (FixedAttributeFactory) fileHandler2.getFactories().get(0);
		Assert.assertEquals(fixedAttributeFactory.getName(), fixedAttributeFactory2.getName());
		Assert.assertEquals(fixedAttributeFactory.getValue(), fixedAttributeFactory2.getValue());
		Assert.assertEquals(fixedAttributeFactory.getStrategy(), fixedAttributeFactory2.getStrategy());
		Assert.assertEquals(fixedAttributeFactory.isOptional(), fixedAttributeFactory2.isOptional());
	}
}

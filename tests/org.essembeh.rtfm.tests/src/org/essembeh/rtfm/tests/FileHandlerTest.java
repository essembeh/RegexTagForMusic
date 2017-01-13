package org.essembeh.rtfm.tests;

import org.apache.commons.lang3.mutable.MutableObject;
import org.eclipse.core.runtime.Path;
import org.essembeh.rtfm.core.controller.LibraryScanner;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.FixedAttributeFactory;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.RegexAttributeFactory;
import org.essembeh.rtfm.model.utils.AttributesUtils;
import org.essembeh.rtfm.model.utils.EmfModelUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.tests.utils.ResourcesUtils;
import org.essembeh.rtfm.tests.utils.TestNodeFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileHandlerTest {

	private static final String ATTR1 = "test.attr1";
	private static final String ATTR2 = "test.attr2";

	public static final String MP3_PATTERN = "(.*\\.mp3)";
	public static final String MP3_PATTERN_WITH_SUBSTITUTION = "(.*\\.@MP3)";

	@Test
	public void test01_Accept() throws Exception {
		FileHandler fileHandler = EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", "(.*\\.mp3)"), fh -> {
			fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory(ATTR1, "foo"));
			fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory(ATTR2, 1, false));
		});
		EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), conf -> {
			conf.getFileHandlers().add(fileHandler);
		});
		Assert.assertTrue(fileHandler.accept(TestNodeFactory.createNode("/foo/file.mp3").getVirtualPath()));
		Assert.assertFalse(fileHandler.accept(TestNodeFactory.createNode("/foo/file.jpg").getVirtualPath()));
	}

	@Test
	public void test02_SimpleAttributes() throws Exception {
		Configuration configuration = EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), config -> {
			config.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", "(.*)\\.mp3"), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory(ATTR1, "foo"));
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory(ATTR2, 1, false));
			}));
		});

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getTreeRootFolder().getAbsolutePath());

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Node node;
		Assert.assertNotNull(node = library.getRoot().find(new Path("/folder/file1.mp3")));
		Assert.assertEquals("foo", node.getAttributes().get(ATTR1));
		Assert.assertEquals("/folder/file1", node.getAttributes().get(ATTR2));
		Assert.assertNotNull(node = library.getRoot().find(new Path("/file1.txt")));
		Assert.assertFalse(node.getAttributes().containsKey(ATTR1));
		Assert.assertFalse(node.getAttributes().containsKey(ATTR2));
	}

	@Test
	public void test03_StrategyUpdate() throws Exception {
		MutableObject<FixedAttributeFactory> factory = new MutableObject<>();
		Configuration configuration = EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), config -> {
			config.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", "(.*)\\.mp3"), fh -> {
				factory.setValue(RtfmCustomFactory.createFixedAttributeFactory(ATTR1, "foo"));
				fh.getFactories().add(factory.getValue());
			}));
		});
		Assert.assertNotNull(factory.getValue());

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getTreeRootFolder().getAbsolutePath());

		Node node;

		factory.getValue().setStrategy(AttributeFactoryStrategy.UPDATE_ONLY);
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertNotNull(node = library.getRoot().find(new Path("/folder/file1.mp3")));
		Assert.assertFalse(node.getAttributes().containsKey(ATTR1));

		factory.getValue().setStrategy(AttributeFactoryStrategy.CREATE_ONLY);
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertNotNull(node = library.getRoot().find(new Path("/folder/file1.mp3")));
		Assert.assertEquals("foo", node.getAttributes().get(ATTR1));

		factory.getValue().setValue("bar");
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertNotNull(node = library.getRoot().find(new Path("/folder/file1.mp3")));
		Assert.assertEquals("foo", node.getAttributes().get(ATTR1));

		factory.getValue().setStrategy(AttributeFactoryStrategy.UPDATE_ONLY);
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertNotNull(node = library.getRoot().find(new Path("/folder/file1.mp3")));
		Assert.assertEquals("bar", node.getAttributes().get(ATTR1));
	}

	@Test
	public void test04_Substitution() throws Exception {
		Configuration configuration = EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), config -> {
			config.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", "(.*)\\.@MP3"), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory(ATTR1, 1, false));
			}));
		});

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getTreeRootFolder().getAbsolutePath());

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertFalse(library.getRoot().find(new Path("/file1.mp3")).getAttributes().containsKey(ATTR1));
		Assert.assertFalse(library.getRoot().find(new Path("/file2.mp3")).getAttributes().containsKey(ATTR1));
		Assert.assertFalse(library.getRoot().find(new Path("/folder/file1.mp3")).getAttributes().containsKey(ATTR1));
		Assert.assertFalse(library.getRoot().find(new Path("/file1.txt")).getAttributes().containsKey(ATTR1));

		configuration.getSubstitutions().put("@MP3", "mp3");

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertEquals("/file1", library.getRoot().find(new Path("/file1.mp3")).getAttributes().get(ATTR1));
		Assert.assertEquals("/file2", library.getRoot().find(new Path("/file2.mp3")).getAttributes().get(ATTR1));
		Assert.assertEquals("/folder/file1", library.getRoot().find(new Path("/folder/file1.mp3")).getAttributes().get(ATTR1));
		Assert.assertFalse(library.getRoot().find(new Path("/file1.txt")).getAttributes().containsKey(ATTR1));
	}

	@Test
	public void test05_Optionnal() throws Exception {
		MutableObject<RegexAttributeFactory> factory = new MutableObject<>();
		Configuration configuration = EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), config -> {
			config.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", "(.*)\\.mp3"), fh -> {
				factory.setValue(RtfmCustomFactory.createRegexAttributeFactory(ATTR1, 2, false));
				fh.getFactories().add(factory.getValue());
			}));
		});
		Assert.assertNotNull(factory.getValue());

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getTreeRootFolder().getAbsolutePath());

		Assert.assertFalse(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertFalse(library.getRoot().find(new Path("/file1.mp3")).getAttributes().containsKey(ATTR1));
		Assert.assertTrue(library.getRoot().find(new Path("/file1.mp3")).getAttributes().containsKey(AttributeConstants.App.ERROR));

		library.getRoot().find(new Path("/file1.mp3")).getAttributes().removeKey(AttributeConstants.App.ERROR);
		factory.getValue().setOptional(true);

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertFalse(library.getRoot().find(new Path("/file1.mp3")).getAttributes().containsKey(ATTR1));
		Assert.assertFalse(library.getRoot().find(new Path("/file1.mp3")).getAttributes().containsKey(AttributeConstants.App.ERROR));

	}

	@Test
	public void test06_Append() throws Exception {
		MutableObject<FixedAttributeFactory> factory = new MutableObject<>();
		Configuration configuration = EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), config -> {
			config.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", ".*"), fh -> {
				factory.setValue(RtfmCustomFactory.createFixedAttributeFactory(ATTR1, "foo"));
				fh.getFactories().add(factory.getValue());
			}));
		});
		Assert.assertNotNull(factory.getValue());

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getTreeRootFolder().getAbsolutePath());

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertEquals("foo", library.getRoot().find(new Path("/file1.mp3")).getAttributes().get(ATTR1));

		factory.getValue().setValue("bar");
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertEquals("bar", library.getRoot().find(new Path("/file1.mp3")).getAttributes().get(ATTR1));

		factory.getValue().setValue("plop");
		factory.getValue().setStrategy(AttributeFactoryStrategy.CREATE_OR_APPEND);
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertEquals(AttributesUtils.appendIfNeeded("bar", "plop"), library.getRoot().find(new Path("/file1.mp3")).getAttributes().get(ATTR1));
	}
}

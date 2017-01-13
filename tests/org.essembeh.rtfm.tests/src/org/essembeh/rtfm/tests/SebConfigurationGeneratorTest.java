package org.essembeh.rtfm.tests;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.essembeh.rtfm.core.configuration.ConfigurationManager;
import org.essembeh.rtfm.core.controller.LibraryScanner;
import org.essembeh.rtfm.core.utils.RtfmModelIO;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.filter.NodeFilterFactory;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.Workflow;
import org.essembeh.rtfm.model.utils.NodeUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.tests.utils.ResourcesUtils;
import org.essembeh.rtfm.tests.utils.SysoutUtils;
import org.junit.Assert;
import org.junit.Test;

public class SebConfigurationGeneratorTest {

	private static final Predicate<Node> MP3 = NodeFilterFactory.attributeEquals(AttributeConstants.File.EXTENSION, "mp3");

	@Test
	public void saveAndLoadConfiguration() throws Exception {
		File tmpConfigFile = File.createTempFile("rtfm-seb-" + System.currentTimeMillis() + "-", "." + RtfmModelIO.CONFIG_EXTENSION);
		Configuration configuration1 = ConfigurationManager.INSTANCE.load(URI.create("rtfm:seb"));
		RtfmModelIO.writeConfiguration(configuration1, tmpConfigFile);
		Configuration configuration2 = RtfmModelIO.loadConfiguration(tmpConfigFile);
		testConfiguration(configuration2);
	}

	@Test
	public void testConfigGenerated() throws Exception {
		testConfiguration(ConfigurationManager.INSTANCE.load(URI.create("rtfm:seb")));
	}

	private void testConfiguration(Configuration configuration) throws Exception {
		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getSebRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));

		NodeUtils.recursiveStream(library.getRoot()).sorted().forEach(SysoutUtils::dumpNodeWithAttributes);
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), MP3.and(NodeFilterFactory.attributeExists("music.artist"))).size());
		Assert.assertEquals(6, NodeUtils.filterNodes(library.getRoot(), MP3.and(NodeFilterFactory.attributeExists("music.year"))).size());
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), MP3.and(NodeFilterFactory.attributeExists("music.album"))).size());
		Assert.assertEquals(SebLibraryTest.MP3_ALBUM_COUNT, NodeUtils.filterNodes(library.getRoot(), MP3.and(NodeFilterFactory.attributeExists("music.track"))).size());
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), MP3.and(NodeFilterFactory.attributeExists("music.title"))).size());
		Assert.assertEquals(0, NodeUtils.filterNodes(library.getRoot(), MP3.and(NodeFilterFactory.HAS_ERROR)).size());
	}

	@Test
	public void testTag() throws Exception {
		Configuration configuration = ConfigurationManager.INSTANCE.load(URI.create("rtfm:seb"));

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.dupplicateSebRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));

		NodeUtils.visit(library, SysoutUtils::dumpNodeWithAttributes);
		Workflow tag = configuration.getWorkflows().stream().filter(w -> w.getName().equals("Tag with eyed3")).findFirst().get();
		List<Node> mp3s = NodeUtils.filterNodes(library.getRoot(), n -> tag.accept(n));
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, mp3s.size());
		IStatus status = tag.execute(new BasicEList<>(mp3s), new NullProgressMonitor());
		SysoutUtils.dumpStatus(status);
		Assert.assertTrue(StatusUtils.hasNoError(status));
		Assert.assertEquals(mp3s.size(), NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeEquals("seb.tagged", "true")).size());
	}
}

package org.essembeh.rtfm.tests.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.essembeh.rtfm.core.configuration.ConfigurationManager;
import org.essembeh.rtfm.core.controller.LibraryScanner;
import org.essembeh.rtfm.core.utils.FileUtils2;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.RtfmFactory;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.junit.Assert;

public class ResourcesUtils {

	public static File getTreeRootFolder() throws URISyntaxException, IOException {
		URL url = FileLocator.find(Platform.getBundle("org.essembeh.rtfm.tests"), Path.fromOSString("resources").append("tree"), null);
		return FileUtils2.checkFolderExists(new File(FileLocator.toFileURL(url).toURI()));
	}

	public static File getSebRootFolder() throws URISyntaxException, IOException {
		URL url = FileLocator.find(Platform.getBundle("org.essembeh.rtfm.tests"), Path.fromOSString("resources").append("seb"), null);
		return FileUtils2.checkFolderExists(new File(FileLocator.toFileURL(url).toURI()));
	}

	public static File dupplicateSebRootFolder() throws URISyntaxException, IOException {
		File source = getSebRootFolder();
		File out = Files.createTempDirectory("rtfm-seb").toFile();
		FileUtils2.checkFolderExists(out);
		FileUtils.copyDirectory(source, out);
		return out;
	}

	public static Library getSimpleLibrary() throws URISyntaxException, IOException {
		Library library = RtfmFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getTreeRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, ConfigurationManager.INSTANCE.getDefault(), null)));
		Assert.assertNotNull(library.getRoot());
		return library;
	}
}

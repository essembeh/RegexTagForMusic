package org.essembeh.rtfm.junit;

import java.io.File;

import org.junit.Before;

public class VirtualFileTest {

	File linuxFolder;
	File linuxFileInFolder;
	File linuxFileNotInFolder;

	File windowsFolder;
	File windowsFileInFolder;
	File windowsFileNotInFolder;

	@Before
	public void init() {
		linuxFolder = new File("/home/seb/");
		linuxFileInFolder = new File("/home/seb/file");
		linuxFileNotInFolder = new File("/tmp/file");
		windowsFolder = new File("C:\\seb\\");
		windowsFileInFolder = new File("C:\\seb\\file");
		windowsFileNotInFolder = new File("C:\\tmp\\file");
	}
}

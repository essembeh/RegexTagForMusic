/**
 * Copyright 2012 Sebastien M-B <seb@essembeh.org>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.bootstrap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to build the application classpath on the fly. <br/>
 * It avoids using scripts to launch the application and is cross platform.
 * 
 * @author seb
 * 
 */
public class RTFMBootStrap {

	/**
	 * The mainclass to invoke
	 */
	private static final String MAINCLASS = "org.essembeh.rtfm.launcher.LaunchUI";

	/**
	 * Returns all jars found in the folder. This methode is recursive.
	 * 
	 * @param folder
	 * @return
	 */
	static List<File> folderToJar(File folder) {
		List<File> list = new ArrayList<File>();
		File[] ls = folder.listFiles();
		for (int i = 0; i < ls.length; i++) {
			File jar = ls[i];
			if (jar.isDirectory()) {
				list.addAll(folderToJar(jar));
			} else if (jar.getName().endsWith(".jar")) {
				list.add(jar);
			}
		}
		return list;
	}

	/**
	 * Checks if the folder is valid
	 * 
	 * @param folder
	 * @throws IOException
	 */
	static void checkValidFolder(File folder) throws IOException {
		if (folder == null) {
			throw new IOException("Null folder");
		}
		if (!folder.isDirectory()) {
			throw new IOException("Invalid folder: " + folder.getAbsolutePath());
		}
		if (!folder.exists()) {
			throw new IOException("Folder does not exist: " + folder.getAbsolutePath());
		}
	}

	/**
	 * Entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			List<URL> urls = new ArrayList<URL>();
			System.out.println("Bootstrapping");
			File bootstrapJar = new File(RTFMBootStrap.class.getProtectionDomain().getCodeSource().getLocation()
					.getPath());
			File binFolder = bootstrapJar.getParentFile();
			checkValidFolder(binFolder);
			System.out.println("Bin folder: " + binFolder.getAbsolutePath());

			File homeFolder = binFolder.getParentFile();
			checkValidFolder(homeFolder);
			System.out.println("RTFM Home folder: " + homeFolder.getAbsolutePath());

			File libFolder = new File(homeFolder, "lib");
			checkValidFolder(libFolder);
			System.out.println("Lib folder: " + homeFolder.getAbsolutePath());
			for (File jar : folderToJar(libFolder)) {
				if (jar.getName().endsWith(".jar")) {
					urls.add(jar.toURI().toURL());
				}
			}

			File customFolder = new File(System.getProperty("user.home"), ".rtfm");
			try {
				checkValidFolder(customFolder);
				System.out.println("Custom folder: " + customFolder.getAbsolutePath());
				urls.add(customFolder.toURI().toURL());
			} catch (IOException e) {
				System.out.println("Cannot find custom folder: " + customFolder.getAbsolutePath());
			}

			File configFolder = new File(homeFolder, "config");
			checkValidFolder(configFolder);
			System.out.println("Config folder: " + homeFolder.getAbsolutePath());
			urls.add(configFolder.toURI().toURL());

			File shareFolder = new File(homeFolder, "share");
			checkValidFolder(shareFolder);
			System.out.println("Share folder: " + homeFolder.getAbsolutePath());
			urls.add(shareFolder.toURI().toURL());

			for (URL url : urls) {
				System.out.println("Adding to classpath: " + url);
			}

			ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
			ClassLoader bootClassLoader = new URLClassLoader(urls.toArray(new URL[0]), currentClassLoader);
			Thread.currentThread().setContextClassLoader(bootClassLoader);
			Class<?> clazz = bootClassLoader.loadClass(MAINCLASS);
			Class<?>[] argTypes = new Class[] { String[].class };
			Method main = clazz.getDeclaredMethod("main", argTypes);
			main.invoke(null, (Object) null);

		} catch (Exception e) {
			System.out.println("Error bootstrapping RegexTagForMusic, try using startup scripts");
		}
	}
}

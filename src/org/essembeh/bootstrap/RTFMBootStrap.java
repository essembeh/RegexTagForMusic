package org.essembeh.bootstrap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class RTFMBootStrap {

	private static final String MAINCLASS = "org.essembeh.rtfm.launcher.LaunchGui";

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

			File customFolder = new File(System.getProperty("user.home") + "/.rtfm");
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
			System.out.println("Error bootstrapping RegexTagForMusic, try to use startup scripts");
		}
	}
}

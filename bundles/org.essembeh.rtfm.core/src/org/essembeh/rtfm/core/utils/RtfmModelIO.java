package org.essembeh.rtfm.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.RtfmFactory;

public class RtfmModelIO {

	public static final String CONFIG_EXTENSION = "config";
	public static final String LIBRARY_EXTENSION = "library";

	static {
		RtfmFactory.eINSTANCE.eClass();
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put(CONFIG_EXTENSION, new XMIResourceFactoryImpl());
		m.put(LIBRARY_EXTENSION, new XMIResourceFactoryImpl());
	}

	public static EObject loadEObject(File in, String extension) throws IOException {
		if (!in.getName().endsWith("." + extension)) {
			throw new IllegalArgumentException("Invalid file extension");
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI.createFileURI(in.getAbsolutePath()));
		resource.load(Collections.EMPTY_MAP);
		if (resource.getContents().size() == 1) {
			return resource.getContents().get(0);
		}
		return null;
	}

	public static void writeEObject(EObject data, File out, String extension) throws IOException {
		if (!out.getName().endsWith("." + extension)) {
			throw new IllegalArgumentException("Invalid file extension");
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI.createFileURI(out.getAbsolutePath()));
		resource.getContents().add(data);
		resource.save(Collections.EMPTY_MAP);
	}

	public static void writeConfiguration(Configuration configuration, File out) throws IOException {
		writeEObject(configuration, out, CONFIG_EXTENSION);
	}

	public static Configuration loadConfiguration(File in) throws IOException {
		EObject out = loadEObject(in, CONFIG_EXTENSION);
		return (Configuration) (out instanceof Configuration ? out : null);
	}

	public static void writeLibrary(Library configuration, File out) throws IOException {
		writeEObject(configuration, out, LIBRARY_EXTENSION);
	}

	public static Library loadLibrary(File in) throws IOException {
		EObject out = loadEObject(in, LIBRARY_EXTENSION);
		return (Library) (out instanceof Library ? out : null);
	}
}

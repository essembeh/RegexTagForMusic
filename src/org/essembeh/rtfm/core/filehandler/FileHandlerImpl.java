package org.essembeh.rtfm.core.filehandler;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.attributes.AttributeIdentifier;
import org.essembeh.rtfm.core.attributes.dynamic.DynamicAttribute;
import org.essembeh.rtfm.core.attributes.dynamic.DynamicAttributeIdentifier;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.MusicFileImpl;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.workflow.Engine;
import org.essembeh.rtfm.core.workflow.Task;

public class FileHandlerImpl implements FileHandler {

	public enum FileType {
		file, folder
	};

	String id;

	Pattern patternOnVirtualPath;

	FileType applyOnType;

	IdList<Attribute, Identifier<Attribute>> simpleAttributes;

	IdList<DynamicAttribute, Identifier<DynamicAttribute>> dynamicAttributes;

	Engine engine;

	public FileHandlerImpl(String id, String pattern, FileType fileType) {
		this.id = id;
		this.patternOnVirtualPath = Pattern.compile(pattern);
		this.applyOnType = fileType;
		engine = new Engine();
		simpleAttributes = new IdList<Attribute, Identifier<Attribute>>(new AttributeIdentifier());
		dynamicAttributes = new IdList<DynamicAttribute, Identifier<DynamicAttribute>>(new DynamicAttributeIdentifier());
	}

	@Override
	public boolean canHandle(VirtualFile file) {
		FileType theFileType = file.isDirectory() ? FileType.folder : FileType.file;
		return theFileType == this.applyOnType && this.patternOnVirtualPath.matcher(file.getVirtualPath()).matches();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "FileHandlerImpl [id:" + id + ", pattern:" + patternOnVirtualPath.pattern() + "]";
	}

	@Override
	public MusicFile create(VirtualFile file) throws InstantiationException, DynamicAttributeException {
		if (!canHandle(file)) {
			throw new InstantiationException("Cannot instantiate: " + id + ", on file: " + file);
		}
		MusicFile musicFile = new MusicFileImpl(id, file, engine);
		for (Attribute attribute : this.simpleAttributes) {
			musicFile.getAttributeList().add(attribute.clone());
		}
		for (DynamicAttribute dynamicAttribute : this.dynamicAttributes) {
			Attribute valuatedAttribute = dynamicAttribute.createAttribute(file);
			if (valuatedAttribute != null) {
				musicFile.getAttributeList().add(valuatedAttribute);
			}
		}
		return musicFile;
	}

	public void addAttribute(Attribute attribute) {
		this.simpleAttributes.add(attribute);
	}

	public void addDynamicAttribute(DynamicAttribute attribute) {
		this.dynamicAttributes.add(attribute);
	}

	public void addWorkflow(String name, Task... tasks) {
		engine.createWorkflow(name, tasks);
	}
}

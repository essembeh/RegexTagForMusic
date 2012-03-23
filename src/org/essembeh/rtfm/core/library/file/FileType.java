package org.essembeh.rtfm.core.library.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileType implements Comparable<FileType> {

	private static List<FileType> knownFiletypes = new ArrayList<FileType>();

	/**
	 * Returns all known Filetypes
	 * 
	 * @return
	 */
	public static List<FileType> getAllFileTypes() {
		return Collections.unmodifiableList(knownFiletypes);
	}

	public static FileType getFiletype(String id) {
		for (FileType fileType : knownFiletypes) {
			if (fileType.getIdentifier().equals(id)) {
				return fileType;
			}
		}
		return null;
	}

	public static FileType createFiletype(String id) {
		FileType newFileType = getFiletype(id);
		if (newFileType == null) {
			newFileType = new FileType(id);
			knownFiletypes.add(newFileType);
		}
		return newFileType;
	}

	private final String identifier;

	private FileType(String id) {
		identifier = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof FileType && identifier.equals(((FileType) obj).getIdentifier());
	}

	@Override
	public int compareTo(FileType o) {
		return identifier.compareTo(o.getIdentifier());
	}
}

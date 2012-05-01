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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static FileType getFiletype(String id) {
		for (FileType fileType : knownFiletypes) {
			if (fileType.getIdentifier().equals(id)) {
				return fileType;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static FileType createFiletype(String id) {
		FileType newFileType = getFiletype(id);
		if (newFileType == null) {
			newFileType = new FileType(id);
			knownFiletypes.add(newFileType);
		}
		return newFileType;
	}

	/**
	 * 
	 */
	private final String identifier;

	/**
	 * 
	 * @param id
	 */
	private FileType(String id) {
		identifier = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof FileType && identifier.equals(((FileType) obj).getIdentifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FileType o) {
		return identifier.compareTo(o.getIdentifier());
	}
}

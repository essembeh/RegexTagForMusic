package org.essembeh.rtfm.fs.exception;

abstract public class FileSystemException extends CommonException {
	private static final long serialVersionUID = 4770018366226375241L;

	public FileSystemException(String message) {
		super(message);
	}
}

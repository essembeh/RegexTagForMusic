package org.essembeh.rtfm.model;

public interface AttributeConstants {

	static final String ATTRIBUTE_NAME_REGEX = "[a-zA-Z0-9_\\.-]+";

	static final String SEPARATOR = ", ";
	static final String NAMESPACE_SEPARATOR = ".";

	public static String nsName(String ns, String name) {
		return ns + NAMESPACE_SEPARATOR + name;
	}

	public static String asVar(String in) {
		return "${" + in + "}";
	}

	public interface File {
		static final String NS = "file";
		static final String EXTENSION = nsName(NS, "extension");
		static final String SIZE = nsName(NS, "size");
		static final String LAST_MODIFIED = nsName(NS, "lastModified");
		static final String FULLPATH = nsName(NS, "path");
		static final String TYPE = nsName(NS, "type");

		static final String TYPE_FILE = "file";
		static final String TYPE_FOLDER = "folder";
	}

	public interface App {
		static final String NS = "app";
		static final String SKIP = nsName(NS, "skip");
		static final String ERROR = nsName(NS, "error");
		static final String IMPORTED = nsName(NS, "importDate");
		static final String LAST_SCAN = nsName(NS, "lastScan");
	}

}

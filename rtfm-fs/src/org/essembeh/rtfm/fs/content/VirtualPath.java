package org.essembeh.rtfm.fs.content;

import java.io.File;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.fs.util.ArrayTools;

public class VirtualPath implements Comparable<VirtualPath> {

	public final static String SEPARATOR = "/";
	public final static VirtualPath ROOT = new VirtualPath(SEPARATOR);

	private final String[] segments;
	private final boolean isAbsolute;
	private final boolean isFolder;

	public VirtualPath(String[] segments, boolean isAbsolute, boolean isFolder) {
		this.segments = segments;
		this.isAbsolute = isAbsolute;
		this.isFolder = isFolder;
	}

	public VirtualPath(String virtualPath) {
		this(StringUtils.split(virtualPath, SEPARATOR), virtualPath.startsWith(SEPARATOR), virtualPath
				.endsWith(SEPARATOR));
	}

	public VirtualPath(VirtualPath parent, String child, boolean isFolder) {
		this(ArrayTools.concat(parent.segments, child), parent.isAbsolute, isFolder);
	}

	public VirtualPath(VirtualPath parent, File child) {
		this(parent, child.getName(), child.isDirectory());
	}

	public boolean isRoot() {
		return segments.length == 0 && isAbsolute;
	}

	public VirtualPath addPrefix(String segment) {
		return new VirtualPath(ArrayTools.concat(StringUtils.split(segment, SEPARATOR), segments),
				segment.startsWith(SEPARATOR), isFolder);
	}

	public VirtualPath addSuffix(String segment) {
		return new VirtualPath(ArrayTools.concat(segments, StringUtils.split(segment, SEPARATOR)), isAbsolute,
				segment.endsWith(SEPARATOR));
	}

	public VirtualPath parent() {
		if (segments.length == 0) {
			return null;
		}
		return new VirtualPath(ArrayTools.removeLast(segments), isAbsolute, true);
	}

	public VirtualPath removeFirstSegment() {
		if (segments.length == 0) {
			return null;
		}
		return new VirtualPath(ArrayTools.removeFirst(segments), false, isFolder);
	}

	public String[] getSegments() {
		return segments;
	}

	public boolean isAbsolute() {
		return isAbsolute;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public String getFirstSegment() {
		return ArrayTools.getFirst(segments);
	}

	public String getLastSegment() {
		return ArrayTools.getLast(segments);
	}

	@Override
	public String toString() {
		if (ROOT == this) {
			return SEPARATOR;
		}
		return (isAbsolute ? SEPARATOR : "") + StringUtils.join(segments, SEPARATOR)
				+ (isFolder && segments.length > 0 ? SEPARATOR : "");
	}

	@Override
	public int compareTo(VirtualPath o) {
		return ObjectUtils.compare(toString(), o.toString());
	}
}

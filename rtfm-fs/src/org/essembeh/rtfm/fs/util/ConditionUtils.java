package org.essembeh.rtfm.fs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.essembeh.rtfm.fs.condition.AndCondition;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.condition.MultipleCondition;
import org.essembeh.rtfm.fs.condition.OrCondition;
import org.essembeh.rtfm.fs.condition.impl.AlwaysFalse;
import org.essembeh.rtfm.fs.condition.impl.AlwaysTrue;
import org.essembeh.rtfm.fs.condition.impl.AttributeExists;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueMatches;
import org.essembeh.rtfm.fs.condition.impl.Extension;
import org.essembeh.rtfm.fs.condition.impl.FileOrFolder;
import org.essembeh.rtfm.fs.condition.impl.FileOrFolder.ResourceType;
import org.essembeh.rtfm.fs.condition.impl.VirtualPathMatches;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ConditionUtils {

	public static ICondition alwaysFalse() {
		return new AlwaysFalse();
	}

	public static ICondition alwaysTrue() {
		return new AlwaysTrue();
	}

	public static ICondition attributeExists(String name, boolean exists) {
		return new AttributeExists(name, exists);
	}

	public static ICondition attributeValueEquals(String attributeName, String expectedValue) {
		return new AttributeValueEquals(attributeName, expectedValue);
	}

	public static ICondition attributeValueMatches(String attributeName, Pattern regexOnValue) {
		return new AttributeValueMatches(attributeName, regexOnValue);
	}

	public static ICondition extension(String[] extensionsList, boolean caseSensitive) {
		return new Extension(extensionsList, caseSensitive);
	}

	public static ICondition fileOrFolder(ResourceType type) {
		return new FileOrFolder(type);
	}

	public static ICondition virtualPathMatches(Pattern regexOnPath) {
		return new VirtualPathMatches(regexOnPath);
	}

	public static MultipleCondition andCondition() {
		return new AndCondition();
	}

	public static MultipleCondition orCondition() {
		return new OrCondition();
	}

	public static List<IResource> filter(List<IResource> in, ICondition filter) {
		List<IResource> out = new ArrayList<>();
		if (filter == null) {
			out.addAll(in);
		} else {
			for (IResource resource : in) {
				if (filter.isTrue(resource)) {
					out.add(resource);
				}
			}
		}
		return out;
	}

}

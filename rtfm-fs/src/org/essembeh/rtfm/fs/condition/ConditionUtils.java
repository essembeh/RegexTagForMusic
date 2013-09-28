package org.essembeh.rtfm.fs.condition;

import java.util.ArrayList;
import java.util.List;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ConditionUtils {

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

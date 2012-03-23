package org.essembeh.rtfm.core.library.filter.conditions;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class TypeCondition implements IFilterCondition {

	private String[] validTypes;

	public TypeCondition(String... validTypes) {
		this.validTypes = validTypes;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		for (int i = 0; i < validTypes.length; i++) {
			if (musicFile.getType().equals(FileType.getFiletype(validTypes[i]))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "TypeCondition [validTypes=" + StringUtils.join(validTypes, ", ") + "]";
	}

}

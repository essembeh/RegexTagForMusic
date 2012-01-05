package org.essembeh.rtfm.core.library.filter.conditions;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.library.file.MusicFile;

public class TypeCondition implements Condition {

	String[] validTypes;

	public TypeCondition(String[] validTypes) {
		this.validTypes = validTypes;
	}

	@Override
	public boolean isTrue(MusicFile musicFile) {
		for (int i = 0; i < validTypes.length; i++) {
			if (validTypes[i].equals(musicFile.getType())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "TypeCondition [regexOnType=" + StringUtils.join(validTypes, ", ") + "]";
	}

}

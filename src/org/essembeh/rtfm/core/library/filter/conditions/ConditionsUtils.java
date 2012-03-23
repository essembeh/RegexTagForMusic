package org.essembeh.rtfm.core.library.filter.conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ConditionsUtils {

	private static Logger logger = Logger.getLogger(ConditionsUtils.class);

	Pattern validation;

	String attributeValue = "([^,=]+)=([^,=]*)";

	public ConditionsUtils() {
		validation = Pattern.compile(attributeValue + "(?:," + attributeValue + ")*");
	}

	public IFilterCondition[] stringToConditions(String line) {
		Matcher matcher = validation.matcher(line);
		if (!matcher.matches()) {
			logger.warn("The condition line is not valid: " + line);
			return null;
		}
		List<IFilterCondition> list = new ArrayList<IFilterCondition>();
		for (int i = 1; i < matcher.groupCount(); i += 2) {
			String key = matcher.group(i);
			String value = matcher.group(i + 1);
			if (key != null) {
				if (value == null || value.length() == 0) {
					value = ".*";
				}
				list.add(new AttributeValueCondition(key, Pattern.compile(value)));
			}
		}
		return list.toArray(new IFilterCondition[0]);
	}
}

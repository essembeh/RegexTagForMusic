package org.essembeh.rtfm.core.library.filter;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.filter.conditions.AttributeValueCondition;
import org.essembeh.rtfm.core.library.filter.conditions.CanExecuteActionCondition;
import org.essembeh.rtfm.core.library.filter.conditions.TypeCondition;

public class CommonFilters {

	public static Filter noFilter() {
		return new Filter();
	}

	public static Filter filterOnAttribute(String attributeName, String expectedValue) {
		Filter filter = new Filter();
		filter.addCondition(new AttributeValueCondition(attributeName, Pattern.compile(expectedValue)));
		return filter;
	}

	public static Filter filterOnType(String expectedValue) {
		Filter filter = new Filter();
		filter.addCondition(new TypeCondition(new String[] { expectedValue }));
		return filter;
	}

	public static Filter filterOnAction(String action) {
		Filter filter = new Filter();
		filter.addCondition(new CanExecuteActionCondition(action));
		return filter;

	}
}

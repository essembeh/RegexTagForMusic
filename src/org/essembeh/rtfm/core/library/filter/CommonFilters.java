/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.library.filter;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.condition.impl.virtualfile.VirtualPathMatches;
import org.essembeh.rtfm.core.condition.impl.xfile.AttributeValueEquals;
import org.essembeh.rtfm.core.condition.impl.xfile.TypeEquals;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.workflow.Workflow;

public class CommonFilters {

	public static XFileFilter noFilter() {
		return new XFileFilter();
	}

	public static XFileFilter filterOnAttribute(String attributeName, String expectedValue) {
		XFileFilter filter = new XFileFilter();
		filter.addCondition(new AttributeValueEquals<IXFile>(attributeName, expectedValue));
		return filter;
	}

	public static XFileFilter filterOnType(String expectedValue) {
		XFileFilter filter = new XFileFilter();
		filter.addCondition(new TypeEquals<IXFile>(expectedValue));
		return filter;
	}

	public static XFileFilter virtualPathStartsWith(String expectedValue) {
		XFileFilter filter = new XFileFilter();
		filter.addCondition(new VirtualPathMatches<IXFile>(Pattern.quote(expectedValue) + ".*"));
		return filter;
	}

	public static XFileFilter supportedByWorkflow(final Workflow workflow) {
		XFileFilter filter = new XFileFilter();
		filter.addCondition(new ICondition<IXFile>() {

			@Override
			public boolean isTrue(IXFile input) {
				return workflow != null && workflow.supportFile(input);
			}
		});
		return filter;
	}
}

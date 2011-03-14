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

package org.essembeh.rtfm.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.essembeh.rtfm.core.conf.Configuration;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testProperties() throws Throwable {
		Configuration conf = Configuration.instance();
		assertNotNull(conf);
		String appName = conf.getProperty("app.version");
		assertNotNull(appName);
		String plop = conf.getProperty("plop");
		assertNull(plop);
	}
}

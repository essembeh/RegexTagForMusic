package org.essembeh.rtfm.cli.resolver;

import java.nio.file.Path;
import java.util.function.Function;

import org.apache.commons.io.FilenameUtils;
import org.essembeh.rtfm.cli.utils.ResourceUtils;

public enum BuiltinVariables {
	PATH("path", ResourceUtils::getFullPath),
	BASENAME("basename", in -> FilenameUtils.getName(ResourceUtils.getFullPath(in))),
	FILENAME("filename", in -> FilenameUtils.getBaseName(ResourceUtils.getFullPath(in))),
	DIRNAME("dirname", in -> ResourceUtils.getFullPath(in.getParent())),
	EXTENSION("extension", in -> FilenameUtils.getExtension(ResourceUtils.getFullPath(in)));

	private final String varName;
	private final Function<Path, String> function;

	BuiltinVariables(String varName, Function<Path, String> function) {
		this.varName = varName;
		this.function = function;
	}

	public String getVarName() {
		return varName;
	}

	public String resolve(Path in) {
		return function.apply(in);
	}

}

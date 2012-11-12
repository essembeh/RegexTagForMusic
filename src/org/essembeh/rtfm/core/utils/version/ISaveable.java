package org.essembeh.rtfm.core.utils.version;

import java.io.OutputStream;

import org.essembeh.rtfm.core.utils.version.exceptions.WriterException;

public interface ISaveable {

	void save(OutputStream outputStream) throws WriterException;
}

import java.io.File;
import java.io.IOException;

import org.essembeh.rtfm.core.CoreModule;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.exception.old.AbstractException;
import org.essembeh.rtfm.core.exception.old.DatabaseException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.gui.controller.MusicManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Test {

	public static void main(String[] args) throws IOException, ActionException, DatabaseException,
			DynamicAttributeException, AbstractException {
		Injector injector = Guice.createInjector(new CoreModule());
		MusicManager manager = injector.getInstance(MusicManager.class);

		File folder = new File("test/tree");
		// File folder = new File("/media/Data/Music/Albums");
		File db1 = new File("out1.xml");
		File db2 = new File("out2.xml");
		File db3 = new File("out3.xml");

		manager.scanFolder(folder);
		manager.saveDatabase(db1);
		for (IMusicFile musicFile : manager.getAllFiles()) {
			musicFile.executeAction("setTagged");
			musicFile.updateTagData();
			musicFile.executeAction("test");
		}
		manager.saveDatabase(db2);
		manager.loadDatabase(db2);
		manager.saveDatabase(db3);
	}
}

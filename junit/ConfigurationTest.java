import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.essembeh.rtfm.core.configuration.io.GenericConfigurationLoader;
import org.essembeh.rtfm.core.configuration.io.CoreConfigurationLoaderV1;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testConstructor() throws Throwable {
		GenericConfigurationLoader configurationLoader = new CoreConfigurationLoaderV1();
		assertNotNull(configurationLoader);
		List<FileHandler> list = configurationLoader.getFileHandlers("res/config/configuration.xml");
		assertNotNull(list);
	}
}

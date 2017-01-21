package org.essembeh.rtfm.cli;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.essembeh.rtfm.cli.app.App;
import org.essembeh.rtfm.cli.app.ProcessHelper.Status;
import org.essembeh.rtfm.cli.app.callback.DefaultCallback;
import org.essembeh.rtfm.cli.app.callback.ICallback;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.utils.Constants;
import org.essembeh.rtfm.cli.utils.JunitUtils;
import org.junit.Assert;
import org.junit.Test;

public class SamplesTest {

	protected static String getChecksum(Path in) {
		try (FileInputStream fis = new FileInputStream(in.toFile())) {
			return DigestUtils.md5Hex(fis);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
		return null;
	}

	@Test
	public void testDefault() throws Exception {
		testConfiguration(Constants.DEFAULT_CONFIG, Constants.DEFAULT_ROOT);
	}

	@Test
	public void testSeb() throws Exception {
		testConfiguration(Constants.SEB_CONFIG, Constants.SEB_ROOT);
	}

	protected void testConfiguration(Path config, Path sampleTree) throws Exception {
		Assert.assertTrue(Files.isDirectory(sampleTree));
		Path root = JunitUtils.copyConfigTree(sampleTree);
		try {
			Map<Path, String> checksums = new HashMap<>();
			Files.walk(root).filter(p -> "mp3".equals(FilenameUtils.getExtension(p.toString()))).forEach(p -> checksums.put(p, getChecksum(p)));
			Assert.assertFalse(checksums.isEmpty());

			runConfig(config, root, true);
			checksums.forEach((path, checksum) -> Assert.assertNotEquals(checksum, getChecksum(path)));

			runConfig(Constants.CLEAN_CONFIG, root, false);
			checksums.forEach((path, checksum) -> Assert.assertEquals(checksum, getChecksum(path)));
		} finally {
			FileUtils.deleteDirectory(root.toFile());
		}
	}

	protected void runConfig(Path config, Path root, boolean failOnUnknown) throws Exception {
		Assert.assertTrue(Files.isRegularFile(config));
		Assert.assertTrue(Files.isDirectory(root));

		Configuration configuration = Configuration.load(config);
		App app = new App(configuration);
		ICallback callback = new DefaultCallback() {
			@Override
			public void workflowException(String id, Exception e) {
				Assert.fail(e.getMessage());
			}

			@Override
			public void workflowEnds(String workflowId, boolean complete) {
				Assert.assertTrue(complete);
			}

			@Override
			public void commandEnds(String commandId, Status status) {
				Assert.assertFalse(status.isDryRun());
				Assert.assertEquals(0, status.getReturnCode());
			}

			@Override
			public void unknownType(String fullpath) {
				if (failOnUnknown) {
					Assert.fail(fullpath);
				}
			}
		};
		app.process(Files.walk(root).filter(Files::isRegularFile), p -> callback);
	}
}

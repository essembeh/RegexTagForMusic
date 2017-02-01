package org.essembeh.rtfm.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.essembeh.rtfm.core.app.App;
import org.essembeh.rtfm.core.app.ProcessHelper.Status;
import org.essembeh.rtfm.core.app.callback.DefaultCallback;
import org.essembeh.rtfm.core.app.callback.ICallback;
import org.essembeh.rtfm.core.config.Configuration;
import org.essembeh.rtfm.core.utils.TestConstants;
import org.essembeh.rtfm.core.utils.TestUtils;
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
		testConfiguration(TestConstants.DEFAULT_CONFIG, TestConstants.DEFAULT_ROOT);
	}

	@Test
	public void testSeb() throws Exception {
		testConfiguration(TestConstants.SEB_CONFIG, TestConstants.SEB_ROOT);
	}

	protected void testConfiguration(Path config, Path sampleTree) throws Exception {
		Assert.assertTrue(Files.isDirectory(sampleTree));
		Path root = TestUtils.copyConfigTree(sampleTree);
		try {
			Map<Path, String> checksums = new HashMap<>();
			Files.walk(root).filter(p -> "mp3".equals(FilenameUtils.getExtension(p.toString()))).forEach(p -> checksums.put(p, getChecksum(p)));
			Assert.assertFalse(checksums.isEmpty());

			runConfig(config, root, true);
			checksums.forEach((path, checksum) -> Assert.assertNotEquals(checksum, getChecksum(path)));

			runConfig(TestConstants.CLEAN_CONFIG, root, false);
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
		Function<Path, ICallback> callbackGenerator = new Function<Path, ICallback>() {

			@Override
			public ICallback apply(Path in) {
				return new DefaultCallback(in) {
					@Override
					public void workflowException(String id, Exception e) {
						Assert.fail(e.getMessage());
					}

					@Override
					public void workflowDone(String workflowId, boolean complete) {
						Assert.assertTrue(complete);
					}

					@Override
					public void commandEnds(String commandId, Status status) {
						Assert.assertFalse(status.isDryRun());
						Assert.assertEquals(0, status.getReturnCode());
					}

					@Override
					public void unknownType() {
						if (failOnUnknown) {
							Assert.fail(getPath().toString());
						}
					}
				};
			}
		};
		Files.walk(root).filter(Files::isRegularFile).forEach(p -> app.process(p, callbackGenerator.apply(p)));
	}

}

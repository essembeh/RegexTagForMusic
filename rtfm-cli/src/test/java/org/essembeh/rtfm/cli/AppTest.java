package org.essembeh.rtfm.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import org.essembeh.rtfm.cli.app.App;
import org.essembeh.rtfm.cli.app.callback.DefaultCallback;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.config.Workflow;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

	@Test
	public void testDatabaseSkip() throws Exception {
		Configuration configuration = new Configuration();
		{
			Workflow workflow = new Workflow();
			workflow.setPattern(".*");
			configuration.getWorkflows().put("test", workflow);
		}
		Path db = Files.createTempFile("rtfm-db", ".json");
		Path input = Paths.get("/tmp/foo.bar");
		{
			App app = new App(configuration);
			Assert.assertFalse(app.getDatabase().getExecutionDate(input, "test").isPresent());
			AtomicBoolean skipped = new AtomicBoolean(false);
			app.process(input, new DefaultCallback(input) {
				@Override
				public void fileSkipped(String workflowId, Date lastExecution) {
					skipped.set(true);
				}
			});
			Assert.assertFalse(skipped.get());
			Assert.assertTrue(app.getDatabase().getExecutionDate(input, "test").isPresent());
			app.process(input, new DefaultCallback(input) {
				@Override
				public void fileSkipped(String workflowId, Date lastExecution) {
					skipped.set(true);
				}
			});
			Assert.assertTrue(skipped.get());
			app.saveDatabase(db);
		}
		{
			App app = new App(configuration);
			app.loadDatabase(db);
			AtomicBoolean skipped = new AtomicBoolean(false);
			Assert.assertTrue(app.getDatabase().getExecutionDate(input, "test").isPresent());
			app.process(input, new DefaultCallback(input) {
				@Override
				public void fileSkipped(String workflowId, Date lastExecution) {
					skipped.set(true);
				}
			});
			Assert.assertTrue(skipped.get());
		}
	}
}

package org.essembeh.rtfm.core.configuration.internal;

import org.essembeh.rtfm.core.configuration.IConfigurationTemplate;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.utils.EmfModelUtils;

public class SebConfigurationTemplate implements IConfigurationTemplate {

	@Override
	public String getName() {
		return "Seb's music configuration";
	}

	@Override
	public Configuration createModel() {
		return EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), configuration -> {
			configuration.getSubstitutions().put("@SEPARATOR", " - ");
			configuration.getSubstitutions().put("@ANYTHING", "[^/]+");
			configuration.getSubstitutions().put("@DOT-EXT", "\\.[a-zA-Z0-9]+");
			configuration.getSubstitutions().put("@DOT-MP3", "\\.mp3");
			configuration.getSubstitutions().put("@YEAR", "[12]\\d{3}");
			configuration.getSubstitutions().put("@TRACKNUMBER", "\\d{2,3}");
			configuration.getSubstitutions().put("@COVER", "cover\\.(png|jpg)");

			// Default: Invalid
			configuration.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("Initialization", ".*[^/]"), fh -> {
				fh.getFactories().add(EmfModelUtils.configure(RtfmCustomFactory.createFixedAttributeFactory("seb.valid", "false"), factory -> {
					factory.setStrategy(AttributeFactoryStrategy.CREATE_ONLY);
				}));
			}));
			// Ignore list
			configuration.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("Skip", "/tmp/"), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory(AttributeConstants.App.SKIP, Boolean.TRUE.toString()));
			}));
			// Album MP3
			configuration.getFileHandlers().add(EmfModelUtils
					.configure(RtfmCustomFactory.createFileHandler("Album mp3", "/Albums/(@ANYTHING)/((?:(@YEAR)@SEPARATOR)?@ANYTHING)/(@TRACKNUMBER)@SEPARATOR(@ANYTHING)@DOT-MP3"), fh -> {
						fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.artist", 1, false));
						fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.year", 3, true));
						fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.album", 2, false));
						fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.track", 4, false));
						fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.title", 5, false));
						fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("seb.valid", "true"));
					}));
			// Album Cover
			configuration.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("Album cover", "/Albums/(@ANYTHING)/((?:(@YEAR)@SEPARATOR)?@ANYTHING)/@COVER"), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.artist", 1, false));
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.year", 3, true));
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.album", 2, false));
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("seb.valid", "true"));
			}));
			// Single MP3
			configuration.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("Single mp3", "/Singles/(@ANYTHING)@SEPARATOR(@ANYTHING)@DOT-MP3"), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.artist", 1, false));
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("music.album", "#Single"));
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.title", 2, false));
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("seb.valid", "true"));
			}));
			// Saga MP3
			configuration.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("Saga mp3", "/Saga/(@ANYTHING)/(@ANYTHING)@DOT-MP3"), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.artist", 1, false));
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("music.album", "#Saga"));
				fh.getFactories().add(RtfmCustomFactory.createRegexAttributeFactory("music.title", 2, false));
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("seb.valid", "true"));
			}));

			// Workflow
			configuration.getWorkflows().add(EmfModelUtils.configure(RtfmCustomFactory.createWorkflow("Tag with eyed3"), workflow -> {
				workflow.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createAttributeTool(), task -> {
					task.getAttributesToUpdate().put("seb.tagged", "false");
				}));
				workflow.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.createExternalProcess("eyed3-id3v2", "/usr/bin/eyeD3"), task -> {
					task.getArguments().add("--no-color");
					task.getArguments().add("--artist=${music.artist}");
					task.getArguments().add("--album=${music.album}");
					task.getArguments().add("--track=${music.track}");
					task.getArguments().add("--year=${music.year}");
					task.getArguments().add("--title=${music.title}");
					task.getArguments().add(AttributeConstants.asVar(AttributeConstants.File.FULLPATH));
				}));
				workflow.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createAttributeTool(), task -> {
					task.getAttributesToUpdate().put("seb.tagged", "true");
				}));
				workflow.getFilters().add("music.artist");
				workflow.getFilters().add("music.title");
				workflow.getFilters().add("file.extension==mp3");
			}));

			// Workflow
			configuration.getWorkflows().add(EmfModelUtils.configure(RtfmCustomFactory.createWorkflow("Remove tags"), workflow -> {
				workflow.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.createExternalProcess("eyed3-clean", "/usr/bin/eyeD3"), task -> {
					task.getArguments().add("--no-color");
					task.getArguments().add("--remove-all");
					task.getArguments().add(AttributeConstants.asVar(AttributeConstants.File.FULLPATH));
				}));
				workflow.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createAttributeTool(), task -> {
					task.getAttributesToUpdate().put("seb.tagged", "false");
				}));
				workflow.getFilters().add("file.extension==mp3");
			}));

			// Workflow
			configuration.getWorkflows().add(EmfModelUtils.configure(RtfmCustomFactory.createWorkflow("Mark as not tagged"), workflow -> {
				workflow.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createAttributeTool(), x -> x.getAttributesToUpdate().put("seb.tagged", "false")));
				workflow.getFilters().add("seb.tagged==true");
			}));
		});
	}

	@Override
	public String getId() {
		return "seb";
	}
}

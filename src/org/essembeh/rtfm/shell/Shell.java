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

package org.essembeh.rtfm.shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.conf.Services;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.ShellCommandInvalidArgument;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class Shell {

	private static final String COMMENT_START = "#";

	public enum Mode {
		INTERACTIVE, BATCH
	}

	protected Logger logger = Logger.getLogger(getClass());

	protected MusicManager app;

	protected String prompt = "rtfm$ ";

	protected boolean endOfLoop;

	protected InputStream inputstream;

	protected StringBuilder buffer = new StringBuilder();

	protected Mode mode;

	private boolean useBuffer = false;

	/**
	 * 
	 * @param app
	 * @throws ConfigurationException
	 */
	public Shell(MusicManager app) throws ConfigurationException {
		this.app = app;
		this.endOfLoop = false;
		this.useBuffer = Boolean.getBoolean(Configuration.instance().getProperty("shell.buffer"));
	}

	/**
	 * 
	 */
	protected void displayPrompt() {
		// Wait logs before displaying prompt
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			this.logger.debug(e.toString());
		}
		System.out.print(this.prompt);
	}

	/**
	 * Run the shell
	 * 
	 * @throws IOException
	 */
	public void runInteractive() throws IOException {
		System.out.println("----- START INTERACTIVE MODE -----");
		this.mode = Mode.INTERACTIVE;
		this.inputstream = System.in;
		loop();
		System.out.println("----- END INTERACTIVE MODE -----");
	}

	/**
	 * Run the shell
	 * 
	 * @throws IOException
	 * 
	 */
	public void runScriptFile(File script) throws IOException {
		System.out.println("----- START SCRIPT MODE -----");
		this.mode = Mode.BATCH;
		this.inputstream = new FileInputStream(script);
		loop();
		System.out.println("----- END SCRIPT MODE -----");
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void loop() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(this.inputstream));
		this.logger.info("Starting shell loop");
		this.endOfLoop = false;
		while (!this.endOfLoop) {
			displayPrompt();
			String command = in.readLine();
			if (command == null) {
				quit();
			} else if (command.trim().startsWith(COMMENT_START)){ 
				this.logger.debug("Comment, do nothing");
			} else {
				if (this.mode != Mode.INTERACTIVE) {
					System.out.println(command);
				}
				preExecute();
				processCommand(StringUtils.stringToList(command, " "));
				postExecute();
			}
		}
		this.logger.info("End shell loop");
	}

	/**
	 * 
	 */
	protected void postExecute() {
		if (this.useBuffer) {
			System.out.println(this.buffer.toString());
		}
		System.out.println();
	}

	/**
	 * 
	 */
	protected void preExecute() {
		this.buffer = new StringBuilder();
	}

	/**
	 * 
	 * @param command
	 */
	protected void processCommand(List<String> args) {
		this.logger.debug("Process command: " + StringUtils.arrayToString(args.toArray(), " "));
		try {
			String commandName = args.get(0);
			ICommand command = Services.instance().instantiateCommand(commandName);
			if (command != null) {
				try {
					int rc = command.execute(this, this.app, args);
					this.logger.debug("Command: " + command + " exited with rc=" + rc);
				} catch (ShellCommandInvalidArgument e) {
					println("Invalid usage of command");
					println(command.getHelp(commandName));
				}
			} else {
				this.logger.warn("Cannot find command: " + commandName);
			}
		} catch (Exception e) {
			this.logger.error("Error instantiating command: " + e.toString());
			quit();
		}
	}

	/**
	 * 
	 */
	public void quit() {
		this.endOfLoop = true;
	}

	/**
	 * 
	 * @param message
	 */
	public void print(String message) {
		if (this.useBuffer) {
			this.buffer.append(message);
		} else {
			System.out.print(message);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void println(String message) {
		print(message + "\n");
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String fileToString(IMusicFile file) {
		StringBuilder out = new StringBuilder();
		if (file.isTaggable()) {
			if (file.isTagged()) {
				out.append("+ ");
			} else {
				out.append("- ");
			}
		} else {
			out.append("  ");
		}
		String id = "[" + file.getType() + "]";
		String formatedId = String.format("%-10s", id);
		out.append(formatedId).append(file.getVirtualPath());
		return out.toString();
	}

}

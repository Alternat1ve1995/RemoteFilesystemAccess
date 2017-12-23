package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.NoSuchFileException;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.NotAFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class Cat extends Command {

	public Cat(String command, String arguments) {
		super(command, arguments);
	}

	public Cat(String command) {
		super(command);
	}

	@Override
	public void execute(FileSystem fileSystem) throws IOException  {

		try {
			this.cat(fileSystem);
		}
		catch (NoSuchFileException e) {

			this.result = new StringBuilder(this.arguments + " does not exist.");
		}
		catch (NotAFileException e) {

			this.result = new StringBuilder(this.arguments + " is not a file.");
		}
	}

	private void cat(FileSystem fileSystem) throws IOException, NotAFileException, NoSuchFileException {

		File	file = new File(fileSystem.getAbsolutePath() + fileSystem.getDelimiter() + this.arguments);

		if (!file.isFile())
			throw new NotAFileException();
		if (!file.exists())
			throw new NoSuchFileException();
		byte[]	bytes = Files.readAllBytes(file.toPath());

		this.result = new StringBuilder(new String(bytes));
	}
}

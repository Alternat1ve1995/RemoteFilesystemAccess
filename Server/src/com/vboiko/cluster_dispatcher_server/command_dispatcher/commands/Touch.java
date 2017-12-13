package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.SuchFileAlreadyExistsException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class Touch extends Command {

	public Touch(String command, String arguments) {
		super(command, arguments);
	}

	public Touch(String command) {
		super(command);
	}

	@Override
	public void execute(FileSystem fileSystem) throws IOException {

		try {

			if (this.createFile(fileSystem)) {

				this.result = new StringBuilder(this.arguments + " has been created");
			}
			else {

				this.result = new StringBuilder("Error creating file");
			}
		}
		catch (SuchFileAlreadyExistsException e) {

			this.result = new StringBuilder("File " + this.arguments + " already exists");
		}
	}

	private boolean	createFile(FileSystem fileSystem) throws IOException, SuchFileAlreadyExistsException {

		File[]	listFiles = fileSystem.listFiles();

		for (File f : listFiles) {

			if (f.getName().equals(this.arguments))
				throw new SuchFileAlreadyExistsException();
		}

		File	newFile = new File(fileSystem.getCurrentPath(), this.arguments);

		return newFile.createNewFile();
	}
}

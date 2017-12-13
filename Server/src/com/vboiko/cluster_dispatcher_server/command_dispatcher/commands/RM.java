package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.NoSuchFileException;

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

public class RM extends Command {

	public RM(String command, String arguments) {
		super(command, arguments);
	}

	public RM(String command) {
		super(command);
	}

	@Override
	public void execute(FileSystem fileSystem) throws IOException {

		if (this.arguments == null) {

			this.result = new StringBuilder("Argument expected");
			return;
		}

		try {

			if (this.deleteFile(fileSystem)) {

				this.result = new StringBuilder(this.arguments + " has been deleted.");
			}
			else {

				this.result = new StringBuilder("Cannot delete " + this.arguments);
			}
		}
		catch (NoSuchFileException e) {

			this.result = new StringBuilder("No such file.");
		}
	}

	private boolean		deleteFile(FileSystem fileSystem) throws NoSuchFileException {

		File[]	listFiles = fileSystem.listFiles();

		for (File f : listFiles) {

			if (f.getName().equals(this.arguments)) {

				return f.delete();
			}
		}
		throw new NoSuchFileException();
	}

	private boolean		deleteDirectory(FileSystem fileSystem) {

		return false;
	}
}

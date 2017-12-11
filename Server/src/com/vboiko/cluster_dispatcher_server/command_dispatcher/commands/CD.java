package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

import java.io.File;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represents cd command
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class CD extends Command {

	public CD(String command, String flags) {
		super(command, flags);
	}

	@Override
	public void execute(FileSystem fileSystem) {

		if (this.arguments == null) {

			fileSystem.setCurrentPath(new File("/"));
		}
		else if (this.arguments.equals("..")) {

			fileSystem.setCurrentPath(fileSystem.getCurrentPath().getParentFile());
		}
		else {

			File[]	listFiles = fileSystem.listFiles();

			for (File file : listFiles) {

				if (file.getName().equals(this.arguments)) {
					if (file.isDirectory()) {
						fileSystem.setCurrentPath(file);
						return;
					}
				}
			}
			this.result.replace(0, this.result.length(), "Unknown command");
		}
	}
}

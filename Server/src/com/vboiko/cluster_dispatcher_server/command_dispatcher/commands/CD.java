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

	public CD(String command) {
		super(command);
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

			File[]	listFiles = fileSystem.getCurrentPath().listFiles();

			for (File file : listFiles) {

				System.out.println(file.getName());
				if (file.getName().equals(this.arguments)) {
					if (file.isDirectory()) {
						fileSystem.setCurrentPath(file);
						return;
					}
				}
			}
			this.result = "No such directory";
		}
	}
}

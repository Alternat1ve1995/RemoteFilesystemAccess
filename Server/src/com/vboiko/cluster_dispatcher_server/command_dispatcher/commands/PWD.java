package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

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

public class PWD extends Command {

	public PWD(String command, String flags) {
		super(command, flags);
	}

	public PWD(String command) {
		super(command);
	}

	@Override
	public void execute(FileSystem fileSystem) {

		this.result = fileSystem.getCurrentPath().getAbsolutePath();
	}
}

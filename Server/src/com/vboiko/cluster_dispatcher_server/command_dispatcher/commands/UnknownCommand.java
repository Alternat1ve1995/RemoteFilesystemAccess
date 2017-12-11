package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represents an unknown command
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class UnknownCommand extends Command {

	public UnknownCommand(String command, String flags) {
		super(command, flags);
	}

	public UnknownCommand(String command) {
		super(command);
	}

	@Override
	public void execute(FileSystem fileSystem) {

		this.result = "Unknown command";
	}
}

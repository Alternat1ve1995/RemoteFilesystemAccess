package com.vboiko.cluster_dispatcher_server.command_dispatcher;

import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Implementation of {@link CommandDispatcher} interface
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class CommandDispatcherImpl implements CommandDispatcher {

	private FileSystem	fileSystem;

	public CommandDispatcherImpl(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	@Override
	public String execute(String command) throws InterruptedException {

		Command		executable;

		if (command.matches("[a-z]+ -[a-z]+")) {

			String[] input = command.split(" -");
			executable = new Command(input[0], input[1]);
		}
		else {

			executable = new Command(command);
		}
		this.fileSystem.executeCommand(executable);
		return (executable.toString());
	}
}

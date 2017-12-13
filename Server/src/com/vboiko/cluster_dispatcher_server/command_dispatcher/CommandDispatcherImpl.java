package com.vboiko.cluster_dispatcher_server.command_dispatcher;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.commands.*;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import java.io.IOException;

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

		Command		executable = new UnknownCommand("unknown");
		String[]	input;

		if (command.matches("[a-z]+ -[a-zA-Z]+")) {

			input = command.split(" -");
		}
		else if (command.matches("[a-z]+ [[a-zA-Z.]\\+/]+")) {

			input = command.split(" ");
		}
		else {

			input = new String[2];
			input[0] = command;
			input[1] = null;
		}

		switch (input[0]) {

			case "pwd"		: executable = new PWD(input[0]);
				break;
			case "cd"		: executable = new CD(input[0], input[1]);
				break;
			case "ls"		: executable = new LS(input[0], input[1]);
				break;
			case "touch"	: executable = new Touch(input[0], input[1]);
				break;
			case "rm"		: executable = new RM(input[0], input[1]);
				break;
		}
		this.fileSystem.setCommand(executable);
		try {
			this.fileSystem.executeCommand();
		}
		catch (IOException e) {
			return "[ERROR]";
		}
		return (executable.toString());
	}
}

package com.vboiko.cluster_dispatcher_server.command_dispatcher;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.commands.*;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.TooManyArgumentsException;

import java.io.IOException;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.2
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

		if (command.matches("[a-z]+ -[a-zA-Z]+ [a-zA-Z._,*+=\\-]+")) {

			input = command.split(" ");
		}
		else if (command.matches("[a-z]+ [[a-zA-Z._,*+=\\-]+/]+")) {

			input = command.split(" ");
		}
		else {

			input = new String[3];
			input[0] = command;
			input[1] = null;
			input[2] = null;
		}

		try {
			
			// TODO: 19.12.2017 Refactor this
			// TODO: 12/23/17 Do it based on HashMap<String, Command>
			switch (input[0]) {
				
				case "pwd"		: executable = new PWD(input[0]);
					break;
				case "cd"		: executable = new CD(input[0], input[1]);
					break;
				case "ls"		: executable = new LS(input[0], input[1]);
					break;
				case "touch"	: executable = new Touch(input[0], input[1]);
					break;
				case "rm"		: executable = input[2] == null ? new RM(input[0], input[1]) : new RM(input[0], input[1] + " " + input[2]);
					break;
				case "mkdir"	: executable = new MKDIR(input[0], input[1]);
					break;
				case "cat"		: executable = new Cat(input[0], input[1]);
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
		catch (TooManyArgumentsException e) {
			
			return "Too many arguments";
		}
	}
}

package com.vboiko.cluster_dispatcher_server.command_dispatcher;

import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represent a command.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public abstract class Command {

	protected String	arguments;
	protected String	command;
	protected String	result;

	public Command(String command, String arguments) {

		this.arguments = arguments;
		this.command = command;
	}

	public Command(String command) {
		this.command = command;
		this.arguments = null;
	}


	public String getCommand() {
		return command;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return (this.result);
	}

	public abstract void		execute(FileSystem	fileSystem);
}

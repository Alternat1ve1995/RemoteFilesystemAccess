package com.vboiko.cluster_dispatcher_server.command_dispatcher;

import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

import java.io.IOException;

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

	protected String		arguments;
	protected String		command;
	protected StringBuilder	result;

	public Command(String command, String arguments) {

		this.arguments = arguments;
		this.command = command;
		this.result = new StringBuilder("");
	}

	public Command(String command) {
		this.command = command;
		this.arguments = null;
		this.result = new StringBuilder("");
	}


	public String getCommand() {
		return command;
	}

	public String getResult() {
		return result.toString();
	}

	public void setResult(String result) {
		this.result = new StringBuilder(result);
	}

	@Override
	public String toString() {
		return (this.result.toString());
	}

	public abstract void		execute(FileSystem	fileSystem) throws IOException;
}

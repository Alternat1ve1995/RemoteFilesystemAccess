package com.vboiko.cluster_dispatcher_server.command_dispatcher;

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

public class Command {

	private char[]		flags;
	private String		command;
	private String		result;

	public Command(String command, String flags) {

		this.flags = new char[flags.length()];

		for (int i = 0; i < flags.length(); i++)
			this.flags[i] = flags.charAt(i);
		this.command = command;
	}

	public Command(String command) {
		this.command = command;
		this.flags = null;
	}

	public char[] getFlags() {
		return flags;
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
}

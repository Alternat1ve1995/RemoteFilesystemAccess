package com.vboiko.cluster_dispatcher_server.filesystem;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Class that represents a machine filesystem.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public abstract class FileSystem implements Execution {

	protected String	delimiter;
	protected File		currentPath;
	private Command		command;
	private String		result;

	public Command	executeCommand() {

		this.command.execute(this);
		return (this.command);
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public File getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(File currentPath) {
		this.currentPath = currentPath;
	}

	public String getDelimiter() {
		return delimiter;
	}

	@Override
	public void cd(String arg) {


	}

	@Override
	public ArrayList<String> ls(String arg) {
		return null;
	}

	@Override
	public String pwd() {
		return (this.currentPath.getAbsolutePath());
	}
}

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

	protected File	currentPath;

	public Command	executeCommand(Command command) {

		switch (command.getCommand()) {

			case "pwd" : command.setResult(this.pwd());
				break;

		}
		return (command);
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

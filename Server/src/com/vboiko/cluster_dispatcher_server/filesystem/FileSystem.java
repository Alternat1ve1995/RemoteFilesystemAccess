package com.vboiko.cluster_dispatcher_server.filesystem;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Class that represents basic rules
 * of interaction with the filesystem.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class FileSystem implements Command {

	private File	currentPath;

	public FileSystem(File currentPath) {
		this.currentPath = currentPath;
	}

	public void	executeCommand(String command) {

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

package com.vboiko.cluster_dispatcher_server.filesystem;

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

public abstract class FileSystem implements Command {

	protected File	currentPath;

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

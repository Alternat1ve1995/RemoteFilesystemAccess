package com.vboiko.cluster_dispatcher_server.filesystem;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.1
 *
 * Implementation of {@link FileSystem}
 * Describes UNIX filesystem model.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class UnixFileSystem extends FileSystem {

	public UnixFileSystem() {

		this.delimiter = "/";
		this.root = "C:\\";
		this.currentPath = new File(this.root);
	}

	@Override
	public boolean isRootDir() {

		return this.currentPath.toString().equals(this.delimiter);
	}
}

package com.vboiko.cluster_dispatcher_server.filesystem;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Implementation of {@link FileSystem}
 * Describes standard UNIX filesystem.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 */
public class UnixFileSystem extends FileSystem {

	public UnixFileSystem() {

		this.delimiter = "/";
		this.currentPath = new File("/");
	}
}

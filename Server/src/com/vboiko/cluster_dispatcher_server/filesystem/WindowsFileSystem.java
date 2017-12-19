package com.vboiko.cluster_dispatcher_server.filesystem;

import java.io.File;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.1
 *
 * Implementation of {@link FileSystem}
 * Describes Windows filesystem model.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class WindowsFileSystem extends FileSystem {
	
	public WindowsFileSystem() {
		
		this.delimiter = "\\\\";
		this.root = "C:\\\\";
		this.currentPath = new File(this.root);
	}
	
	@Override
	public boolean isRootDir() {
		
		return this.currentPath.toString().equals(this.delimiter);
	}
}

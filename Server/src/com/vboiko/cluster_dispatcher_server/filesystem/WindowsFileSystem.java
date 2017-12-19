package com.vboiko.cluster_dispatcher_server.filesystem;

import java.io.File;

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

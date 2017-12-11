package com.vboiko.cluster_dispatcher_server.filesystem;

import java.util.ArrayList;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * An Interface that represents possible commands to apply on {@link FileSystem}
 *
 */
public interface Execution {

	void				cd(String arg);
	ArrayList<String>	ls(String arg);
	String				pwd();
}

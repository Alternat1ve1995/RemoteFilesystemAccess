package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represents mkdir command
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class MKDIR extends Command {
	
	public MKDIR(String command, String arguments) {
		super(command, arguments);
	}
	
	public MKDIR(String command) {
		super(command);
	}
	
	@Override
	public void execute(FileSystem fileSystem) throws IOException {
		
		File	newDir = new File(fileSystem.getCurrentPath().getAbsolutePath() + fileSystem.getDelimiter() + this.arguments);
		
		if (newDir.mkdir())
			this.result = new StringBuilder(this.arguments + " has been created");
		else
			this.result = new StringBuilder("Cannot create " + this.arguments);
	}
}

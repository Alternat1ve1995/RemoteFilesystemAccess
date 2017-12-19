package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.NoSuchFileException;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.TooManyArgumentsException;

import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class RM extends Command {
	
	private String		flags;
	private String		filename;

	public RM(String command, String arguments) throws TooManyArgumentsException {
		super(command, arguments);
		
		String[]	dta = arguments.split(" ");
		String		flags = null;
		
		if (dta.length > 2)
			throw new TooManyArgumentsException();
		for (String s : dta) {
			
			if (s.contains("-"))
				flags = s;
			else
				this.filename = s;
		}
		
		this.flags = flags;
	}

	public RM(String command) {
		super(command);
	}

	@Override
	public void execute(FileSystem fileSystem) throws IOException {

		if (this.arguments == null) {

			this.result = new StringBuilder("Argument expected");
			return;
		}

		try {

			if (this.flags == null) {
				
				if (this.deleteFile(fileSystem)) {
					
					this.result = new StringBuilder(this.arguments + " has been deleted.");
				}
				else {
					
					this.result = new StringBuilder("Cannot delete " + this.arguments);
				}
			}
			else if (this.flags.contains("r")) {
				
				if (this.deleteDirectory(fileSystem)) {
					
					this.result = new StringBuilder(this.filename + " has been deleted.");
				}
				else {
					
					this.result = new StringBuilder("Cannot delete " + this.filename);
				}
			}
		}
		catch (NoSuchFileException e) {

			this.result = new StringBuilder("No such file.");
		}
	}

	private boolean		deleteFile(FileSystem fileSystem) throws NoSuchFileException {

		File[]	listFiles = fileSystem.listFiles();

		for (File f : listFiles) {

			if (f.getName().equals(this.arguments)) {

				return f.delete();
			}
		}
		throw new NoSuchFileException();
	}

	private boolean		deleteDirectory(FileSystem fileSystem) {
		
		File	dir = new File(fileSystem.getCurrentPath().getAbsolutePath() + fileSystem.getDelimiter() + this.filename);
		return this.del(dir);
	}
	
	private boolean		del(File file) {
		
		if (!file.exists())
			return false;
		if (file.isDirectory()) {
			
			for (File f : file.listFiles())
				del(f);
			file.delete();
		}
		else {
			file.delete();
		}
		return true;
	}
}

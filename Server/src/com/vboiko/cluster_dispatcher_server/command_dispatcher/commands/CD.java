package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.NoSuchDirectoryException;
import com.vboiko.cluster_dispatcher_server.filesystem.exceptions.NotADirectoryException;

import java.io.File;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represents cd command
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public class CD extends Command {

	public CD(String command, String flags) {
		super(command, flags);
	}

	@Override
	public void execute(FileSystem fileSystem) {

		if (this.arguments == null) {

			fileSystem.setCurrentPath(new File("/"));
		}
		else {

			String[]	path = this.arguments.split(fileSystem.getDelimiter());

			for (String dir : path) {

				try {
					this.enterDirectory(dir, fileSystem);
				}
				catch (NotADirectoryException e) {

					this.result = new StringBuilder(dir + " is not a directory.");
				}
				catch (NoSuchDirectoryException e) {

					this.result = new StringBuilder(dir + " does not exist.");
				}
			}
		}
	}

	private void	enterDirectory(String name, FileSystem fileSystem) throws NoSuchDirectoryException, NotADirectoryException {

		String	pathname = fileSystem.getCurrentPath().toString().endsWith("/") ?
				fileSystem.getCurrentPath().getAbsolutePath() + name :
				fileSystem.getCurrentPath().getAbsolutePath() + fileSystem.getDelimiter() + name;

		File	dir = new File(pathname);

		if (fileSystem.getCurrentPath().getName().equals(fileSystem.getDelimiter()))
			throw new NoSuchDirectoryException();

		if (name.equals("..")) {

			if (!fileSystem.isRootDir())
				fileSystem.setCurrentPath(fileSystem.getCurrentPath().getParentFile());
			else
				throw new NoSuchDirectoryException();
		}
		else if (dir.exists()) {

			if (dir.isDirectory()) {

				fileSystem.setCurrentPath(dir);
			}
			else {

				throw new NotADirectoryException();
			}
		}
		else {

			throw new NoSuchDirectoryException();
		}
	}
}

package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represents an ls command
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 */

public class LS extends Command {

	public LS(String command, String arguments) {
		super(command, arguments);
	}

	public LS(String command) {
		super(command);
	}

	@Override
	public void		execute(FileSystem fileSystem) {

		File[]	listFiles = fileSystem.listFiles();

		if (this.arguments == null) {

			for (File file : listFiles) {

				this.result.append(file.getName()).append('\n');
			}
		}
		else {

			ArrayList<String>	info = new Arguments(listFiles).getInfo(arguments);
			for (String fileInfo : info) {

				this.result.append(fileInfo).append('\n');
			}
		}
	}

	private void	parseArgs() {


	}

	class Arguments {

		ArrayList<String>	list;
		File[]				listFiles;

		private Arguments(File[] files) {

			this.list = new ArrayList<>();
			this.listFiles = files;
		}

		private ArrayList<String>	getInfo(String args) {

			for (File f : listFiles) {

				StringBuilder	sb = new StringBuilder();
				if (args.contains("l")) {

				}
			}

			return this.list;
		}
	}
}

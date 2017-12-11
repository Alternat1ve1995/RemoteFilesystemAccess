package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
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
	public void		execute(FileSystem fileSystem) throws IOException {

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

	class Arguments {

		ArrayList<String>	list;
		File[]				listFiles;

		private Arguments(File[] files) {

			this.list = new ArrayList<>();
			this.listFiles = files;
		}

		private ArrayList<String>	getInfo(String args) throws IOException {

			for (int i = 0; i < this.listFiles.length; i++) {

				StringBuilder	sb = new StringBuilder("");
				String			fileName = this.listFiles[i].getName();

				if (fileName.charAt(0) == '.' && !args.contains("a"))
					continue;
				sb.append(fileName);
				if (args.contains("l")) {

					// TODO: 12/11/17 Create formatting
					BasicFileAttributes	attrs = Files.readAttributes(this.listFiles[i].toPath(), BasicFileAttributes.class);
					sb.insert(0, attrs.creationTime().toString() + " ");
					if (this.listFiles[i].canRead()) {

						if (this.listFiles[i].isDirectory())
							sb.insert(0, (this.listFiles[i].list().length + " "));
						else
							sb.insert(0, "1 ");
					}
					char	inf;
					inf = this.listFiles[i].canExecute() ? 'x' : '-';
					sb.insert(0, inf + " ");
					inf = this.listFiles[i].canWrite() ? 'w' : '-';
					sb.insert(0, inf);
					inf = this.listFiles[i].canRead() ? 'r' : '-';
					sb.insert(0, inf);
				}
				this.list.add(sb.toString());
			}

			return this.list;
		}
	}
}

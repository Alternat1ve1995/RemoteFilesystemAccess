package com.vboiko.cluster_dispatcher_server.command_dispatcher.commands;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.Command;
import com.vboiko.cluster_dispatcher_server.filesystem.FileSystem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that represents an ls command
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
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
				if (!this.listFiles[i].canRead())
					continue;
				sb.append(fileName);
				if (args.contains("l")) {

					BasicFileAttributes	attrs = Files.readAttributes(this.listFiles[i].toPath(), BasicFileAttributes.class);
					Formatter.format(attrs.creationTime().toString());
					sb.insert(0, Formatter.format(attrs.creationTime().toString()) + " ");
					if (this.listFiles[i].canRead()) {

						int	len = this.listFiles[i].isDirectory() ? this.listFiles[i].list().length : 1;
						String	tmp = String.format("%4d ", len);
						sb.insert(0, tmp);
					}
					char	inf;
					inf = this.listFiles[i].canExecute() ? 'x' : '-';
					sb.insert(0, inf + " ");
					inf = this.listFiles[i].canWrite() ? 'w' : '-';
					sb.insert(0, inf);
					inf = this.listFiles[i].canRead() ? 'r' : '-';
					sb.insert(0, inf);
					inf = this.listFiles[i].isDirectory() ? 'd' : '-';
					sb.insert(0, inf);
				}
				this.list.add(sb.toString());
			}

			return this.list;
		}
	}


	// TODO: 12/11/17 Temporary solution (refactor this)
	static class Formatter {

		private static HashMap<String, String> map = new HashMap<>();

		static {
			map.put("01", "Jan");
			map.put("02", "Feb");
			map.put("03", "Mar");
			map.put("04", "Apr");
			map.put("05", "May");
			map.put("06", "Jun");
			map.put("07", "Jul");
			map.put("08", "Aug");
			map.put("09", "Sep");
			map.put("10", "Oct");
			map.put("11", "Nov");
			map.put("12", "Dec");
		}

		public static String	format(String time) {

			String	format = "";

			format += Formatter.map.get(time.substring(time.indexOf('-') + 1, time.length()).substring(0, 2));
			format += " ";
			format += time.substring(time.lastIndexOf('-') + 1, time.lastIndexOf('-') + 3);
			format += " ";
			format += time.substring(0, time.indexOf('-'));

			return (format);
		}
	}
}

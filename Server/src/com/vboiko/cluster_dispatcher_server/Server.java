package com.vboiko.cluster_dispatcher_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * The main class that starts the Server program
 * and represents the logic of reading commands from Dispatcher.
 *
 * Main class: {@link Server}
 *
 */

public class Server {

	public static void main(String[] args) throws IOException {

		Runtime				runtime = Runtime.getRuntime();
		ServerSocket		serverSocket;
		Scanner				scanner = new Scanner(System.in);

		new Thread(() -> {

			while (true) {

				if (scanner.hasNext())
					if (scanner.nextLine().equals("exit"))
						System.exit(0);
			}
		}).start();

		while (true) {

			serverSocket = new ServerSocket(8090);
			Socket				server = serverSocket.accept();

			System.out.println("Someone connected...");

			InputStream			serverInputStream = server.getInputStream();
			OutputStream		serverOutputStream = server.getOutputStream();

			DataInputStream		in = new DataInputStream(serverInputStream);
			DataOutputStream 	out = new DataOutputStream(serverOutputStream);

			while (true) {

				try
				{
					Process			process;
					String			command = in.readUTF();
					StringBuilder	response = new StringBuilder("");
					if (command.equals("disconnect")) {

						System.out.println("Client disconnected...");
						server.close();
						in.close();
						out.close();
						serverSocket.close();
						break;
					}
					try {
						process = runtime.exec(command);
						process.waitFor();
					}
					catch (InterruptedException e) {

						System.out.println("Process has been interrupted... ");
						continue;
					}
					Scanner		processScanner = new Scanner(process.getInputStream());
					while (processScanner.hasNext())
						response.append(processScanner.nextLine()).append('\n');
					out.writeUTF(response.toString());
				}
				catch (IOException e) {

					continue;
				}
			}
		}
	}
}

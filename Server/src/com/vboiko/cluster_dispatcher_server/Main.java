package com.vboiko.cluster_dispatcher_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

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
					String	command = in.readUTF();
					String	response = "";
					if (command.equals("disconnect")) {

						System.out.println("Client disconnected...");
						server.close();
						in.close();
						out.close();
						serverSocket.close();
						break;
					}
					Process		process = runtime.exec(command);
					Scanner		processScanner = new Scanner(process.getInputStream());
					while (processScanner.hasNext())
						response += processScanner.nextLine() + '\n';
					out.writeUTF(response);
				}
				catch (IOException e) {
					continue;
				}
			}
		}
	}
}

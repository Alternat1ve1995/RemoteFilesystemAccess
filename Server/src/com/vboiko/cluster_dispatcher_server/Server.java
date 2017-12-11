package com.vboiko.cluster_dispatcher_server;

import com.vboiko.cluster_dispatcher_server.command_dispatcher.CommandDispatcher;
import com.vboiko.cluster_dispatcher_server.command_dispatcher.CommandDispatcherImpl;
import com.vboiko.cluster_dispatcher_server.filesystem.*;
import com.vboiko.cluster_dispatcher_server.heartbeat.Heartbeat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.1
 *
 * The main class that starts the Server program
 * and represents the logic of reading commands from Dispatcher.
 *
 * Main class: {@link Server}
 *
 */

public class Server {

	private ServerSocket	serverSocket;
	private Socket			server;
	private Runtime			runtime;
	private boolean			connected;
	InputStream				serverInputStream;
	OutputStream			serverOutputStream;
	CommandDispatcher		commandDispatcher;

	public Server() throws IOException {

		this.runtime = Runtime.getRuntime();
		this.commandDispatcher = new CommandDispatcherImpl(new UnixFileSystem());
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	private void 		serverRuntime() throws IOException {

//		Heartbeat	heartbeat = Heartbeat.getInstance(this);
//		heartbeat.start();

		while (true) {

			this.openConnection();
			System.out.println("Someone connected...");

			DataInputStream		in = new DataInputStream(this.serverInputStream);
			DataOutputStream 	out = new DataOutputStream(this.serverOutputStream);

			while (true) {

				try
				{
					Process			process;
					String			command = in.readUTF();
					String			response = "";
					if (command.equals("disconnect")) {

						System.out.println("Client disconnected...");
						this.closeConnection();
						break;
					}
					try {
						response = this.commandDispatcher.execute(command);
						if (response == null)
							response = "";
					}
					catch (InterruptedException e) {

						System.out.println("Process has been interrupted... ");
						continue;
					}
					out.writeUTF(response);
				}
				catch (IOException e) {

					continue;
				}
			}
		}
	}

	private void		closeConnection() throws IOException {

		this.server.close();
		this.serverOutputStream.close();
		this.serverInputStream.close();
		this.serverSocket.close();
		System.out.println("Connection closed");
	}

	private void 		openConnection() throws IOException {

		this.serverSocket = new ServerSocket(8090);
		this.server = this.serverSocket.accept();
		serverInputStream = this.server.getInputStream();
		serverOutputStream = this.server.getOutputStream();
		System.out.println("Connection opened at port " + this.serverSocket.getLocalPort());
	}

	public boolean		heartbeat() throws IOException, InterruptedException {

		this.serverOutputStream.write(new byte[]{4, 2});
		Thread.sleep(1000);
		byte[]	response = new byte[2];
		if (this.serverInputStream.read(response) == 2) {
			if (response[0] == 4 && response[1] == 2) {

				System.out.println("client is alive");
				return (true);
			}
		}
		return (false);
	}

	public void 		disconnect() throws IOException {

		this.server.close();
		this.serverInputStream.close();
		this.serverOutputStream.close();
		this.serverSocket.close();
	}

	public static void 	main(String[] args) throws IOException {

		Scanner				scanner = new Scanner(System.in);

		new Thread(() -> {

			while (true) {

				if (scanner.hasNext())
					if (scanner.nextLine().equals("exit"))
						System.exit(0);
			}
		}).start();
		Server	server = new Server();
		server.serverRuntime();
		// TODO: 12/11/17 Do heartbeat
	}
}

package com.vboiko.cluster_dispatcher;

import com.vboiko.cluster_dispatcher.clusters.Cluster;
import com.vboiko.cluster_dispatcher.clusters.Heartbeat;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.1
 *
 * The main class that starts the Dispatcher program
 * and represents the logic of communication with Server.
 *
 * Main class: {@link Dispatcher}
 *
 */

public class Dispatcher {

	private int				serverPort;
	private InputStream		serverInputStream;
	private OutputStream	serverOutputStream;
	private InetAddress		inetAddress;
	private Socket			socket = null;

	public Dispatcher(int serverPort) {
		this.serverPort = serverPort;
	}

	private void		dispatcherRuntime() throws IOException {

		Cluster				cluster;
		String				ipAddress;
		DataInputStream		in;
		DataOutputStream	out;

		// TODO: 12/11/17 Fix bug with error command typing (Dispatcher lags forever)

//		Heartbeat	heartbeat = Heartbeat.getInstance(this);
//		heartbeat.start();

		while (true) {

			System.out.print("Dispatcher -> ");
			try {
				System.out.println("Enter machine name to connect:");
				System.out.print("Dispatcher -> ");
				String	command = new Scanner(System.in).nextLine();
				if (command.equals("exit")) {

					this.disconnect();
					System.out.println("exiting...");
					System.exit(0);
				}

				try {
					cluster = Cluster.getCluster(command);
				}
				catch (Exception e) {

					System.out.println("[ERROR] Cannot connect to " + command + ", name might be invalid\n");
					continue;
				}
				assert cluster != null;
				ipAddress = cluster.getIp();
				this.inetAddress = InetAddress.getByName(ipAddress);
				this.socket = new Socket(this.inetAddress, this.serverPort);
				break;
			}
			catch (ConnectException e) {

				System.out.println("[ERROR] Connection refused\n");
			}
			catch (NullPointerException e) {

				System.out.println("[ERROR] Could not connect to server\n");
				break;
			}
		}

		try {

			assert this.socket != null;
			this.serverInputStream = this.socket.getInputStream();
			this.serverOutputStream = this.socket.getOutputStream();
			in = new DataInputStream(this.serverInputStream);
			out = new DataOutputStream(this.serverOutputStream);
			System.out.println("Connection successful!\n");
		}
		catch (Exception e) {

			System.out.println("[ERROR] Could not connect to server\n");
			return;
		}
		Scanner				scanner = new Scanner(System.in);

		while (true) {

			System.out.print("Dispatcher -> ");
			String	command = scanner.nextLine();
			String	response;

			if (command.equals("disconnect")) {

				this.disconnect();
				break;
			}
			else if (command.equals("exit")) {

				this.disconnect();
				System.out.println("exiting...");
				System.exit(0);
			}
			try {
				out.writeUTF(command);
				out.flush();
				response = in.readUTF();
				System.out.println(response);
			}
			catch (SocketException | EOFException e) {

				System.out.println("[ERROR] Disconnected from server\n");
				this.socket.close();
				in.close();
				out.close();
				break;
			}
		}
	}

	private void		disconnect() throws IOException {

		new DataOutputStream(this.serverOutputStream).writeUTF("disconnect");
		this.socket.close();
		this.serverOutputStream.close();
		this.serverInputStream.close();
		System.out.println("Disconnected from server...\n");
	}

	@Override
	protected void		finalize() throws Throwable {
		super.finalize();
		this.disconnect();
	}

	public static void	main(String[] args) throws Exception {

		Scanner		serverInfoScanner = new Scanner(System.in);
		Dispatcher	dispatcher;
		int			port;

		while (true) {

			while (true) {
				System.out.print("Select server port: ");
				String	portInfo = serverInfoScanner.nextLine();
				if (portInfo == null || portInfo.equals("")) {
					port = 8090;
					break;
				}
				else if (portInfo.equals("exit")) {
					System.exit(0);
				}
				else {

					try {
						port = Integer.parseInt(portInfo);
						if (port < 1 || port > 65535)
							throw new NumberFormatException("Invalid port");
						break;
					}
					catch (NumberFormatException e) {

						System.out.println("[ERROR] Invalid port. Port must be a value between 1 and 65535\n");
					}
				}
			}
			dispatcher = new Dispatcher(port);
			dispatcher.dispatcherRuntime();
		}
	}

	public InputStream getServerInputStream() {
		return serverInputStream;
	}

	public OutputStream getServerOutputStream() {
		return serverOutputStream;
	}
}

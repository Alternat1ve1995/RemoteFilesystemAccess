package com.vboiko.cluster_dispatcher;

import com.vboiko.cluster_dispatcher.clusters.Cluster;

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
 * @version 1.0
 *
 * The main class that starts the Dispatcher program
 * and represents the logic of communication with Server.
 *
 * Main class: {@link Dispatcher}
 *
 */

public class Dispatcher {

	public static void main(String[] args) throws Exception {

		int		serverPort = 8090;

		Cluster				cluster;
		String				ipAddress;
		InetAddress			inetAddress;
		Socket				socket = null;
		InputStream			serverInputStream;
		OutputStream		serverOutputStream;
		DataInputStream		in;
		DataOutputStream	out;

		while (true) {

			while (true) {

				System.out.print("Dispatcher -> ");
				try {
					System.out.println("Enter machine name to connect:");
					System.out.print("Dispatcher -> ");
					String	command = new Scanner(System.in).nextLine();
					if (command.equals("exit")) {

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
					inetAddress = InetAddress.getByName(ipAddress);
					socket = new Socket(inetAddress, serverPort);
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

				assert socket != null;
				serverInputStream = socket.getInputStream();
				serverOutputStream = socket.getOutputStream();
				in = new DataInputStream(serverInputStream);
				out = new DataOutputStream(serverOutputStream);

				System.out.println("Connection successful!\n");
			}
			catch (Exception e) {

				System.out.println("[ERROR] Could not connect to server\n");
				continue;
			}
			Scanner				scanner = new Scanner(System.in);

			while (true) {

				System.out.print("Dispatcher -> ");
				String	command = scanner.nextLine();
				String	response;

				if (command.equals("disconnect")) {

					out.writeUTF(command);
					socket.close();
					in.close();
					out.close();
					System.out.println("Disconnected from server...\n");
					break;
				}
				else if (command.equals("exit")) {

					socket.close();
					in.close();
					out.close();
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
					socket.close();
					in.close();
					out.close();
					break;
				}
			}
		}
	}
}

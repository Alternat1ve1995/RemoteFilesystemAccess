package com.vboiko.cluster_dispatcher_server.heartbeat;

import com.vboiko.cluster_dispatcher_server.Server;

import java.io.IOException;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that periodically checks client's heartbeat.
 *
 * Main class: {@link Server}
 *
 */

public class Heartbeat extends Thread {

	private Server				server;
	private static Heartbeat	heartbeat = null;

	private Heartbeat(Server server) {

		this.server = server;
	}

	public static synchronized Heartbeat	getInstance(Server server) {

		if (Heartbeat.heartbeat == null) {
			Heartbeat.heartbeat = new Heartbeat(server);
		}
		return (Heartbeat.heartbeat);
	}

	public boolean			heartbeat() {

		try {
			return server.heartbeat();
		}
		catch (IOException | InterruptedException e) {
			return (false);
		}
	}

	@Override
	public void run() {
		super.run();

		while (!this.isInterrupted()) {

			try {
				Thread.sleep(10000);
			}
			catch (InterruptedException e) {
				this.interrupt();
			}
			if (!this.heartbeat()) {

				try {
					server.disconnect();
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
	}
}

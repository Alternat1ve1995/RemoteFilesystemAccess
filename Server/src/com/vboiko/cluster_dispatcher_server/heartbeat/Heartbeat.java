package com.vboiko.cluster_dispatcher_server.heartbeat;

import com.vboiko.cluster_dispatcher_server.Server;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that periodically checks client's heartbeat.
 *
 */
public class Heartbeat extends Thread {

	private Server	server;

	public Heartbeat(Server server) {
		this.server = server;
	}

	public boolean	heartbeat() {

		return (false);
	}
}

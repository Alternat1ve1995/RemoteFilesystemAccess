package com.vboiko.cluster_dispatcher.clusters;

import com.vboiko.cluster_dispatcher.Dispatcher;

import java.io.IOException;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * A class that answers for Server heartbeat checks.
 *
 * Main class: {@link Dispatcher}
 *
 */

public class Heartbeat extends Thread {


	private Dispatcher			dispatcher;
	private static Heartbeat	heartbeat = null;

	private Heartbeat(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public static synchronized Heartbeat		getInstance(Dispatcher dispatcher) {

		if (Heartbeat.heartbeat == null) {

			Heartbeat.heartbeat = new Heartbeat(dispatcher);
		}
		return (Heartbeat.heartbeat);
	}

	public void 	heartbeat() {

		byte[]	heartbeat = new byte[2];

		try {
			if (dispatcher.getServerInputStream().read(heartbeat) == 2)
				if (heartbeat[0] == 4 && heartbeat[1] == 2)
					dispatcher.getServerOutputStream().write(new byte[]{4, 2});
		}
		catch (IOException e) {

		}
	}

	@Override
	public void run() {
		super.run();

		while (dispatcher.getServerInputStream() == null || dispatcher.getServerOutputStream() == null) {
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {

				System.out.println(e.toString());
			}
		}
		while (!this.isInterrupted()) {
			this.heartbeat();
		}
	}
}

package com.vboiko.cluster_dispatcher.clusters;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Extension of {@link Cluster} class
 * that represents the e2 cluster machines.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher.Dispatcher}
 *
 */

class E2 extends Cluster {

	E2(String name) {
		super(name);
		this.setIp();
	}
}

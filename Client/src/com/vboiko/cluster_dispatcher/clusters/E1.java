package com.vboiko.cluster_dispatcher.clusters;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Extension of {@link Cluster} class
 * that represents the e1 cluster machines.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher.Dispatcher}
 *
 */

class E1 extends Cluster {

	E1(String name) {
		super(name);
		this.setIp();
	}
}

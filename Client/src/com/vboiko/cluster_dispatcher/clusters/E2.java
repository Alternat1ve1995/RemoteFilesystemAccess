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
 */

public class E2 extends Cluster {

	public E2(String name) {
		super(name);
		this.setIp();
	}
}

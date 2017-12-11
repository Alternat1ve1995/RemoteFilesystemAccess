package com.vboiko.cluster_dispatcher.clusters;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Extension of {@link Cluster} class
 * that represents the e3 cluster machines.
 *
 */

public class E3 extends Cluster {

	public E3(String name) {
		super(name);
		this.setIp();
	}
}

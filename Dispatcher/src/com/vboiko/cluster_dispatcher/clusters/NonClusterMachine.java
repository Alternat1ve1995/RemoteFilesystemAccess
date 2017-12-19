package com.vboiko.cluster_dispatcher.clusters;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * Extension of {@link Cluster} class
 * that represents any remote machine.
 *
 * Main class: {@link com.vboiko.cluster_dispatcher.Dispatcher}
 *
 */

public class NonClusterMachine extends Cluster {
	
	public NonClusterMachine(String name) {
		
		super();
		this.setIp(name);
	}
}

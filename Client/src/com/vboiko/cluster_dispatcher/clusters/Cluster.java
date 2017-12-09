package com.vboiko.cluster_dispatcher.clusters;


public abstract class Cluster {

	protected String	e;
	protected String	r;
	protected String	p;
	protected String	ip;

	public Cluster(String name) {

		this.e = name.substring(1, name.indexOf('r'));
		this.r = name.substring(name.indexOf('r') + 1, name.indexOf('p'));
		this.p = name.substring(name.indexOf('p') + 1);
		this.ip = null;
	}

	public static Cluster	getCluster(String name) {

		if (name.contains("e1"))
			return (new E1(name));
		else if (name.contains("e2"))
			return (new E2(name));
		else if (name.contains("e3"))
			return (new E3(name));
		return (null);
	}

	protected void setIp() {

		if (this.ip == null) {

			this.ip = "10.11" + this.e + "." + this.r + "." + this.p;
		}
	}

	public String getIp() {

		return (this.ip);
	}
}

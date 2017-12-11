package com.vboiko.cluster_dispatcher_server.command_dispatcher;

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 * An interface that represents ways of
 * interaction with commands from Dispatcher
 *
 * Main class: {@link com.vboiko.cluster_dispatcher_server.Server}
 *
 */

public interface CommandDispatcher {

	void	execute(String command);
}

package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.processing_network.Network;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.hal.HAL;

public abstract class NetworkRobot extends RobotBase {

	// TODO add some stuff to deal with the logger.
	@Override
	public void startCompetition() {

		Network network = new Network(10000);

		this.setupNetwork(network);

		network.start();

		HAL.observeUserProgramStarting();
	}

	/**
	 * Adds all of the Nodes to the Network. Can create and start other Networks
	 * here as well.
	 * 
	 * @param network
	 *            The main Network to add nodes to. It has a default update rate
	 *            10,000 microseconds and will automatically be started after
	 *            this method is run.
	 */
	public abstract void setupNetwork(Network network);
}

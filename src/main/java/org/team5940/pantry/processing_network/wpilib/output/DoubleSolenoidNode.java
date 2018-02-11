package org.team5940.pantry.processing_network.wpilib.output;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.Node;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * This is used to set the values of an array of DoubleSolenoids based on the
 * value from a ValueNode. All of the DoubleSolenoids will have their value set
 * at the same time.
 * 
 * @author Michael Bentley
 */
public class DoubleSolenoidNode extends Node {

	ValueNode<? extends DoubleSolenoid.Value> solenoidValueNode;
	DoubleSolenoid[] doubleSolenoids;

	/**
	 * Creates a new DoubleSolenoidNode. This sets the value of multiple
	 * doubleSolenoids based on the desired value.
	 * 
	 * @param network
	 *            The Network.
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' label
	 * @param requireUpdate
	 *            If it requires and update every cycle.
	 * @param solenoidValueNode
	 *            The value to set DoubleSolenoids to.
	 * @param doubleSolenoids
	 *            DoubleSolenoids to set value of.
	 * @throws IllegalArgumentException
	 *             If argument is null.
	 * @throws IllegalStateException
	 *             If network is started.
	 */
	public DoubleSolenoidNode(Network network, Logger logger, String label, boolean requireUpdate,
			ValueNode<? extends DoubleSolenoid.Value> solenoidValueNode, DoubleSolenoid... doubleSolenoids)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label, requireUpdate, solenoidValueNode);

		LoggingUtils.checkArrayArguments(doubleSolenoids);

		this.solenoidValueNode = solenoidValueNode;
		this.doubleSolenoids = doubleSolenoids;
	}

	@Override
	protected void doUpdate() {
		for (DoubleSolenoid doubleSolenoid : this.doubleSolenoids) {
			doubleSolenoid.set(solenoidValueNode.getValue());
		}
	}

}

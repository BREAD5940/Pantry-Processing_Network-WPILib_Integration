package org.team5940.pantry.processing_network.wpilib.output;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.Node;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Michael Bentley
 *
 */
public class NumberSmartDashboardNode extends Node {

	/**
	 * The key to put next to the value on the SmartDashboard.
	 */
	String key;

	/**
	 * The value to log.
	 */
	ValueNode<? extends Number> valueNode;

	/**
	 * Puts the value of a number ValueNode onto the SmartDashboard with specified
	 * key.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' label
	 * @param requireUpdate
	 *            If the Node should be updated.
	 * @param key
	 *            The key to put with the number. It is the text that shows up right
	 *            by the value on the SmartDashboard.
	 * @param valueNode
	 *            The ValueNode to determine what value should be logged.
	 */
	public NumberSmartDashboardNode(Network network, Logger logger, String label, boolean requireUpdate, String key,
			ValueNode<? extends Number> valueNode) throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label, requireUpdate);

		this.key = key;

		this.valueNode = valueNode;
	}

	@Override
	protected void doUpdate() {
		SmartDashboard.putNumber(key, valueNode.getValue().doubleValue());
	}

}

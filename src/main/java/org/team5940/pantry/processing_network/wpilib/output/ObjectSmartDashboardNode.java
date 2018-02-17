package org.team5940.pantry.processing_network.wpilib.output;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.Node;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This displays a value on the SmartDashboard using
 * {@link SmartDashboard#putString(String, String)} with the Object being
 * converted to a string using toString().
 * 
 * @author Michael Bentley
 *
 */
public class ObjectSmartDashboardNode extends Node {

	/**
	 * The ValueNode that describes the output to display.
	 */
	ValueNode<? extends Object> objectValueNode;

	/**
	 * The key that is used to describe the output in
	 * {@link SmartDashboard#putString(String, String)}.
	 */
	String key;

	/**
	 * Creates a new {@link ObjectSmartDashboardNode}
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This Logger.
	 * @param label
	 *            This label.
	 * @param requireUpdate
	 *            If this Node should be updated every cycle.
	 * @param key
	 *            The String used to describe the output on the Smartdashboard.
	 * @param objectValueNode
	 *            The ValueNode that determines the value to display.
	 */
	public ObjectSmartDashboardNode(Network network, Logger logger, String label, boolean requireUpdate, String key,
			ValueNode<? extends Object> objectValueNode) {
		super(network, logger, label, requireUpdate, objectValueNode);

		LoggingUtils.checkArgument(key);

		this.key = key;
		this.objectValueNode = objectValueNode;
	}

	@Override
	protected void doUpdate() {
		SmartDashboard.putString(this.key, this.objectValueNode.getValue().toString());
	}

}

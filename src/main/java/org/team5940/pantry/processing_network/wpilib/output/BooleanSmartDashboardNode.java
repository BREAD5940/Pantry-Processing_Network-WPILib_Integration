package org.team5940.pantry.processing_network.wpilib.output;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.Node;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This displays a value of a boolean ValueNode on the SmartDashboard with a
 * specified key.
 * 
 * @author Michael Bentley
 *
 */
public class BooleanSmartDashboardNode extends Node {

	/**
	 * The key that displays the SmartDashboard.
	 */
	String key;

	/**
	 * The ValueNode that determines the value to display.
	 */
	ValueNode<? extends Boolean> source;

	/**
	 * Creates a new {@link BooleanSmartDashboardNode}.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' Label
	 * @param requireUpdate
	 *            If this Node should be updated every cycle.
	 * @param key
	 *            The key that describes the value being displayed for the
	 *            SmartDashboard.
	 * @param source
	 *            The ValueNode that determines the value to display.
	 */
	public BooleanSmartDashboardNode(Network network, Logger logger, String label, boolean requireUpdate, String key,
			ValueNode<? extends Boolean> source) {
		super(network, logger, label, requireUpdate, source);

		LoggingUtils.checkArgument(key);

		this.source = source;
		this.key = key;
	}

	@Override
	protected void doUpdate() {
		SmartDashboard.putBoolean(this.key, this.source.getValue());
	}

}

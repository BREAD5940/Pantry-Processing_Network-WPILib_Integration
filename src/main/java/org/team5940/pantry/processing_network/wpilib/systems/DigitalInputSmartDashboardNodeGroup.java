package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.wpilib.input.DigitalInputValueNode;
import org.team5940.pantry.processing_network.wpilib.output.BooleanSmartDashboardNode;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This displays the value of a DigitalInput on the SmartDashboard.
 * 
 * @author Michael Bentley
 *
 */
public class DigitalInputSmartDashboardNodeGroup extends NodeGroup {

	/**
	 * Creates a new {@link DigitalInputValueNode} with a set key.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This' Logger.
	 * @param label
	 *            This' Label.
	 * @param digitalInput
	 *            The DigitalInput which determines the value to display.
	 * @param inverted
	 *            If the DigitalInput value is inverted.
	 * @param key
	 *            The key for the value on the SmartDashboard.
	 */
	public DigitalInputSmartDashboardNodeGroup(Network network, Logger logger, String label, DigitalInput digitalInput,
			boolean inverted, String key) {
		super(network, logger, label);

		LoggingUtils.checkArgument(digitalInput);
		LoggingUtils.checkArgument(key);

		DigitalInputValueNode digitalInputValueNode = new DigitalInputValueNode(network, logger,
				label + ": Digital Input", digitalInput, inverted);

		new BooleanSmartDashboardNode(network, logger, label + ": SmartDashboard", true, key, digitalInputValueNode);
	}

}

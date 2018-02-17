package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.wpilib.input.DigitalInputValueNode;
import org.team5940.pantry.processing_network.wpilib.output.BooleanSmartDashboardNode;

import edu.wpi.first.wpilibj.DigitalInput;

public class DigitalInputSmartDashboardNodeGroup extends NodeGroup {

	public DigitalInputSmartDashboardNodeGroup(Network network, Logger logger, String label, DigitalInput input) {
		super(network, logger, label);
		DigitalInputValueNode inputNode = new DigitalInputValueNode(network, logger, label, input);
		new BooleanSmartDashboardNode(network, logger, label, true, "Digital Input Value", inputNode);
	}

}
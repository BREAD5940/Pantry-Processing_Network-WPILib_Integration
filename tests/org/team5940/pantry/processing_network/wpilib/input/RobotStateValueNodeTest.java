package org.team5940.pantry.processing_network.wpilib.input;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.wpilib.input.RobotStateValueNode.RobotState;

public class RobotStateValueNodeTest {

	@Test(expected = IllegalArgumentException.class)
	public void robotStateValueNode_NullRobot_IllegalArgumentException() {
		Network network = new Network(2000);

		new RobotStateValueNode(network, null);
	}
}

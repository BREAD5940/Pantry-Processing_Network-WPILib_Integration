package org.team5940.pantry.processing_network.wpilib.input;

import org.junit.Test;
import org.team5940.pantry.processing_network.FullSystemTest;
import org.team5940.pantry.processing_network.Network;

public class RobotStateValueNodeTest {

	@Test(expected = IllegalArgumentException.class)
	public void robotStateValueNode_NullRobot_IllegalArgumentException() {
		Network network = new Network(2000, FullSystemTest.defaultLogger);

		new RobotStateValueNode(network, FullSystemTest.defaultLogger, null);
	}
}

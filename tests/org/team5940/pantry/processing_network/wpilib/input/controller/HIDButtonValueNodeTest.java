package org.team5940.pantry.processing_network.wpilib.input.controller;

import org.junit.Test;
import org.team5940.pantry.processing_network.FullSystemTest;
import org.team5940.pantry.processing_network.Network;

public class HIDButtonValueNodeTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void HIDButtonValueNode_NullGenericHID() {
		Network network = new Network(2000, FullSystemTest.defaultLogger);
		new HIDButtonValueNode(network, null, null, 2);
//		new HIDButtonValueNode(network, FullSystemTest.defaultLogger, null, 2);
	}
}

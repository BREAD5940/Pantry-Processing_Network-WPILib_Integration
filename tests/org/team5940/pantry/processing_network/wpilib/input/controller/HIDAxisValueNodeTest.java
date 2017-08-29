package org.team5940.pantry.processing_network.wpilib.input.controller;

import org.junit.Test;
import org.team5940.pantry.processing_network.Network;

public class HIDAxisValueNodeTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void HIDAxisValueNode_NullGenericHID() {
		Network network = new Network(2000);
		new HIDAxisValueNode(network, null, 2);
	}
	
}

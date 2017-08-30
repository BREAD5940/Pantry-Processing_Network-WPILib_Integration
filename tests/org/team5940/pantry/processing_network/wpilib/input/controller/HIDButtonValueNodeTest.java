package org.team5940.pantry.processing_network.wpilib.input.controller;

import org.junit.Test;
import org.team5940.pantry.processing_network.Network;

public class HIDButtonValueNodeTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void HIDButtonValueNode_NullGenericHID() {
		Network network = new Network(2000);
		new HIDButtonValueNode(network, null, 2);
	}
}

package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;
import org.team5940.pantry.processing_network.functional.MultiplicationValueNode;

/**
 * Converts the rotation of an object to the corresponding encoder value. Should
 * be typically used to tell the TalonSRX what speed it should move at with PID.
 * 
 * This is a MultiplicationValueNode because it is easier to do the math that
 * way. This should not make a difference with usage of this ValueNode.
 * 
 * @author Michael Bentley
 *
 */
public class RotationToEncoderValueNode extends MultiplicationValueNode {

	/**
	 * Converts object rotations to encoder pulses.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param rotationsValueNode
	 *            The rotations of an object. Can be velocity or position.
	 * @param pulsesPerRotation
	 *            The encoder pulses to rotation conversion constant.
	 */
	public RotationToEncoderValueNode(Network network, Logger logger, ValueNode<? extends Number> rotationsValueNode,
			double pulsesPerRotation) {
		super(network, logger, rotationsValueNode, pulsesPerRotation);
	}

}

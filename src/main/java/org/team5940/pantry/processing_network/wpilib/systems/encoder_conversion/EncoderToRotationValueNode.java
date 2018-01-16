package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;
import org.team5940.pantry.processing_network.functional.DivisionValueNode;

/**
 * Converts the value of Encoder pulses to the rotations. This uses a
 * DivisionValueNode to do the math as it is just basic division. This node can
 * work for either position or velocity of a ValueNode although they may have
 * different pulsesPerRotation for the same encoder.
 * 
 * @author Michael Bentley
 *
 */
public class EncoderToRotationValueNode extends DivisionValueNode {

	/**
	 * Converts encoder pulses to rotation.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param encoderPulsesValueNode
	 *            The encoder ValueNode. Can be velocity or position.
	 * @param pulsesPerRotation
	 *            The encoder pulses to rotation conversion constant.
	 */
	public EncoderToRotationValueNode(Network network, Logger logger,
			ValueNode<? extends Number> encoderPulsesValueNode, double pulsesPerRotation) {
		super(network, logger, encoderPulsesValueNode, pulsesPerRotation);
	}

}

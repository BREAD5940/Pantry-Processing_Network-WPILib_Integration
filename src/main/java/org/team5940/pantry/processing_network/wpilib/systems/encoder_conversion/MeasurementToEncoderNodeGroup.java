package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * This converts a Measurement such as a distance to the corresponding encoder
 * pulses. You can retrieve the ValueNode that returns the pulse with this
 * {@link #getEncoderPulsesValueNode()}.
 * 
 * @author Michael Bentley
 *
 */
public class MeasurementToEncoderNodeGroup extends NodeGroup {

	/**
	 * The Encoder pulses ValueNode
	 */
	RotationToEncoderValueNode encoderPulsesValueNode;

	/**
	 * Creates a new {@link MeasurementToEncoderNodeGroup}
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' Label
	 * @param measurementValueNode
	 *            The measurement you would like to convert.
	 * @param diameter
	 *            The diameter of the object being rotated.
	 * @param pulsesPerRotation
	 *            The pulses per rotation of the object. Changes based on if you are
	 *            measuring velocity or position.
	 */
	public MeasurementToEncoderNodeGroup(Network network, Logger logger, String label,
			ValueNode<? extends Number> measurementValueNode, double diameter, double pulsesPerRotation) {
		super(network, logger, label);

		MeasurementToRotationValueNode rotationValueNode = new MeasurementToRotationValueNode(network, logger,
				"Measurement To Rotation Value Node: " + label, measurementValueNode, diameter);

		this.encoderPulsesValueNode = new RotationToEncoderValueNode(network, logger,
				"Rotation to Encoder Value Node: " + label, rotationValueNode, pulsesPerRotation);
	}

	/**
	 * Gets the ValueNode that determines the pulses from the measurement.
	 * 
	 * @return The ValueNode that records the pulses from the measurement.
	 */
	public RotationToEncoderValueNode getEncoderPulsesValueNode() {
		return encoderPulsesValueNode;
	}
}

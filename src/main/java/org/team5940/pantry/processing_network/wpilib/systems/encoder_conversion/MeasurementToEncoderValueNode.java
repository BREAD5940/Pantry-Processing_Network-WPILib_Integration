package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.ValueNode;

public class MeasurementToEncoderValueNode extends NodeGroup {

	RotationToEncoderValueNode encoderPulsesValueNode;

	// TODO add comments.
	public MeasurementToEncoderValueNode(Network network, Logger logger, String label,
			ValueNode<? extends Number> measurementValueNode, double diameter, double pulsesPerRotation) {
		super(network, logger, label);

		MeasurementToRotationValueNode rotationValueNode = new MeasurementToRotationValueNode(network, logger,
				"Measurement To Rotation Value Node: " + label, measurementValueNode, diameter);

		this.encoderPulsesValueNode = new RotationToEncoderValueNode(network, logger,
				"Rotation to Encoder Value Node: " + label, rotationValueNode, pulsesPerRotation);
	}

	public RotationToEncoderValueNode getEncoderPulsesValueNode() {
		return encoderPulsesValueNode;
	}
}

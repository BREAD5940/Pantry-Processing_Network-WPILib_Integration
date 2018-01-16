package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.ValueNode;

public class MeasurementToEncoderValueNode extends NodeGroup {
	
	RotationToEncoderValueNode encoderPulsesValueNode;

	public MeasurementToEncoderValueNode(Network network, Logger logger, ValueNode<? extends Number> measurementValueNode, double diameter, double pulsesPerRotation) {
		super(network, logger);
		
		MeasurementToRotationValueNode rotationValueNode = new MeasurementToRotationValueNode(network, logger, measurementValueNode, diameter);
		
		this.encoderPulsesValueNode = new RotationToEncoderValueNode(network, logger, rotationValueNode, pulsesPerRotation);
	}
	
	
	public RotationToEncoderValueNode getEncoderPulsesValueNode() {
		return encoderPulsesValueNode;
	}
}

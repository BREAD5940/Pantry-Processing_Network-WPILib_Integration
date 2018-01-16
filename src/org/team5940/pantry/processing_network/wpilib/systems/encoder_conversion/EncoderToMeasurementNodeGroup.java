package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * Converts the pulses of an encoder to a useful measurement. The measurement
 * can be any unit depending on what the encoder is measuring and the diameter
 * unit. What the encoder is measuring changes if this returns distance,
 * velocity, or acceleration. The Diameter unit changes the distance unit such
 * as feet or meters.
 * 
 * @author Michael Bentley
 *
 */
public class EncoderToMeasurementNodeGroup extends NodeGroup {

	/**
	 * The measurement of the distance, velocity, acceleration or jerk of a wheel or
	 * other cylindrical object.
	 */
	RotationToMeasurementValueNode measurmentValueNode;

	/**
	 * This converts the value of an encoder to a distance/velocity. It can be
	 * either distance or velocity depending on what encoder value node is inputed.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This' logger.
	 * @param encoderPulsesValueNode
	 *            The ValueNode for an encoder position, velocity, acceleration, or
	 *            jerk.
	 * @param diameter
	 *            The diameter of the wheel this encoder is connected to. Could be
	 *            anything cylindrical. Also this will determine the unit that will
	 *            be returned for the distance or velocity such as meters or inches
	 *            or feet.
	 * @param pulsesPerRotation
	 *            The conversion of pulses of the encoder to the rotation of the
	 *            wheel. Should be greater than one.
	 */
	public EncoderToMeasurementNodeGroup(Network network, Logger logger,
			ValueNode<? extends Number> encoderPulsesValueNode, double pulsesPerRotation, double diameter) {
		super(network, logger);

		EncoderToRotationValueNode rotationValueNode = new EncoderToRotationValueNode(network, logger,
				encoderPulsesValueNode, pulsesPerRotation);
		this.measurmentValueNode = new RotationToMeasurementValueNode(network, logger, rotationValueNode, diameter);
	}

	/**
	 * Gets the ValueNode that returns the measurement.
	 * 
	 * @return The ValueNode that returns the measurement.
	 */
	public RotationToMeasurementValueNode getMeasurementValueNode() {
		return this.measurmentValueNode;
	}

}

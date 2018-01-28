package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * Converts the rotation of the wheels to the measurment. Could either be a
 * distance or velocity depending if the rotation is rot/s or just rotations.
 * Could also be acceleration or jerk if you are feeling crazy. This also does
 * not have to work only for wheels. Could be any cylindrical object.
 * 
 * @author Michael Bentley
 *
 */
public class RotationToMeasurementValueNode extends ValueNode<Double> {

	ValueNode<? extends Number> rotationsValueNode;
	double diameter;

	/**
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' label
	 * @param rotationsValueNode
	 *            The rotations of the cylindrical object. Does not have to be just
	 *            rotations it could also be rot/s or rot/s^2 or anything past that.
	 * @param diameter
	 *            The diameter of the rotating cylindrical object.
	 */
	public RotationToMeasurementValueNode(Network network, Logger logger, String label,
			ValueNode<? extends Number> rotationsValueNode, double diameter)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label, rotationsValueNode);

		this.rotationsValueNode = rotationsValueNode;
		this.diameter = diameter;
	}

	@Override
	protected Double updateValue() {
		double circumference = diameter * Math.PI;

		double rotations = rotationsValueNode.getValue().doubleValue();

		return circumference * rotations;
	}

}

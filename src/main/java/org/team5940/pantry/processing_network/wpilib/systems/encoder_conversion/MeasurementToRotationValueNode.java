package org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * Converts the velocity or distance a cylindrical object is or has traveled to
 * the rotations it is or has traveled. So m/s would become rot/s and feet would
 * become rotations.
 * 
 * @author Michael Bentley
 *
 */
public class MeasurementToRotationValueNode extends ValueNode<Double> {

	/**
	 * The distance or velocity the wheel has or is traveling.
	 */
	ValueNode<? extends Number> measurementValueNode;

	/**
	 * The diameter of a cylindrical object. Most likely a wheel.
	 */
	double diameter;

	/**
	 * Converts a distance or velocity in a unit such as m/s to rot/s. The diameter
	 * should be in the same unit as the measurementValueNode value not including
	 * the time part. So if the value is m/s^2 then the circumerence should be in
	 * meters.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param measurementValueNode
	 *            The distance or velocity the cylindrical object has or is
	 *            traveling.
	 * @param diameter
	 *            The diameter of the rotating cylindrical object.
	 */
	public MeasurementToRotationValueNode(Network network, Logger logger,
			ValueNode<? extends Number> measurementValueNode, double diameter)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, measurementValueNode);

		this.measurementValueNode = measurementValueNode;
		this.diameter = diameter;
	}

	@Override
	protected Double updateValue() {
		double circumference = diameter * Math.PI;

		double measurement = measurementValueNode.getValue().doubleValue();

		return measurement / circumference;
	}

}

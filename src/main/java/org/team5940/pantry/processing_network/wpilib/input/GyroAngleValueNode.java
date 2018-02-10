package org.team5940.pantry.processing_network.wpilib.input;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * This returns the angle a gyro returns. Can work with any gyro that implements
 * {@link Gyro}.
 * 
 * @author Michael Bentley
 *
 */
public class GyroAngleValueNode extends ValueNode<Double> {

	/**
	 * The gryo to measure the angle of.
	 */
	Gyro gyro;

	/**
	 * Creates a new {@link GyroAngleValueNode}.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' Label
	 * @param gyro
	 *            The Gyro to measure the angle of.
	 */
	public GyroAngleValueNode(Network network, Logger logger, String label, Gyro gyro)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label);
		LoggingUtils.checkArgument(gyro);
		this.gyro = gyro;
	}

	@Override
	protected Double updateValue() {
		return this.gyro.getAngle();
	}

}

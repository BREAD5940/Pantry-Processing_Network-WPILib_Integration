package org.team5940.pantry.processing_network.wpilib.input;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * This returns the value from a GenericHID button using getRawAxis().
 *
 */
public class HIDAxisValueNode extends ValueNode<Double> {

	/**
	 * The GenericHID device that the axis value is received from.
	 */
	final GenericHID inputDevice;

	/**
	 * The port that the axis is from. This corresponds to the getRawAxis(axis) for
	 * the GenericHID.
	 */
	final int axis;

	/**
	 * If the axis is inverted.
	 */
	boolean inverted;

	/**
	 *
	 * This returns the value of the axis from the GenericHID using
	 * getRawAxis(axis).
	 * 
	 * @param network
	 *            The Network.
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' label
	 * @param inputDevice
	 *            The device that the axis is from.
	 * @param axis
	 *            The port that the axis corresponds to for getRawAxis(axis).
	 * @throws IllegalArgumentException
	 *             If the inputDevice is null.
	 */
	public HIDAxisValueNode(Network network, Logger logger, String label, GenericHID inputDevice, int axis,
			boolean inverted) throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label);
		LoggingUtils.checkArgument(inputDevice);
		this.inputDevice = inputDevice;
		this.axis = axis;
		this.inverted = inverted;
	}

	@Override
	protected Double updateValue() {
		if (this.inverted) {
			return -this.inputDevice.getRawAxis(this.axis);
		}
		return this.inputDevice.getRawAxis(this.axis);
	}
}

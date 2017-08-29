package org.team5940.pantry.processing_network.wpilib.input.controller;

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
	 * The port that the axis is from. This corresponds to the getRawAxis(axis)
	 * for the GenericHID.
	 */
	final int axis;

	/**
	 *
	 * This returns the value of the axis from the GenericHID using
	 * getRawAxis(axis).
	 * 
	 * @param network
	 *            The Network.
	 * @param inputDevice
	 *            The device that the axis is from.
	 * @param axis
	 *            The port that the axis corresponds to for getRawAxis(axis).
	 * @throws IllegalArgumentException
	 *             If the inputDevice is null.
	 */
	public HIDAxisValueNode(Network network, GenericHID inputDevice, int axis)
			throws IllegalArgumentException, IllegalStateException {
		super(network);
		if (inputDevice == null) {
			throw new IllegalArgumentException("GenericHID cannot be null");
		}
		this.inputDevice = inputDevice;
		this.axis = axis;
	}

	@Override
	protected Double updateValue() {
		return this.inputDevice.getRawAxis(this.axis);
	}
}

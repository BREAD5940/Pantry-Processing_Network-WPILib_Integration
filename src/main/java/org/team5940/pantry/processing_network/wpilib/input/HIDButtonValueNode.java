package org.team5940.pantry.processing_network.wpilib.input;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * This returns the value from a GenericHID button using getRawButton().
 *
 */
public class HIDButtonValueNode extends ValueNode<Boolean> {

	/**
	 * The GenericHID device that the button value is received from.
	 */
	GenericHID inputDevice;

	/**
	 * The port that the button is from. This corresponds to the
	 * getRawButton(buttonPort) for the GenericHID.
	 */
	int buttonPort;

	/**
	 * This returns the value of the button from the GenericHID using
	 * getRawButton(buttonPort).
	 * 
	 * @param network
	 *            The Network.
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' label
	 * @param inputDevice
	 *            The device that the button is from.
	 * @param buttonPort
	 *            The port that the button corresponds to for
	 *            getRawButton(buttonPort).
	 * @throws IllegalArgumentException
	 *             If the inputDevice is null.
	 */
	public HIDButtonValueNode(Network network, Logger logger, String label, GenericHID inputDevice, int buttonPort)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label);
		LoggingUtils.checkArgument(inputDevice);
		this.inputDevice = inputDevice;
		this.buttonPort = buttonPort;
	}

	@Override
	protected Boolean updateValue() {
		return this.inputDevice.getRawButton(this.buttonPort);
	}

}

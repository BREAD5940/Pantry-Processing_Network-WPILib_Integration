package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.wpilib.input.HIDButtonValueNode;
import org.team5940.pantry.processing_network.wpilib.output.DoubleSolenoidNode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This allows one to easily control a DoubleSolenoid with two buttons. One for
 * each state.
 * 
 * @author Michael Bentley
 *
 */
public class ShiftingNodeGroup extends NodeGroup {

	/**
	 * The ValueNode that controls the Solenoid.
	 */
	SolenoidTwoBooleanControllerValueNode solenoidController;

	/**
	 * This Node group is able to shift a DoubleSolenoid between two states with two
	 * seperate buttons.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This' Logger.
	 * @param label
	 *            This' Label.
	 * @param joystick
	 *            This' Joystick.
	 * @param shiftReverseButton
	 *            The button that sets the solenoid to {@link Value#kReverse}
	 * @param shiftForwardButton
	 *            The button that sets the solenoid to {@link Value#kForward}
	 * @param solenoid
	 *            The solenoid to set the value of.
	 * @param startingPosition
	 *            The starting position of the DoubleSolenoid.
	 */
	public ShiftingNodeGroup(Network network, Logger logger, String label, Joystick joystick, int shiftReverseButton,
			int shiftForwardButton, DoubleSolenoid solenoid, Value startingPosition) {
		super(network, logger, label);

		LoggingUtils.checkArrayArguments(joystick, solenoid, startingPosition);

		HIDButtonValueNode shiftDown = new HIDButtonValueNode(network, logger, label + ": Shift Down Button", joystick,
				shiftForwardButton);
		HIDButtonValueNode shiftUp = new HIDButtonValueNode(network, logger, label + ": Shift Up Button", joystick,
				shiftReverseButton);

		this.solenoidController = new SolenoidTwoBooleanControllerValueNode(network, logger,
				label + ": Shifter Controller", shiftUp, shiftDown, startingPosition);

		this.addNode(shiftUp);
		this.addNode(shiftDown);
		this.addNode(this.solenoidController);
		this.addNode(new DoubleSolenoidNode(network, logger, label + ": Shifting Solinode", true, solenoidController,
				solenoid));
	}

	/**
	 * Gets the ValueNode which controls the DoubleSolenoid.
	 * 
	 * @return The ValueNode that corresponds to the DoubleSolenoid.
	 */
	public SolenoidTwoBooleanControllerValueNode getSolenoidController() {
		return solenoidController;
	}
}

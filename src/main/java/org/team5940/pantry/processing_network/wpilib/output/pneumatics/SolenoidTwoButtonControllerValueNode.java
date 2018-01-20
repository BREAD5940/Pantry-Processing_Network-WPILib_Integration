package org.team5940.pantry.processing_network.wpilib.output.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;
import org.team5940.pantry.processing_network.ProcessingNetworkUtils;

/**
 * This returns a value for a Solenoid to use. This uses two separate buttons to
 * switch back and forth. Each is designated a solenoid {@linkplain Value} and
 * then when that button is activated the solenoids are set to that value.
 * 
 * @author Michael Bentley
 *
 */
public class SolenoidTwoButtonControllerValueNode extends ValueNode<DoubleSolenoid.Value> {

	/**
	 * The ValueNode that sets the value of the solenoids to kReverse when
	 * activated. When both ValueNodes are pressed this takes precedent.
	 */
	ValueNode<? extends Boolean> shiftReverseButton;

	/**
	 * The ValueNode that sets the value of the solenoids to kForward when
	 * activated.
	 */
	ValueNode<? extends Boolean> shiftForwardButton;

	/**
	 * The last set position of the Solenoid. Used so the value of the solenoid is
	 * unchanged when neither button is pressed.
	 */
	DoubleSolenoid.Value currentPosition;

	/**
	 * This is used to shift up and down with two separate buttons. The shift up and
	 * down buttons do not have to be buttons, but it is recommended for them to be
	 * buttons. They could be any value node that returns a boolean. If both buttons
	 * are pressed at once then {@linkplain Value#kReverse} takes priority.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param shiftReverseButton
	 *            When true it sets the piston's state to
	 *            {@linkplain Value#kReverse}
	 * @param shiftForwardButton
	 *            When true it sets the piston's state to
	 *            {@linkplain Value#kForward}
	 * @param startingPosition
	 *            The starting position of the piston.
	 */
	public SolenoidTwoButtonControllerValueNode(Network network, Logger logger,
			ValueNode<? extends Boolean> shiftReverseButton, ValueNode<? extends Boolean> shiftForwardButton,
			DoubleSolenoid.Value startingPosition) throws IllegalArgumentException, IllegalStateException {
		super(network, logger, shiftReverseButton, shiftForwardButton);

		ProcessingNetworkUtils.checkArgument(startingPosition);

		this.shiftReverseButton = shiftReverseButton;
		this.shiftForwardButton = shiftForwardButton;
		this.currentPosition = startingPosition;
	}

	@Override
	protected Value updateValue() {
		if (shiftReverseButton.getValue()) {
			this.currentPosition = DoubleSolenoid.Value.kReverse;
			return DoubleSolenoid.Value.kReverse;
		}
		if (shiftForwardButton.getValue()) {
			this.currentPosition = DoubleSolenoid.Value.kForward;
			return DoubleSolenoid.Value.kForward;
		}
		return currentPosition;
	}

}

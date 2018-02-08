package org.team5940.pantry.processing_network.wpilib.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * This returns a value for a Solenoid to use. This uses two separate booleans
 * to switch back and forth. Each is designated a solenoid {@linkplain Value}
 * and then when that boolean is true the solenoids are set to that value.
 * 
 * @author Michael Bentley
 *
 */
public class SolenoidTwoBooleanControllerValueNode extends ValueNode<Value> {

	/**
	 * The ValueNode that sets the value of the solenoids to kReverse when
	 * activated. When both ValueNodes are pressed this takes precedent.
	 */
	ValueNode<? extends Boolean> shiftReverseBoolean;

	/**
	 * The ValueNode that sets the value of the solenoids to kForward when
	 * activated.
	 */
	ValueNode<? extends Boolean> shiftForwardBoolean;

	/**
	 * The last set position of the Solenoid. Used so the value of the solenoid is
	 * unchanged when neither boolean is true.
	 */
	DoubleSolenoid.Value currentPosition;

	/**
	 * This is used to shift up and down with two separate boolean ValueNodes. The
	 * shift up and down booleans are typically from buttons but not always. They
	 * could be any value node that returns a boolean. If both booleans are true at
	 * once then {@linkplain Value#kReverse} takes priority.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' label.
	 * @param shiftReverseBoolean
	 *            When true it sets the piston's state to
	 *            {@linkplain Value#kReverse}
	 * 
	 * @param shiftForwardBoolean
	 *            When true it sets the piston's state to
	 *            {@linkplain Value#kForward}
	 * @param startingPosition
	 *            The starting position of the piston.
	 */
	public SolenoidTwoBooleanControllerValueNode(Network network, Logger logger, String label,
			ValueNode<? extends Boolean> shiftReverseBoolean, ValueNode<? extends Boolean> shiftForwardBoolean,
			DoubleSolenoid.Value startingPosition) throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label, shiftReverseBoolean, shiftForwardBoolean);

		LoggingUtils.checkArgument(startingPosition);

		this.shiftReverseBoolean = shiftReverseBoolean;
		this.shiftForwardBoolean = shiftForwardBoolean;
		this.currentPosition = startingPosition;
	}

	@Override
	protected Value updateValue() {
		if (shiftReverseBoolean.getValue()) {
			this.currentPosition = DoubleSolenoid.Value.kReverse;
			return DoubleSolenoid.Value.kReverse;
		}
		if (shiftForwardBoolean.getValue()) {
			this.currentPosition = DoubleSolenoid.Value.kForward;
			return DoubleSolenoid.Value.kForward;
		}
		return currentPosition;
	}

}

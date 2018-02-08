package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * This returns the current max speed of the robot based on what gear the robot
 * is currently shifted to. This will be commonly used for PID to set the speed
 * of the max robot.
 * 
 * @author Michael Bentley
 *
 */
public class MaxSpeedValueNode extends ValueNode<Double> {

	/**
	 * The current state of the high gear and low gear shifting pistons.
	 */
	ValueNode<? extends DoubleSolenoid.Value> shifterState;

	/**
	 * The max speed in low gear.
	 */
	double lowGearMaxSpeed;

	/**
	 * The max speed in high gear.
	 */
	double highGearMaxSpeed;

	/**
	 * The position of the solenoid when shifted to high gear.
	 */
	Value highGearState;

	/**
	 * This returns the speed of the robot based on the state of shifting. If the
	 * robot is in high gear this returns highGearMaxSpeed else it returns
	 * lowGearMaxSpeed. The high gear is specified by highGearState.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param shifterState
	 *            The set solenoid value for the current gear position.
	 * @param highGearState
	 *            The state the pistons are in for high gear.
	 * @param lowGearMaxSpeed
	 *            The max speed at low gear.
	 * @param highGearMaxSpeed
	 *            The max speed at high gear.
	 */
	public MaxSpeedValueNode(Network network, Logger logger, ValueNode<? extends DoubleSolenoid.Value> shifterState,
			Value highGearState, double lowGearMaxSpeed, double highGearMaxSpeed)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, "Max Robot Speed", shifterState);

		LoggingUtils.checkArgument(highGearState);

		this.highGearState = highGearState;

		this.shifterState = shifterState;

		this.lowGearMaxSpeed = lowGearMaxSpeed;
		this.highGearMaxSpeed = highGearMaxSpeed;
	}

	@Override
	protected Double updateValue() {
		if (this.shifterState.getValue().equals(this.highGearState)) {
			return this.highGearMaxSpeed;
		} else {
			return this.lowGearMaxSpeed;
		}
	}
}

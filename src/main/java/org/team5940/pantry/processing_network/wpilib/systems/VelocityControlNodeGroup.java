package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.ValueNode;
import org.team5940.pantry.processing_network.functional.DeadzoneValueNode;
import org.team5940.pantry.processing_network.functional.MultiplicationValueNode;
import org.team5940.pantry.processing_network.wpilib.input.HIDAxisValueNode;
import org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion.MeasurementToEncoderNodeGroup;
import org.team5940.pantry.processing_network.wpilib.systems.encoder_conversion.RotationToEncoderValueNode;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This is used to control the robot's drivetrain talons using PID velocity
 * control. This uses arcade drive and has a set deadzone for the joystick axis.
 * You can get the set Encoder speed with
 * {@link VelocityControlNodeGroup#getLeftEncoderValue()} and
 * {@link VelocityControlNodeGroup#getRightEncoderValue()} to use with a talon
 * Node.
 * 
 * @author Michael Bentley
 *
 */
public class VelocityControlNodeGroup extends NodeGroup {

	/**
	 * The set Pulses Per Rotation of the left talons.
	 */
	MeasurementToEncoderNodeGroup leftEncoderValue;

	/**
	 * The set Pulses Per Rotation of the right talons.
	 */
	MeasurementToEncoderNodeGroup rightEncoderValue;

	/**
	 * Can easily return the set Encoder speed of a talon based on either arcade or
	 * west coast drive joystick axis. Also has a deadzone.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This' Logger.
	 * @param label
	 *            This' Label.
	 * @param joystick
	 *            The Joystick to read the axis from.
	 * @param yawAxisIndex
	 *            The axis of the joystick that controls the yaw of the robot.
	 * @param forwardAxisIndex
	 *            The axis of the joystick that controls the forward motion of the
	 *            robot.
	 * @param yawDeadzone
	 *            The deadzone of the yaw axis.
	 * @param forwardDeadzone
	 *            The deadzone of the forward axis.
	 * @param maxSpeed
	 *            The current max speed of the robot. May change based on what gear
	 *            the robot is in.
	 * @param wheelDiameter
	 *            The wheel diameter.
	 * @param velocityPulsesPerRotation
	 *            The velocity encoder pulses to rotation value.
	 */
	public VelocityControlNodeGroup(Network network, Logger logger, String label, Joystick joystick, int yawAxisIndex,
			int forwardAxisIndex, boolean yawAxisInverted, boolean forwardAxisInverted, double yawDeadzone,
			double forwardDeadzone, ValueNode<? extends Number> maxSpeed, double wheelDiameter,
			double velocityPulsesPerRotation) {
		super(network, logger, label);

		LoggingUtils.checkArrayArguments(joystick, maxSpeed);

		HIDAxisValueNode yawAxis = new HIDAxisValueNode(network, logger, label + ": Yaw Axis", joystick, yawAxisIndex,
				yawAxisInverted);
		HIDAxisValueNode forwardAxis = new HIDAxisValueNode(network, logger, label + ": Yaw Axis", joystick,
				forwardAxisIndex, forwardAxisInverted);

		DeadzoneValueNode yawAxisDeadzone = new DeadzoneValueNode(network, logger, label + ": Yaw Deadzone", yawAxis,
				yawDeadzone);
		DeadzoneValueNode forwardAxisDeadzone = new DeadzoneValueNode(network, logger, label + ": Forward Deadzone",
				forwardAxis, forwardDeadzone);

		ArcadeDriveNodeGroup arcadeDrive = new ArcadeDriveNodeGroup(network, logger, label + ": Arcade Math",
				forwardAxisDeadzone, yawAxisDeadzone);

		MultiplicationValueNode rightSpeed = new MultiplicationValueNode(network, logger,
				label + ": Right Set Speed (ft/s)", arcadeDrive.getRightMotorValueNode(), maxSpeed);
		MultiplicationValueNode leftSpeed = new MultiplicationValueNode(network, logger,
				label + ": Left Set Speed (ft/s)", arcadeDrive.getLeftMotorValueNode(), maxSpeed);

		this.leftEncoderValue = new MeasurementToEncoderNodeGroup(network, logger,
				label + ": Left Measuremnt To Encoder Node Group", leftSpeed, wheelDiameter, velocityPulsesPerRotation);
		this.rightEncoderValue = new MeasurementToEncoderNodeGroup(network, logger,
				label + ": Right Measuremnt To Encoder Node Group", rightSpeed, wheelDiameter,
				velocityPulsesPerRotation);
	}

	/**
	 * Gets the ValueNode that determines the left talon encoder speed.
	 * 
	 * @return The ValueNode that determines the left talon encoder speed.
	 */
	public RotationToEncoderValueNode getLeftEncoderValue() {
		return leftEncoderValue.getEncoderPulsesValueNode();
	}

	/**
	 * Gets the ValueNode that determines the right talon encoder speed.
	 * 
	 * @return The ValueNode that determines the right talon encoder speed.
	 */
	public RotationToEncoderValueNode getRightEncoderValue() {
		return rightEncoderValue.getEncoderPulsesValueNode();
	}

}
package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * This ValueNode returns the speed of the specified side of a drivetrain for a
 * standard arcade drive. It uses forward and yaw axis of a joystick or
 * equivalent. This can also be used for a west coast drive if the ValueNodes
 * are from seperate axis. The ValueNodes values do not have to be limited to
 * between -1 and 1 but the output of this method will be scaled to be from -1
 * to 1.
 * 
 * @author Michael Bentley
 *
 */
public class ArcadeDriveValueNode extends ValueNode<Double> {

	/**
	 * The ValueNode which determines the yaw for the robots movement. This would be
	 * the equivalent of the x-axis for a standard arcade drive.
	 */
	ValueNode<? extends Number> yawValueNode;

	/**
	 * The ValueNode which determines the forward velocity for the robots movement.
	 * This would be the equivalent of the y-axis for a standard arcade drive.
	 */
	ValueNode<? extends Number> forwardValueNode;

	/**
	 * If this ValueNode should return the left or right motor speed.
	 */
	boolean isLeftMotorOutput;

	/**
	 * This ValueNode returns the speed of the specified side of a drivetrain for a
	 * standard arcade drive. It uses forward and yaw axis of a joystick or
	 * equivalent. This can also be used for a west coast drive if the ValueNodes
	 * are from seperate axis. The ValueNodes values do not have to be limited to
	 * between -1 and 1 but the output of this method will be scaled to be from -1
	 * to 1.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This' Logger.
	 * @param label
	 *            This' Label.
	 * @param forwardValueNode
	 *            The forward value for an arcade drive. The equivalent of the
	 *            y-axis of a joystick in a normal arcade drive.
	 * @param yawValueNode
	 *            The yaw value for an arcade drive. The equivalent of the x-axis of
	 *            a joystick in a normal arcade drive.
	 * @param isLeftMotorOutput
	 *            If this ValueNode should return the speed for the left motor.
	 *            False if it should return the right motor.
	 */
	public ArcadeDriveValueNode(Network network, Logger logger, String label,
			ValueNode<? extends Number> forwardValueNode, ValueNode<? extends Number> yawValueNode,
			boolean isLeftMotorOutput) throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label, forwardValueNode, yawValueNode);

		this.forwardValueNode = forwardValueNode;
		this.yawValueNode = yawValueNode;
		this.isLeftMotorOutput = isLeftMotorOutput;
	}

	// TODO test to make sure that it returns the proper side for left and right.
	@Override
	protected Double updateValue() {
		double yaw = this.yawValueNode.getValue().doubleValue();
		double forward = this.forwardValueNode.getValue().doubleValue();

		double leftMotorSpeed = forward;
		double rightMotorSpeed = forward;

		leftMotorSpeed += yaw;
		rightMotorSpeed -= yaw;

		double absRightMotorSpeed = Math.abs(rightMotorSpeed);
		double absLeftMotorSpeed = Math.abs(leftMotorSpeed);

		if (absRightMotorSpeed > 1 || absLeftMotorSpeed > 1) {
			if (absRightMotorSpeed > absLeftMotorSpeed) {
				rightMotorSpeed = rightMotorSpeed / absRightMotorSpeed;
				leftMotorSpeed = leftMotorSpeed / absRightMotorSpeed;
			} else {
				leftMotorSpeed = leftMotorSpeed / absLeftMotorSpeed;
				rightMotorSpeed = rightMotorSpeed / absLeftMotorSpeed;
			}
		}

		if (this.isLeftMotorOutput) {
			return leftMotorSpeed;
		}
		return rightMotorSpeed;
	}

}

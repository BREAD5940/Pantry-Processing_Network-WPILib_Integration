package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.NodeGroup;
import org.team5940.pantry.processing_network.ValueNode;

/**
 * This helps easily create and ArcadeDrive control scheme. You can access the
 * ValueNodes that return motor speed values through getLeftMotorValueNode() and
 * getRightMotorValueNode(). This uses {@link ArcadeDriveValueNode} as the left
 * and right motor speed ValueNodes. This can also work with west coast drive if
 * inputed axis are from different joysticks. If the yaw and forward ValueNode
 * return values outside of -1 and 1 then they will both be scaled down to -1
 * and 1.
 * 
 * @author Michael Bentley
 *
 */
public class ArcadeDriveNodeGroup extends NodeGroup {

	/**
	 * The ValueNode that returns the motor speed for the left drivetrain motors.
	 */
	ArcadeDriveValueNode leftMotorValueNode;

	/**
	 * The ValueNode that returns the motor speed for the right drivetrain motors.
	 */
	ArcadeDriveValueNode rightMotorValueNode;

	/**
	 * This helps easily create and ArcadeDrive control scheme. You can access the
	 * ValueNodes that return motor speed values through getLeftMotorValueNode() and
	 * getRightMotorValueNode(). This uses {@link ArcadeDriveValueNode} as the left
	 * and right motor speed ValueNodes. This can also work with west coast drive if
	 * inputed axis are from different joysticks. If the yaw and forward ValueNode
	 * return values outside of -1 and 1 then they will both be scaled down to -1
	 * and 1. The two ArcadeDriveValueNodes created will have the label will have
	 * the label "Left Arcade Value Node: " + baseLabel and "Right Arcade Value
	 * Node: " + baseLabel.
	 * 
	 * @param network
	 *            This' Network.
	 * @param logger
	 *            This's Network.
	 * @param baseLabel
	 *            The label for this node group and what to base the created nodes
	 *            labels off of.
	 * @param forwardValueNode
	 *            The forward axis for arcade drive.
	 * @param yawValueNode
	 *            The yaw axis for arcade drive.
	 */
	public ArcadeDriveNodeGroup(Network network, Logger logger, String baseLabel,
			ValueNode<? extends Number> forwardValueNode, ValueNode<? extends Number> yawValueNode) {
		super(network, logger, baseLabel);

		this.leftMotorValueNode = new ArcadeDriveValueNode(network, logger, "left Arcade ValueNode: " + baseLabel,
				forwardValueNode, yawValueNode, true);
		this.rightMotorValueNode = new ArcadeDriveValueNode(network, logger, "Right Arcade ValueNode: " + baseLabel,
				forwardValueNode, yawValueNode, false);
	}

	/**
	 * Returns the ValueNode for the left motor speed.
	 * 
	 * @return The ValueNode for the left motor speed.
	 */
	public ArcadeDriveValueNode getLeftMotorValueNode() {
		return this.leftMotorValueNode;
	}

	/**
	 * Returns the ValueNode for the right motor speed.
	 * 
	 * @return The ValueNode for the right motor speed.
	 */
	public ArcadeDriveValueNode getRightMotorValueNode() {
		return rightMotorValueNode;
	}
}

package org.team5940.pantry.processing_network.wpilib.input;

import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;
import org.team5940.pantry.processing_network.wpilib.input.RobotStateValueNode.RobotState;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * This {@link ValueNode} provides an enum indicating the current state (opcon, auto, etc...) of the robot (as passed in as an instance of RobotBase on this' initialization).
 * @author David Boles
 *
 */
public class RobotStateValueNode extends ValueNode<RobotState> {

	/**
	 * An enum representing the possible states of the robot: disabled, autonomous, operator control, or testing.
	 * @author David Boles
	 *
	 */
	public enum RobotState {
		DISABLED,
		AUTONOMOUS,
		OPERATOR_CONTROL,
		TESTING
	}
	
	/**
	 * Stores the robot base used to determine which state to return.
	 */
	RobotBase robot;
	
	/**
	 * Initializes a new {@link RobotStateValueNode}.
	 * @param network The network that this node is a member of.
	 * @param robot The RobotBase used to determine what state to return.
	 * @throws IllegalArgumentException robot is null or thrown by superclass, {@link ValueNode}.
	 * @throws IllegalStateException thrown by superclass, {@link ValueNode}.
	 */
	public RobotStateValueNode(Network network, RobotBase robot) throws IllegalArgumentException, IllegalStateException {
		super(network);
		
		if(robot == null) {
			throw new IllegalArgumentException("Null Robot");
		}
		
		this.robot = robot;
	}

	@Override
	protected RobotState updateValue() {
		if(this.robot.isDisabled()) {//This one has to go first, WPILib is goofy.
			return RobotState.DISABLED;
		}else if(this.robot.isAutonomous()) {
			return RobotState.AUTONOMOUS;
		}else if(this.robot.isOperatorControl()) {
			return RobotState.OPERATOR_CONTROL;
		}else {
			return RobotState.TESTING;
		}
	}
}

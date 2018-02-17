package org.team5940.pantry.processing_network.wpilib.systems;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Converts a boolean to a doubleSolenoidValue.
 * 
 * @author Paul Dowd
 *
 */

public class BooleanToDoubleSolenoidValueNode extends ValueNode<DoubleSolenoid.Value> {
	
	/**
	 * creates a boolean ValueNode to convert
	 */
	
	ValueNode<?extends Boolean> valueNode;
	
	/**
	 * Converts the boolean to a DoubleSolenoidValue
	 * @param network this' network
	 * @param logger this' logger
	 * @param label this' label
	 * @param valueNode the ValueNode which is being converted
	 */
	
	public BooleanToDoubleSolenoidValueNode(Network network, Logger logger, String label, ValueNode<? extends Boolean> valueNode)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label, valueNode);
		this.valueNode = valueNode;
	}

	@Override
	protected Value updateValue() {
		if (valueNode.getValue()) {
			return DoubleSolenoid.Value.kForward;
		} else {
			return DoubleSolenoid.Value.kReverse;
		}
	}
}

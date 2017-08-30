/**
 * 
 */
package org.team5940.pantry.processing_network.wpilib.output.pneumatics;

import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.Node;
import org.team5940.pantry.processing_network.ProcessingNetworkUtils;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * @author Michael Bentley
 *
 */ // TODO rename this to DoubleSolenode.
public class DoubleSolenoidNode extends Node {

	ValueNode<? extends DoubleSolenoid.Value> solenoidValueNode;
	DoubleSolenoid[] doubleSolenoids;

	public DoubleSolenoidNode(Network network, boolean requireUpdate, ValueNode<? extends DoubleSolenoid.Value> solenoidValueNode, DoubleSolenoid... doubleSolenoids)
			throws IllegalArgumentException, IllegalStateException {
		super(network, requireUpdate, solenoidValueNode);

		ProcessingNetworkUtils.checkArrayArguments(doubleSolenoids);

		this.solenoidValueNode = solenoidValueNode;
		this.doubleSolenoids = doubleSolenoids;
	}

	@Override
	protected void doUpdate() {
		for (DoubleSolenoid doubleSolenoid : this.doubleSolenoids) {
			doubleSolenoid.set(solenoidValueNode.getValue());
		}
	}

}

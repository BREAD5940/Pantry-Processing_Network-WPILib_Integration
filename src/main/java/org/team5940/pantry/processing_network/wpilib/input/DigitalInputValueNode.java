package org.team5940.pantry.processing_network.wpilib.input;

import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This ValueNode returns the value of a {@link DigitalInput} which can be
 * inverted. It uses the {@link DigitalInput#get()} to get the current value of
 * the DigitalInput.
 * 
 * @author Michael Bentley
 *
 */
public class DigitalInputValueNode extends ValueNode<Boolean> {

	/**
	 * The {@link DigitalInput} to return the value of for this ValueNode.
	 */
	DigitalInput digitalInput;

	/**
	 * If the value of the DigitalInput should be inverted.
	 */
	boolean inverted;

	/**
	 * Creates a new {@link DigitalInputValueNode}.
	 * 
	 * @param network
	 *            This' Network
	 * @param logger
	 *            This' Logger
	 * @param label
	 *            This' Label.
	 * @param digitalInput
	 *            The {@link DigitalInput} to return the value of.
	 * @param inverted
	 *            if the value of the DigitalInput should be inverted.
	 */
	public DigitalInputValueNode(Network network, Logger logger, String label, DigitalInput digitalInput,
			boolean inverted) throws IllegalArgumentException, IllegalStateException {
		super(network, logger, label);

		LoggingUtils.checkArgument(digitalInput);

		this.digitalInput = digitalInput;
		this.inverted = inverted;
	}

	@Override
	protected Boolean updateValue() {
		if (this.inverted) {
			return !this.digitalInput.get();
		}
		return this.digitalInput.get();
	}

}

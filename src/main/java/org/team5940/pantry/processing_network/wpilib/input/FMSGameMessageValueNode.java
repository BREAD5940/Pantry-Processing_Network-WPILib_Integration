package org.team5940.pantry.processing_network.wpilib.input;

import edu.wpi.first.wpilibj.DriverStation;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import com.google.gson.JsonArray;

public class FMSGameMessageValueNode extends ValueNode {

    DriverStation dsInstance;

    /**
     * Initializes a new {@link FMSGameMessageValueNode}
     *
     * @param network
     *            This' Network
     * @param logger
     *            This' Logger
     * @param label
     *            The label that describes this Node.
     */
    public FMSGameMessageValueNode(Network network, Logger logger, JsonArray label)
            throws IllegalArgumentException, IllegalStateException {
        super(network, logger, label);
        this.dsInstance = DriverStation.getInstance();
    }

    @Override
    protected Object updateValue() {
        return this.dsInstance.getGameSpecificMessage();
    }
}

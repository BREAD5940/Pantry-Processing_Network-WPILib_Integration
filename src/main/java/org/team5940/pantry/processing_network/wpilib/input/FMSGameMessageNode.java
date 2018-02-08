package org.team5940.pantry.processing_network.wpilib.input;

import edu.wpi.first.wpilibj.DriverStation;
import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import com.google.gson.JsonArray;

public class FMSGameMessageNode extends ValueNode {

    DriverStation dsInstance;

    /**
     * Initializes a new {@link FMSGameMessageNode}
     *
     * @param network
     *            This' Network
     * @param logger
     *            This' Logger
     * @param label
     *            The label that describes this Node.
     */
    public FMSGameMessageNode(Network network, Logger logger, JsonArray label)
            throws IllegalArgumentException, IllegalStateException {
        super(network, logger, label);
        this.dsInstance = DriverStation.getInstance();
    }

    @Override
    protected Object updateValue() {
        return this.dsInstance.getGameSpecificMessage();
    }
}

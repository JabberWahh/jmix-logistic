package org.lightspeedsnail.screen.shipment;

import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Shipment;

@UiController("Shipment.edit")
@UiDescriptor("shipment-edit.xml")
@EditedEntityContainer("shipmentDc")
public class ShipmentEdit extends StandardEditor<Shipment> {
}
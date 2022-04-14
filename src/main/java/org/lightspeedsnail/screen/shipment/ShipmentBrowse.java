package org.lightspeedsnail.screen.shipment;

import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Shipment;

@UiController("Shipment.browse")
@UiDescriptor("shipment-browse.xml")
@LookupComponent("shipmentsTable")
public class ShipmentBrowse extends StandardLookup<Shipment> {
}
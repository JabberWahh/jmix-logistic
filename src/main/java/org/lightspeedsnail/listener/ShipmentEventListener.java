package org.lightspeedsnail.listener;

import org.lightspeedsnail.entity.Shipment;
import io.jmix.core.event.EntitySavingEvent;
import org.lightspeedsnail.service.NumberingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventListener {

    @Autowired
    private NumberingService numberingService;

    @EventListener
    public void onShipmentSaving(EntitySavingEvent<Shipment> event) {
        if (event.getEntity().getNumber() == null) {
            event.getEntity().setUnicNumber(numberingService.getLastNumber(event.getEntity().getClass().getSimpleName()));
        }
    }
}
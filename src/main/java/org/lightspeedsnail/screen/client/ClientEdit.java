package org.lightspeedsnail.screen.client;

import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Client;

import java.time.LocalDateTime;


@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {

    @Subscribe
    public void onInitEntity(InitEntityEvent<Client> event) {
        if(event.getEntity().getRegistrationDate() == null){
            event.getEntity().setFirstRegistrationDate(LocalDateTime.now());
        }
    }

}
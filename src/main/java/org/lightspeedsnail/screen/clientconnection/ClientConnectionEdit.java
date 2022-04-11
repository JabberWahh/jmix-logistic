package org.lightspeedsnail.screen.clientconnection;

import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.ClientContact;

@UiController("ClientConnection.edit")
@UiDescriptor("client-connection-edit.xml")
@EditedEntityContainer("clientConnectionDc")
public class ClientConnectionEdit extends StandardEditor<ClientContact> {
}
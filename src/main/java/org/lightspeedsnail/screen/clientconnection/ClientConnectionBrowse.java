package org.lightspeedsnail.screen.clientconnection;

import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.ClientContact;

@UiController("ClientConnection.browse")
@UiDescriptor("client-connection-browse.xml")
@LookupComponent("clientConnectionsTable")
public class ClientConnectionBrowse extends StandardLookup<ClientContact> {
}
package org.lightspeedsnail.screen.client;

import io.jmix.ui.Actions;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Client;
import org.lightspeedsnail.entity.ClientContact;
import org.lightspeedsnail.service.MarkToDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@UiController("Client.browse")
@UiDescriptor("client-browse.xml")
@LookupComponent("clientsTable")
public class ClientBrowse extends StandardLookup<Client> {

    @Autowired
    private DataGrid<Client> clientsTable;
    @Autowired
    private Actions actions;
    @Autowired
    private Button deleteBtn;

    @Subscribe
    public void onInit(InitEvent event) {

        clientsTable.addRowStyleProvider(client -> {
            if (client.getDeleted()) {
                return "deleted-entity";
            }
            return null;
        });

        MarkToDeleteAction ss = actions.create(MarkToDeleteAction.class, MarkToDeleteAction.ID);
        ss.setTarget(clientsTable);
        deleteBtn.setAction(ss);

        //Not necessary
//        DataGrid.TextRenderer clientsTableConnectionRenderer = getApplicationContext().getBean(DataGrid.TextRenderer.class);
//        clientsTable.getColumn("info").setRenderer(clientsTableConnectionRenderer);


    }

    @Install(to = "clientsTable.info", subject = "columnGenerator")
    private String info(DataGrid.ColumnGeneratorEvent<Client> columnGeneratorEvent) {
        Set<ClientContact> connection = columnGeneratorEvent.getItem().getClientContact();
        String info = connection.stream()
                .filter(clientConnection -> clientConnection.getPrimaryType())
                .findFirst()
                .map(clientConnection -> clientConnection.getInfo())
                .orElse("");
        return info;
    }
}
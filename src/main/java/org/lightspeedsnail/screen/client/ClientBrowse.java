package org.lightspeedsnail.screen.client;

import io.jmix.ui.Actions;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.data.GroupInfo;
import io.jmix.ui.screen.*;
import org.eclipse.persistence.sessions.interceptors.CacheKeyInterceptor;
import org.lightspeedsnail.entity.Client;
import org.lightspeedsnail.service.MarkToDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.function.Function;

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
            if(client.getDeleted()){
                return "deleted-entity";
            }
            return null;
        });

        MarkToDeleteAction ss = actions.create(MarkToDeleteAction.class, MarkToDeleteAction.ID);
        ss.setTarget(clientsTable);
        deleteBtn.setAction(ss);

    }
}
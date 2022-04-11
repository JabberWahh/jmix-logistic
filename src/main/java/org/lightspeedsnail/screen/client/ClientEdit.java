package org.lightspeedsnail.screen.client;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Fragments;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Client;
import org.lightspeedsnail.entity.ClientContact;
import org.lightspeedsnail.entity.User;
import org.lightspeedsnail.screen.lockedentity.LockedEntity;
import org.lightspeedsnail.service.GlobalLocked;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;


@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {

    @Autowired
    private GlobalLocked globalLocked;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private InstanceContainer<Client> clientDc;
    @Autowired
    private GroupBoxLayout lockedGroup;
    @Autowired
    private Fragments fragments;
    @Autowired
    private CollectionPropertyContainer<ClientContact> clientContactDc;
    @Autowired
    private DataContext dataContext;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Client> event) {
        if(event.getEntity().getRegistrationDate() == null){
            event.getEntity().setFirstRegistrationDate(LocalDateTime.now());
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        GlobalLocked.LockInfo isLocked = globalLocked.isLocked(clientDc.getItem(), (User)currentAuthentication.getUser());
        lockedGroup.setVisible(isLocked.getLocked());

        if(isLocked.getLocked()) {
            this.setReadOnly(true);
            LockedEntity le = fragments.create(this, LockedEntity.class);
            le.setLabelLocked(clientDc.getItem().getName(), isLocked.getUser(), isLocked.getLocalDateTime());
            lockedGroup.add(le.getFragment());
        }
    }

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        globalLocked.unlock(clientDc.getItem(), (User)currentAuthentication.getUser());
    }

    @Subscribe("clientContactTableAddBtn")
    public void onClientConnectionsTableAddBtnClick(Button.ClickEvent event) {
        ClientContact cc = dataContext.create(ClientContact.class);
        cc.setClient(clientDc.getItem());
        clientContactDc.getDisconnectedItems().add(cc);
    }

    @Subscribe("clientConnectionsTable")
    public void onClientConnectionsTableEditorClose(DataGrid.EditorCloseEvent<ClientContact> event) {
        ClientContact current = event.getItem();
        List<ClientContact> items = clientContactDc.getItems();
        if(items.size() == 1){
            current.setPrimaryType(true);
        }
        else{
            if(current.getPrimaryType()){
                for (ClientContact c :
                        items) {
                    if (c != current && c.getPrimaryType()) {
                        c.setPrimaryType(false);
                    }
                }
            }
            else{
                boolean isAnyPrimary = false;
                for (ClientContact c :
                        items) {
                    if (c.getPrimaryType()) {
                        isAnyPrimary = true;
                    }
                }
                if (!isAnyPrimary){
                    current.setPrimaryType(true);
                }
            }
        }
    }

}
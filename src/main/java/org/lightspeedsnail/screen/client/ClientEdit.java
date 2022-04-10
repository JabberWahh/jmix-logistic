package org.lightspeedsnail.screen.client;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Fragments;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Client;
import org.lightspeedsnail.entity.User;
import org.lightspeedsnail.screen.lockedentity.LockedEntity;
import org.lightspeedsnail.service.GlobalLocked;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


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

}
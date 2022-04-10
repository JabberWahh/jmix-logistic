package org.lightspeedsnail.service;

import io.jmix.core.MetadataTools;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.ActionType;
import io.jmix.ui.action.ItemTrackingAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.ComponentsHelper;
import io.jmix.ui.meta.StudioAction;
import org.lightspeedsnail.entity.DocumentsAndReferences;
import org.springframework.beans.factory.annotation.Autowired;

@StudioAction(target = "io.jmix.ui.component.ListComponent")
@ActionType("markToDelete")
public class MarkToDeleteAction extends ItemTrackingAction {

    public static final String ID = "markToDelete";

    @Autowired
    private MetadataTools metadataTools;
    @Autowired
    private Deletion deletion;
    @Autowired
    private Dialogs dialogs;

    private String description;

    public MarkToDeleteAction(String id) {
        super(id);
        setCaption("Delete");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void actionPerform(Component component) {
        if (getTarget() != null) {
            Object selected = getTarget().getSingleSelected();
            if (selected != null) {
                Notifications notifications = ComponentsHelper.getScreenContext(target).getNotifications();
                notifications.create()
                        .withType(Notifications.NotificationType.TRAY)
                        .withDescription(description)
                        .withCaption(metadataTools.getInstanceName(selected))
                        .show();
                deletion.markToDelete((DocumentsAndReferences) selected, dialogs);
            }
        }

    }

}
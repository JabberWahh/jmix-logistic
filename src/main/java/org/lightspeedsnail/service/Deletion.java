package org.lightspeedsnail.service;

import io.jmix.core.DataManager;
import io.jmix.ui.Dialogs;
import io.jmix.ui.action.DialogAction;
import org.lightspeedsnail.entity.DocumentsAndReferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Deletion {

    @Autowired
    private DataManager dataManager;

    public void markToDelete(DocumentsAndReferences item, Dialogs dialogs) {

        String msg = "Do you really want to remove? : " + item;

        dialogs.createOptionDialog()
                .withCaption("Confirm to delete")
                .withMessage(msg)
                .withActions(
                        new DialogAction(DialogAction.Type.YES)
                                .withHandler(actionPerformedEvent -> {

                                    try {
                                        item.setDeleted(!item.getDeleted());
                                        dataManager.save(item);
                                    } catch (RuntimeException e) {
                                        e.printStackTrace();
                                    }

                                }),
                        new DialogAction(DialogAction.Type.NO))
                .show();
    }
}

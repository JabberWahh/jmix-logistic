package org.lightspeedsnail.screen.lockedentity;

import io.jmix.ui.component.Label;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.lightspeedsnail.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UiController("LockedEntity")
@UiDescriptor("locked-entity.xml")
public class LockedEntity extends ScreenFragment {

    @Autowired
    private Label<String> labelLocked;

    public void setLabelLocked(String itemName, User user, LocalDateTime localDateTime) {
        this.labelLocked.setIconFromSet(JmixIcon.LOCK);
        this.labelLocked.setValue("<font style=\"color:#ff4444\">" + "Item <b>" + itemName + "</b> is locked by <b>" + user.getDisplayName()
                + "</b> at <b>" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</b></font>");

    }

}
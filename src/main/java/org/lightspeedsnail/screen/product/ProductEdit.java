package org.lightspeedsnail.screen.product;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Fragments;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Product;
import org.lightspeedsnail.entity.User;
import org.lightspeedsnail.screen.lockedentity.LockedEntity;
import org.lightspeedsnail.service.GlobalLocked;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
public class ProductEdit extends StandardEditor<Product> {

    @Autowired
    private GroupBoxLayout lockedGroup;
    @Autowired
    private GlobalLocked globalLocked;
    @Autowired
    private InstanceContainer<Product> productDc;
    @Autowired
    private Fragments fragments;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        globalLocked.unlock(productDc.getItem(), (User)currentAuthentication.getUser());
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        GlobalLocked.LockInfo isLocked = globalLocked.isLocked(productDc.getItem(), (User)currentAuthentication.getUser());
        lockedGroup.setVisible(isLocked.getLocked());

        if(isLocked.getLocked()) {
            this.setReadOnly(true);
            LockedEntity le = fragments.create(this, LockedEntity.class);
            le.setLabelLocked(productDc.getItem().getName(), isLocked.getUser(), isLocked.getLocalDateTime());
            lockedGroup.add(le.getFragment());
        }
    }
}
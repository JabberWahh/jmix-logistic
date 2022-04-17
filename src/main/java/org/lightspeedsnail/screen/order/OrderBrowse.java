package org.lightspeedsnail.screen.order;

import io.jmix.ui.component.DataGrid;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Order_.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {
    @Autowired
    private DataGrid<Order> ordersTable;

    @Subscribe
    public void onInit(InitEvent event) {
        ordersTable.addRowStyleProvider(product -> {
            if (product.getDeleted()) {
                return "deleted-entity";
            }
            return null;
        });
    }
}
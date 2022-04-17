package org.lightspeedsnail.screen.order;

import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.*;
import org.lightspeedsnail.screen.address.AddressBrowse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {

    @Autowired
    private EntityPicker<Client> clientField;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private InstanceContainer<Order> orderDc;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private CollectionPropertyContainer<OrderDetail> order_detailDc;

    @Subscribe("setAddressBtn")
    public void onSetAddressBtnClick(Button.ClickEvent event) {
        screenBuilders.lookup(Address.class, this)
                .withOpenMode(OpenMode.DIALOG)
                .withOptions(new AddressFilterOptions(clientField.getValue()))
                .withScreenClass(AddressBrowse.class)
                .withSelectHandler(a -> {
                            orderDc.getItem().setAddress(a.stream().map(Address::getInstanceName).findFirst().orElse(""));
                        }
                )
                .build()
                .show();
    }

    @Subscribe("orderDetailTableAddBtn")
    public void onOrderDetailTableAddBtnClick(Button.ClickEvent event) {
        OrderDetail cc = dataContext.create(OrderDetail.class);
        cc.setOrder(orderDc.getItem());
        order_detailDc.getDisconnectedItems().add(cc);
    }

    @Subscribe("orderDetailsTable")
    public void onOrderDetailsTableEditorClose(DataGrid.EditorCloseEvent<OrderDetail> event) {
        OrderDetail item = event.getItem();
        item.setSum(item.getPrice() * item.getQuantity());
    }

    //TODO make footer with sums
    public class AddressFilterOptions implements ScreenOptions {

        private Client client;

        public AddressFilterOptions(Client client) {
            this.client = client;
        }

        public Client getClient() {
            return client;
        }
    }
}
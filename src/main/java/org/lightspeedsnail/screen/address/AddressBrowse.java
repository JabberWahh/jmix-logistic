package org.lightspeedsnail.screen.address;

import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Address;
import org.lightspeedsnail.entity.Client;
import org.lightspeedsnail.screen.order.OrderEdit;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Address.browse")
@UiDescriptor("address-browse.xml")
@LookupComponent("addressesTable")
public class AddressBrowse extends StandardLookup<Address> {

    @Autowired
    private CollectionLoader<Address> addressesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof OrderEdit.AddressFilterOptions) {
            Client client = ((OrderEdit.AddressFilterOptions) options).getClient();
            addressesDl.setParameter("client", client);
            addressesDl.load();
        }
    }
    
}
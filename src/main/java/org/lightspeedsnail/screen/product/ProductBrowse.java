package org.lightspeedsnail.screen.product;

import io.jmix.ui.component.DataGrid;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
public class ProductBrowse extends StandardLookup<Product> {
    @Autowired
    private DataGrid<Product> productsTable;

    @Subscribe
    public void onInit(InitEvent event) {
        productsTable.addRowStyleProvider(product -> {
            if (product.getDeleted()) {
                return "deleted-entity";
            }
            return null;
        });
    }
}
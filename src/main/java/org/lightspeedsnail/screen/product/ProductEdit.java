package org.lightspeedsnail.screen.product;

import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Product;

@UiController("Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
public class ProductEdit extends StandardEditor<Product> {
}
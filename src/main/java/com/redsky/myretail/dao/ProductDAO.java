package com.redsky.myretail.dao;

import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductPrice;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO {

    public Product productDetails(final ProductObject productObject) {

        Product productObjectData = null;

        if(productObject != null) {
            productObjectData = new Product();

            productObjectData.setId(productObject.getProductId());
            productObjectData.setName(productObject.getTitle());

            ProductPrice productPrice = new ProductPrice();
            productPrice.setCurrencyCode(productObject.getCurrencyCode());
            productPrice.setPrice(productPrice.getPrice());
            productObjectData.setProductPrice(productPrice);
        }

        return productObjectData;
    }

    public ProductObject productObject(final Product product) {
        ProductObject productObject = new ProductObject(product.getId());
        productObject.setProductPrice(product.getProductPrice().getPrice());
        return productObject;
    }
}

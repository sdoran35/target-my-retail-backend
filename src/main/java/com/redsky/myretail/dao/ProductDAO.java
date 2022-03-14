package com.redsky.myretail.dao;

import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductPrice;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO {

    public Product productDetails(final ProductObject productObject) {

        Product product = null;

        if(productObject != null) {
            product = new Product();

            product.setId(productObject.getProductId());
            product.setTitle(productObject.getTitle());

            ProductPrice productPrice = new ProductPrice();
            productPrice.setCurrencyCode(productObject.getCurrencyCode());
            productPrice.setPrice(productObject.getProductPrice());
            product.setProductPrice(productPrice);
        }

        return product;
    }

    public ProductObject productObject(final Product product) {
        ProductObject productObject = new ProductObject(product.getId());
        productObject.setProductPrice(product.getProductPrice().getPrice());
        return productObject;
    }
}

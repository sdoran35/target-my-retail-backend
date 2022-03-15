package com.redsky.myretail.dao;

import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductPrice;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO {

    /**
     * Create a new product
     *
     * @param productObject the product object
     * @return a new product
     */
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

    /**
     * Create a new product object
     *
     * @param product the product
     * @return a new product object
     */
    public ProductObject productObject(final Product product) {
        ProductObject productObject = new ProductObject(product.getId());
        productObject.setProductPrice(product.getProductPrice().getPrice());
        return productObject;
    }
}

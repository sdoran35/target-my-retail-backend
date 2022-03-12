package com.redsky.myretail.service;

import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductObject;

public interface ProductService {

    Product getProductById(final int id) throws Exception;

    Product updateProduct(final ProductObject productObject);
}

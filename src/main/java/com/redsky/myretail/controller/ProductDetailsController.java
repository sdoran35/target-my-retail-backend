package com.redsky.myretail.controller;


import com.redsky.myretail.dao.ProductDAO;
import com.redsky.myretail.domain.Product;
import com.redsky.myretail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductDetailsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDAO productDAO;

    @GetMapping(value = "products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable final int id) throws Exception {

        Product product = null;

        try {
            product = p
        }
    }
}

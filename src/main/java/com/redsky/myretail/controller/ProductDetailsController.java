package com.redsky.myretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.dao.ProductDAO;
import com.redsky.myretail.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductDetailsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDAO productDAO;

    // Return JSON representation of Product
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductDetailsById(@PathVariable int id) throws Exception {
        Product product = null;

        try {
            product = productService.getProductById(id);
            if (product == null) {
                return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(ex.getStatusCode());
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Update Product's Price
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProductPriceById(@RequestBody Product productRequest, @PathVariable int id)
            throws Exception {
        Product product = null;

        try {
            ProductObject productRO = productDAO.productObject(productRequest);
            product = productService.updateProduct(productRO);
            if (product == null) {
                return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
            }
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(ex.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
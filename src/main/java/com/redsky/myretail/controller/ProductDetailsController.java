package com.redsky.myretail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.dao.ProductDAO;
import com.redsky.myretail.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductDetailsController {

    private final Logger logger = LoggerFactory.getLogger(ProductDetailsController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDAO productDAO;

    /**
     * Return the JSON for the product
     *
     * @param id the product id
     * @return the product
     * @throws Exception
     */
    @GetMapping(value = "products/{id}")
    public ResponseEntity<Product> getProductDetailsById(@PathVariable final int id) throws Exception {
        Product product = null;

        try {
            product = productService.getProductById(id);
            if (product == null) {
                return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
            }
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (HttpClientErrorException ex) {
            logger.error(ex.getLocalizedMessage(), ex);
            return new ResponseEntity<>(ex.getStatusCode());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Update the price of a various product
     *
     * @param productRequest the product to update
     * @param id the product id
     * @return the product with an updated price
     * @throws Exception
     */
    @PutMapping(value = "products/{id}")
    public ResponseEntity<Product> updateProductPriceById(@RequestBody final Product productRequest,
                                                          @PathVariable final int id) throws Exception {
        Product product;

        try {
            ProductObject productRO = productDAO.productObject(productRequest);
            product = productService.updateProduct(productRO);
            if (product == null) {
                logger.info(HttpStatus.NO_CONTENT.getReasonPhrase());
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (HttpClientErrorException ex) {
            logger.error(ex.getLocalizedMessage(), ex);
            return new ResponseEntity<>(ex.getStatusCode());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
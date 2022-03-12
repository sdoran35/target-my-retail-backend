package com.redsky.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redsky.myretail.dao.ProductDAO;
import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Properties;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final ProductRepository productRepository;
    RestTemplate restTemplate = new RestTemplate();
    private final Properties properties;

    public ProductServiceImpl(final ProductDAO productDAO,
                              final ProductRepository productRepository) {

        this.productDAO = productDAO;
        this.productRepository = productRepository;
        this.properties = System.getProperties();
    }


    public String getURI(final int productId) {
        return properties.getProperty("api.host") + productId + properties.getProperty("api.excludes");
    }

    public Product getProductById(final int id) throws JsonProcessingException, IOException {

        ProductObject productObject = productRepository.findByProductId(id);

        String productTitle = getProductTitle(id);

        if(productObject == null) {
            productObject = new ProductObject(id);
        }

        productObject.setTitle(productTitle);

        return productDAO.productDetails(productObject);
    }

    public String getProductTitle(final int productId) throws JsonProcessingException, IOException {
        String responseBody = null;
        String productTitle = null;

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(getURI(productId)).build();
        responseBody = restTemplate.getForEntity(uriComponents.encode().toUri(), String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode productRootNode = mapper.readTree(responseBody);

        if(productRootNode != null) {
            productTitle = productRootNode.get("product").get("item").get("product_description").get("title").asText();
        }

        return productTitle;
    }

    public Product updateProduct(final ProductObject productObject) {
        // get the product from the database
        ProductObject currentProduct = productRepository.findByProductId(productObject.getProductId());

        if(currentProduct == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "404");
        }

        currentProduct.setProductPrice(productObject.getProductPrice());

        // update the product in the database
        return productDAO.productDetails(productRepository.updateProduct(currentProduct));
    }

}

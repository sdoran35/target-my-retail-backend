package com.redsky.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redsky.myretail.dao.ProductDAO;
import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service(value = "productDetailsService")
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private final String apiHost;

    public ProductServiceImpl(final ProductDAO productDAO,
                              final ProductRepository productRepository,
                              @Value("${api.host:null}") final String apiHost) {

        this.productDAO = productDAO;
        this.productRepository = productRepository;
        this.restTemplate = new RestTemplate();
        this.apiHost = apiHost;
    }

    /**
     * Return the RedSky API URL
     *
     * @param productId the product id
     * @return RedSky API URL
     */
    public String getURI(final int productId) {
        return apiHost + productId;
    }

    /**
     * Get the product using id
     *
     * @param id the id
     * @return return a product
     * @throws JsonProcessingException json processing exception
     * @throws IOException io exception
     */
    public Product getProductById(final int id) throws JsonProcessingException, IOException {

        ProductObject productObject = productRepository.findByProductId(id);

        String productTitle = getProductTitle(id);

        if(productObject == null) {
            productObject = new ProductObject(id);
        }

        productObject.setTitle(productTitle);

        return productDAO.productDetails(productObject);
    }

    /**
     * Get a product title using product id
     *
     * @param productId the product id
     * @return the product title
     * @throws JsonProcessingException json processing exception
     * @throws IOException io exception
     */
    public String getProductTitle(final int productId) throws JsonProcessingException, IOException {
        String responseBody = null;
        String productTitle = null;

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(getURI(productId)).build();
        responseBody = restTemplate.getForEntity(uriComponents.encode().toUri(), String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode productRootNode = mapper.readTree(responseBody);

        if(productRootNode != null) {
            productTitle = productRootNode
                    .get("data")
                    .get("product")
                    .get("item")
                    .get("product_description")
                    .get("title").asText();
        }

        return productTitle;
    }

    /**
     * Update a product price
     *
     * @param productObject the product object
     * @return the updated product
     */
    public Product updateProduct(final ProductObject productObject) {
        // get the product from the database
        ProductObject currentProduct = productRepository.findByProductId(productObject.getProductId());

        if(currentProduct == null) {
            logger.info(HttpStatus.NOT_FOUND.getReasonPhrase());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "404");
        }

        currentProduct.setProductPrice(productObject.getProductPrice());

        // update the product in the database
        ProductObject updatedProduct = productRepository.save(currentProduct);

        return productDAO.productDetails(updatedProduct);
    }

}

package com.redsky.myretail;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;

import com.redsky.myretail.domain.Product;
import com.redsky.myretail.domain.ProductObject;
import com.redsky.myretail.domain.ProductPrice;
import com.redsky.myretail.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyRetailApplication.class)
@WebAppConfiguration
public class MyRetailApplicationTests {

	@Autowired
	ProductRepository repository;

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	@Value("${default.product.id:13860428}")
	private int successfulID;

	@Before
	public void setUp() {
		//Setup the Mock MVC
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		//Remove existing entries in the repository
		this.repository.deleteAll();

		//Setup the mock product Object
		ProductObject productObject = new ProductObject(successfulID);
		productObject.setTitle("The Big Lebowski (Blu-ray)");
		productObject.setCurrencyCode("USD");
		productObject.setProductPrice(new BigDecimal("14.95"));

		//Save the mock product object to the repository
		repository.save(productObject);
	}

	/**
	 * Test getting a product with id
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testGetProductById() throws Exception {
		mockMvc.perform(get("/v1/api/products/" + successfulID)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(successfulID)));
	}

	/**
	 * Test updating the product price
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testUpdateProductPriceById() throws Exception {
		Product requestBody = new Product();
		ProductPrice priceData = new ProductPrice();
		priceData.setPrice(new BigDecimal(14.95));
		requestBody.setId(successfulID);
		requestBody.setProductPrice(priceData);

		mockMvc.perform(put("/v1/api/products/" + successfulID).contentType(MediaType.APPLICATION_JSON)
						.content(convertToString(requestBody))).andExpect(status().isOk())
				.andExpect(jsonPath("$.productPrice.price", is(requestBody.getProductPrice().getPrice())));
	}

	/**
	 * Test an invalid product id
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetProductById_NotFound() throws Exception {
		mockMvc.perform(get("/v1/api/products/" + 123456789)).andExpect(status().isNotFound());
	}

	/**
	 * Test updating a product with an invalid product id
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testUpdateProductPriceById_NotFound() throws Exception {

		//Create product request body
		Product requestBody = new Product();

		//Create product price request
		ProductPrice priceData = new ProductPrice();

		priceData.setPrice(new BigDecimal(14.95));
		requestBody.setId(12345678);
		requestBody.setProductPrice(priceData);

		mockMvc.perform(put("/v1/api/products/" + requestBody.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(convertToString(requestBody))).andExpect(status().isNotFound());
	}

	/**
	 * Convert the JSON object into a string representation
	 *
	 * @param obj the json object
	 * @return a string
	 */
	private static String convertToString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
package com.redsky.myretail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    private static final long serialVersionUID = -6192476799660533368L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("productPrice")
    private ProductPrice productPrice;

    public Product() {
        //default, empty constructor
    }

    public Product(final int id,
                   final String name,
                   final ProductPrice productPrice) {

        this.id = id;
        this.name = name;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId() == product.getId() && getName().equals(product.getName()) && getProductPrice().equals(product.getProductPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getProductPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}

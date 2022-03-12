package com.redsky.myretail.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Objects;

@Document(collection = "Product")
public class ProductObject {

    @Id
    private String id;

    @Indexed
    private int productId;

    private String title;

    @Field("price")
    private BigDecimal productPrice;

    @Field("currencyCode")
    private String currencyCode;

    private ProductObject() {
        //default, empty constructor
    }

    public ProductObject(final int productId) {
        super();
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductObject)) return false;
        ProductObject productObject = (ProductObject) o;
        return getProductId() == productObject.getProductId() && Objects.equals(id, productObject.id) && getTitle().equals(productObject.getTitle()) && getProductPrice().equals(productObject.getProductPrice()) && getCurrencyCode().equals(productObject.getCurrencyCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getProductId(), getTitle(), getProductPrice(), getCurrencyCode());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productId=" + productId +
                ", title='" + title + '\'' +
                ", productPrice=" + productPrice +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}

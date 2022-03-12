package com.redsky.myretail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductPrice {

    @JsonProperty("value")
    protected BigDecimal price;

    @JsonProperty("currency_code")
    protected String currencyCode;

//    public ProductPrice() {
//        //default, empty constructor
//    }
//
//    public ProductPrice(final BigDecimal price,
//                        final String currencyCode) {
//
//        this.price = price;
//        this.currencyCode = currencyCode;
//    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        if (!(o instanceof ProductPrice)) return false;
        ProductPrice that = (ProductPrice) o;
        return getPrice().equals(that.getPrice()) && getCurrencyCode().equals(that.getCurrencyCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getCurrencyCode());
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "price=" + price +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}

package com.redsky.myretail.repository;

import com.redsky.myretail.domain.ProductObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("ProductRepository")
public interface ProductRepository extends MongoRepository<ProductObject, String> {

    @Query("{ 'productId' : ?0 }")
    ProductObject findByProductId(final int productId);

    @SuppressWarnings("unchecked")
    ProductObject save(final ProductObject productObject);
}

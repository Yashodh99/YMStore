package com.product.product.dao;


import com.product.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

    @Query(value="SELECT * FROM product WHERE product_id = ?1", nativeQuery = true)
    Product getProductById(int productId);
}

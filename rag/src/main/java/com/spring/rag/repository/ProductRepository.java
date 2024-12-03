package com.spring.rag.repository;

import com.spring.rag.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value =
            "SELECT productid, productname, productdescription " +
                    "FROM products p " +
                    "JOIN embeddings e ON p.productid = e.id " +
                    "WHERE 1 - (embedding <=> CAST(:embedding as vector)) > 0.5 " +
                    "LIMIT 5", nativeQuery = true)
    List<Product> getSimilarProducts(@Param("embedding") float[] embedding);


    @Query(value = "" +
            "WITH t AS ( " +
            "SELECT embedding " +
            "FROM embeddings e " +
            "JOIN products p ON e.id = p.productid " +
            "WHERE productname = :name ) " +
            "SELECT productid, productname, productdescription " +
            "FROM products p " +
            "JOIN embeddings e ON p.productid = e.id " +
            "WHERE 1 - (embedding <=> (SELECT embedding FROM t)) > 0.55 " +
            "LIMIT 5", nativeQuery = true)
    List<Product> getExactProducts(@Param("name") String name);
}
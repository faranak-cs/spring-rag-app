package com.spring.rag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    private Integer productid;
    private String productname;
    private String productdescription;
}
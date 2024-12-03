package com.spring.rag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;

@Setter
@Entity
@Table(name = "embeddings")
public class Embedding {
    @Id
    private Integer id;
    private float[] embedding;
}
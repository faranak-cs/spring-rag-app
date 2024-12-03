package com.spring.rag.repository;

import com.spring.rag.model.Embedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmbeddingRepository extends JpaRepository<Embedding, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO embeddings (id, embedding) VALUES (:id, :embedding)", nativeQuery = true)
    void insertEmbedding(@Param("id") Integer id, @Param("embedding") float[] embedding);
}
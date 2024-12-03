CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS products (
    productid SERIAL PRIMARY KEY,
    productname TEXT NOT NULL,
    productdescription TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS embeddings (
    id SERIAL PRIMARY KEY,
    embedding vector(1024) NOT NULL
);

CREATE INDEX ON embeddings USING HNSW (embedding vector_cosine_ops);
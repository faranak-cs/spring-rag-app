# Building RAG with Spring 
RAG using Spring Boot + Spring AI + PostgreSQL

## Current features
- Followed MVC project structure
- Integrated pgAdmin in docker-compose
- Integrated Ollama for embeddings ONLY from Spring AI
- Integrated Spring Data JPA + raw SQL queries
- Integrated Liquibase for database migrations
- Didn't used VectorStore from Spring AI instead used Spring Data JPA
  
## Setup
1. Install and open Docker desktop  
2. Install Ollama and pull down `mxbai-embed-large` embeddings model
3. Install Liquibase
4. Install Postman
5. Run `RagApplication.java`
6. Open pgAdmin:

```
http://localhost:8081/browser/
```
Get the credentials from `docker-compose` file

## Endpoints
Follow the above steps first. Open Postman client and hit below endpoints:
- To get embeddings of a string
```
http://localhost:8080/spring/ai/embedding/string?message=hello
```
- To generate embeddings of products
```
http://localhost:8080/spring/ai/embedding/products
```
See the logs. 
- To get products based on user query
```
http://localhost:8080/spring/ai/user/query?query=shoes
```
- To get products based on product name itself
```
http://localhost:8080/spring/ai/user/product?productName=Blender
```

## Arch Diagram
![arch_diagram](https://github.com/user-attachments/assets/b7fda6ef-28ef-436a-9d65-07e38d45909f)

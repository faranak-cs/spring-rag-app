# Building RAG with Spring 
RAG using Spring Boot + Spring AI + PostgreSQL

## Current features
- Followed MVC project structure
- Integrated pgAdmin in docker-compose
- Integrated Ollama to generate embeddings using Spring AI
- Integrated Ollama to generate LLM response using Spring AI
- Integrated Spring Data JPA + raw SQL queries
- Integrated Liquibase for database migrations
- Integrated gRPC service to stream LLM response
- Didn't use VectorStore from Spring AI instead used Spring Data JPA
  
## Setup
1. Install and open Docker desktop  
2. Install Ollama
3. Run the project:
```
.\mvnw spring-boot:run
```

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
- To get LLM response based on user query

  POST:
  ```
  http://localhost:8080/spring/ai/llm/generate
  ```
  Body (text):
  ```
  "I want products related to hiking"
  ```
  Response (String):
  ```
  For hiking enthusiasts, I recommend checking out our Backpack and Running Shoes. These two products are perfect for individuals who enjoy spending time outdoors, whether it's for a casual stroll or an intense adventure.
  
  The Backpack is designed with comfort and practicality in mind, making it ideal for carrying all your essentials on a long hike. It features multiple compartments to keep your gear organized, as well as padded shoulder straps for added support.
  
  On the other hand, our Running Shoes are perfect for those who enjoy trail running or need a reliable pair of shoes for their hiking excursions. They offer excellent grip and traction, allowing you to navigate uneven terrain with confidence.
  
  Let me know if you'd like more information on either of these products!
  ```
- To get LLM response based on user query
  ```
  grpc://localhost:9090
  ```

  Message:
  ```
  {
      "query": "I want kitchen items and give me details and prices"
  }
  ```

## Arch Diagram
![arch_diagram](https://github.com/user-attachments/assets/b7fda6ef-28ef-436a-9d65-07e38d45909f)

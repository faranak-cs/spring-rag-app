package com.spring.rag.integrationTests;

import com.spring.rag.integrationTests.containers.LiquibaseContainer;
import com.spring.rag.integrationTests.containers.PostgresContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;

public abstract class BaseIntegrationTest {
    protected static final PostgresContainer postgresContainer;
    protected static final LiquibaseContainer liquibaseContainer;
    static Network network;

    static {
        network = Network.newNetwork();
        postgresContainer = new PostgresContainer(network);
        liquibaseContainer = new LiquibaseContainer(network, postgresContainer);

        postgresContainer.start();
        liquibaseContainer.start();

    }

    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgresContainer::getDriverClassName);
    }

    @AfterAll
    static void teardown() {
        postgresContainer.stop();
        liquibaseContainer.stop();

        postgresContainer.close();
        liquibaseContainer.close();
    }
}

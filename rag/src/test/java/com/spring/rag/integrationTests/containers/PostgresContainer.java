package com.spring.rag.integrationTests.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresContainer.class);
    private static final String IMAGE_VERSION = "pgvector/pgvector:pg16";


    public PostgresContainer(Network network){
        super(DockerImageName.parse(IMAGE_VERSION));
        withNetwork(network);
        withNetworkAliases("postgres");
//        withEnv("LIQUIBASE_URL", postgresContainer.getJdbcUrl());
//        withEnv("LIQUIBASE_USERNAME", postgresContainer.getUsername());
//        withEnv("LIQUIBASE_PASSWORD", postgresContainer.getPassword());
        withLogConsumer(new Slf4jLogConsumer(LOGGER));
        waitingFor(Wait.forLogMessage(".*database system is ready to accept connections*\\n", 1));

    }
}

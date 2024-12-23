package com.spring.rag.integrationTests.containers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class LiquibaseContainer extends GenericContainer<LiquibaseContainer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseContainer.class);
    private static final String IMAGE_VERSION = "liquibase/liquibase:latest";
    private static final String HOST_FILEPATH = "src/test/resources/db-test.changelog/";
    private static final String CONTAINER_FILEPATH = "/changes/postgres";

    public LiquibaseContainer(Network network, PostgresContainer postgresContainer){
        super(DockerImageName.parse(IMAGE_VERSION));
        withNetwork(network);
        withNetworkAliases("liquibase");
        dependsOn(postgresContainer);
        withCopyFileToContainer(MountableFile.forHostPath(HOST_FILEPATH), CONTAINER_FILEPATH);
        withLogConsumer(new Slf4jLogConsumer(LOGGER));
//        waitingFor(Wait.forLogMessage(".*Successfully released change log lock*\\n", 1));
    }
}

package com.ramon.pereira;

import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.lifecycle.Startable;

public abstract class AbstractContainerBaseTest {

  protected static final GenericContainer<?> CUSTOMERAPI;
  protected static final GenericContainer<?> MYSQL_CONTAINER;
  protected static final Network NETWORK = Network.newNetwork();

  static {
    MYSQL_CONTAINER = buildMySqlContainer();
    MYSQL_CONTAINER.start();


    CUSTOMERAPI = buildCustomerApiContainer(MYSQL_CONTAINER);
    CUSTOMERAPI.start();
  }


  private static GenericContainer<?> buildMySqlContainer() {
    return new MySQLContainer<>("mysql:5.7.22")
        .withNetwork(NETWORK)
        .withNetworkAliases("testdb")
        .withDatabaseName("customerdb");
  }

    private static GenericContainer<?> buildCustomerApiContainer(final Startable... dependsOn) {
      return new GenericContainer<>("xavier-test:integration")
          .dependsOn(dependsOn)
          .withNetwork(NETWORK)
          .withEnv("DATASOURCE_USERNAME", "test")
          .withEnv("DATASOURCE_PASSWORD", "test")
          .withEnv("DATASOURCE_URL", "jdbc:mysql://testdb:" + MySQLContainer.MYSQL_PORT + "/customerdb?autoReconnect=true&useSSL=false")
          .withExposedPorts(8080)
          .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("APP_CONTAINER")));
  }

}

package ru.lavrinenko.downloader.fias.util;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.extension.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

public class PostgresSqlExtension implements Extension {

    private static final Logger log = LoggerFactory.getLogger(PostgresSqlExtension.class);

    static {
        PostgreSQLContainer container =
                new PostgreSQLContainer<>("postgres:12.9")
                        .withLogConsumer(new Slf4jLogConsumer(log))  // при необходимости жестко задать порт
                        .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                                new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(48432), new
                                        ExposedPort(5432)))
                        ).withName("test-db"))
                /*.withInitScript("db/scripts/init.sql")*/;

        container.start();

        System.setProperty("spring.datasource.url", container.getJdbcUrl() + "&currentSchema=public");
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
        log.info("getUsername:" + container.getUsername());
        log.info("getPassword:" + container.getPassword());

    }

}

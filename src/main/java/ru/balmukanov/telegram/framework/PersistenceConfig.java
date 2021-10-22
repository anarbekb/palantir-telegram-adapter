package ru.balmukanov.telegram.framework;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.balmukanov.telegram")
@EntityScan("ru.balmukanov.telegram")
public class PersistenceConfig {
}
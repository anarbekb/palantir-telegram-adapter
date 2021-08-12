package ru.balmukanov.telegram.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.balmukanov.telegram.adapter.kafka.palantir.Sender;

@SpringBootApplication(scanBasePackages = "ru.balmukanov.telegram")
public class PalantirTelegramAdapterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PalantirTelegramAdapterApplication.class, args);
        Sender sender = context.getBean(Sender.class);
        sender.send();
    }

}

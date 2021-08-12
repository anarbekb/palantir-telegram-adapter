package ru.balmukanov.telegram.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.balmukanov.telegram")
public class PalantirTelegramAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalantirTelegramAdapterApplication.class, args);
    }

}

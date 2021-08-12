package ru.balmukanov.telegram.framework;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;
import ru.balmukanov.telegram.adapter.kafka.palantir.AdapterMappingException;

@Configuration
@EnableBinding(ChannelBinding.class)
public class StreamConfig {
    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer<Object, Object>> listenerContainerCustomizer() {
        SeekToCurrentErrorHandler handler = new SeekToCurrentErrorHandler();
        handler.addNotRetryableException(AdapterMappingException.class);
        return (container, destinationName, group) -> container.setErrorHandler(handler);
    }
}

package com.example.LibraryService.config;

import com.example.LibraryService.dto.BookDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka

public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;
    @Value("${spring.kafka.consumer.properties.use-type-info-headers}")
    private String useTypeInfoHeaders;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffset;
    @Value("${spring.kafka.consumer.properties.trusted.packages}")
    private String trustedPackages;

    @Bean
    public ConsumerFactory<String, BookDTO> eventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(defaultConfig(), new StringDeserializer(),
                new JsonDeserializer<>(BookDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BookDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventConsumerFactory());
        return factory;
    }

    private Map<String, Object> defaultConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, useTypeInfoHeaders);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffset);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, trustedPackages);
        return props;
    }
}

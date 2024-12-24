package org.example.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        configProps.put(ProducerConfig.RETRIES_CONFIG, 3); // Число повторных попыток отправки
        configProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1000); // Интервал между попытками
        configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 15000); // Тайм-аут запроса
        configProps.put(ProducerConfig.ACKS_CONFIG, "all"); // Уровень подтверждения (все узлы)
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 5); // Ожидание перед отправкой данных (ускоряет при высокой нагрузке)
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // Размер пакета (в байтах)
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // Размер буфера памяти (32 MB)

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

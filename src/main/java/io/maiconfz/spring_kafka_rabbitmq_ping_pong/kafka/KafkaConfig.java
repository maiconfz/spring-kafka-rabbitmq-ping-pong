package io.maiconfz.spring_kafka_rabbitmq_ping_pong.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;

@Configuration
@EnableKafka
public class KafkaConfig {

    public static final String DEFAULT_TOPIC_NAME = "kafkaDefaultTopic";
    public static final String DEFAULT_GROUP_ID = "kafkaDefaultGroup";

    @Bean
    public NewTopic topicDefault() {
        return TopicBuilder.name(DEFAULT_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public ConsumerFactory<String, Msg> msgConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, DEFAULT_GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        final var jsonDeserializer = new JsonDeserializer<>(Msg.class);
        jsonDeserializer.addTrustedPackages("io.maiconfz.spring_kafka_rabbitmq_ping_pong.model");
        jsonDeserializer.setTypeMapper(this.kafkaClassMapper());

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Msg> msgKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Msg> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(msgConsumerFactory());
        return factory;
    }

    @Bean
    public ProducerFactory<String, Msg> msgProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS, "msg:io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg");
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Msg> msgKafkaTemplate() {
        return new KafkaTemplate<>(msgProducerFactory());
    }

    @Bean
    public DefaultJackson2JavaTypeMapper kafkaClassMapper() {
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.addTrustedPackages("io.maiconfz.spring_kafka_rabbitmq_ping_pong.model");
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("msg", Msg.class);
        typeMapper.setIdClassMapping(idClassMapping);

        return typeMapper;
    }
}

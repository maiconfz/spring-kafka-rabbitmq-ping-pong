package io.maiconfz.spring_kafka_rabbitmq_ping_pong.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class KafkaConsumerPublisher {

    @KafkaListener(topics = "kafkaDefaultTopic", groupId = "kafkaDefaultGroup")
    public void receiveMessage(String msg) {
        log.info("Received Message in group foo: " + msg);
    }

    public void publishToRabbitMq(String msg) {
        log.info("msg published: {}", msg);
        // TODO: publish to kafka
    }
}

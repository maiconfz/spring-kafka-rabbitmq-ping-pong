package io.maiconfz.spring_kafka_rabbitmq_ping_pong.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@AllArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, Msg> kafkaTemplate;

    public void publish(Msg msg) {
        log.info("Sending Message: {} ", msg);
        kafkaTemplate.send(KafkaConfig.DEFAULT_TOPIC_NAME, msg);
    }

}

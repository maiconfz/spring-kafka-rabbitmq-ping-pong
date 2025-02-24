package io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@AllArgsConstructor
public class RabbitMqPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(Msg msg) {
        log.info("Sending Message: {} ", msg);
        this.rabbitTemplate.convertAndSend(RabbitMqConfig.DEFAULT_TOPIC_EXCHANGE_NAME,
                RabbitMqConfig.DEFAULT_QUEUE_NAME + ".all", msg);
    }
}

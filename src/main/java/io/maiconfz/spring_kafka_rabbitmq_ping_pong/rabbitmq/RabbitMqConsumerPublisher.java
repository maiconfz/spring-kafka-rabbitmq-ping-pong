package io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class RabbitMqConsumerPublisher {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "rabbitMqDefault", durable = "false"), exchange = @Exchange(value = RabbitMqConfig.TOPIC_EXCHANGE_NAME, ignoreDeclarationExceptions = "true", type = "topic"), key = "rabbitMqDefault.#"))
    public void receiveMessage(String msg) {
        log.info("msg received: {}", msg);
    }

    public void publishToKafka(String msg) {
        log.info("msg published: {}", msg);
        // TODO: publish to kafka
    }
}

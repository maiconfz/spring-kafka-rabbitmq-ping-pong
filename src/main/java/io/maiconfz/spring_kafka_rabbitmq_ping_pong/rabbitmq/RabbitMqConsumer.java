package io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.kafka.KafkaPublisher;
import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;
import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.builder.PingPongMsgBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@AllArgsConstructor
public class RabbitMqConsumer {

    private final KafkaPublisher kafkaPublisher;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitMqConfig.DEFAULT_QUEUE_NAME, durable = "false"), exchange = @Exchange(value = RabbitMqConfig.DEFAULT_TOPIC_EXCHANGE_NAME, ignoreDeclarationExceptions = "true", type = "topic"), key = RabbitMqConfig.DEFAULT_QUEUE_NAME
            + ".#"))
    public void receiveMessage(Msg msg) throws InterruptedException {
        log.info("msg received: {}", msg);
        kafkaPublisher.publish(PingPongMsgBuilder.PING.equals(msg.getText()) ? PingPongMsgBuilder.newPongMsg()
                : PingPongMsgBuilder.newPingMsg());
    }
}

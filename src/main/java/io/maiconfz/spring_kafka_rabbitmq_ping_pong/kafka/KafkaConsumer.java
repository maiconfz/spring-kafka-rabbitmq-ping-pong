package io.maiconfz.spring_kafka_rabbitmq_ping_pong.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;
import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.builder.PingPongMsgBuilder;
import io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq.RabbitMqPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final RabbitMqPublisher rabbitMqPublisher;

    @KafkaListener(topics = KafkaConfig.DEFAULT_TOPIC_NAME, containerFactory = "msgKafkaListenerContainerFactory")
    public void receiveMessage(Msg msg) {
        log.info("Received Message: {} ", msg);
        rabbitMqPublisher.publish(PingPongMsgBuilder.PING.equals(msg.getText()) ? PingPongMsgBuilder.newPongMsg()
                : PingPongMsgBuilder.newPingMsg());
    }

}

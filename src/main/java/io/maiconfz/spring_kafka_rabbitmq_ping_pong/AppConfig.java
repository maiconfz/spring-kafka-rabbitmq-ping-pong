package io.maiconfz.spring_kafka_rabbitmq_ping_pong;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.builder.PingPongMsgBuilder;
import io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq.RabbitMqPublisher;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AppConfig {

    public static final long DEFAULT_WAIT_TIME_BEFORE_MSG_PUBLISH = 300L;

    @Bean
    public CommandLineRunner publishFirstMsg(final RabbitMqPublisher publisher) {
        return args -> publisher.publish(PingPongMsgBuilder.newPingMsg());
    }
}

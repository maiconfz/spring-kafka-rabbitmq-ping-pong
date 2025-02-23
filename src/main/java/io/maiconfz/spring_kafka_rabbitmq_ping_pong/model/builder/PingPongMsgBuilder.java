package io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.builder;

import java.time.LocalDateTime;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PingPongMsgBuilder {

    public static final String PING = "ping";
    public static final String PONG = "pong";

    public static Msg newPingMsg() {
        return build(PING);
    }

    public static Msg newPongMsg() {
        return build(PONG);
    }

    private static Msg build(String text) {
        return Msg.builder()
                .text(text)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

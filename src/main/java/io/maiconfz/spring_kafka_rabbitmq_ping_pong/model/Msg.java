package io.maiconfz.spring_kafka_rabbitmq_ping_pong.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Msg {

    private Long id;
    private String text;
    private LocalDateTime timestamp;

}

package io.maiconfz.spring_kafka_rabbitmq_ping_pong;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.builder.PingPongMsgBuilder;
import io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq.RabbitMqPublisher;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class App {

	public static void main(String[] args) {
		log.info("App starting...");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner publishFirstMsg(final RabbitMqPublisher publisher) {
		return args -> publisher.publish(PingPongMsgBuilder.newPingMsg());
	}

}

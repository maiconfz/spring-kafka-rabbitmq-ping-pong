package io.maiconfz.spring_kafka_rabbitmq_ping_pong;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq.RabbitMqConsumerPublisher;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class App {

	public static void main(String[] args) {
		log.info("App starting...");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner rabbitMqPublishToKafka(final RabbitMqConsumerPublisher publisher) {
		return args -> IntStream.range(0, 2).parallel().forEach(i -> publisher.publishToKafka("Hello Queue " + i));
	}

}

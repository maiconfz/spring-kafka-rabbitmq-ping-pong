package io.maiconfz.spring_kafka_rabbitmq_ping_pong.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.maiconfz.spring_kafka_rabbitmq_ping_pong.model.Msg;

@Configuration
public class RabbitMqConfig {

    public static final String DEFAULT_TOPIC_EXCHANGE_NAME = "rabbitmq-exchange";
    public static final String DEFAULT_QUEUE_NAME = "rabbitMqDefault";

    
	@Bean
	public MessageConverter rabbitMqJsonMessageConverter() {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		converter.setClassMapper(rabbitMqTypeMapper());
		return converter;
	}

	@Bean
	public DefaultJackson2JavaTypeMapper rabbitMqTypeMapper() {
		DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
		typeMapper.setTrustedPackages("io.maiconfz.spring_kafka_rabbitmq_ping_pong.model");
		Map<String, Class<?>> idClassMapping = new HashMap<>();
		idClassMapping.put("msg", Msg.class);
		typeMapper.setIdClassMapping(idClassMapping);
		return typeMapper;
	}

}

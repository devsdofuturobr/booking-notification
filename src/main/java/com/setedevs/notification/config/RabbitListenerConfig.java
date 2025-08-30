package com.setedevs.notification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "broker.retry")
@Configuration
public class RabbitListenerConfig {

    private int maxAttempts;
    private long initialInterval;
    private double multiplier;
    private long maxInterval;


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDefaultRequeueRejected(false);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());

        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .maxAttempts(maxAttempts)
                .backOffOptions(
                        initialInterval,
                        multiplier,
                        maxInterval
                )
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build());

        return factory;
    }
}
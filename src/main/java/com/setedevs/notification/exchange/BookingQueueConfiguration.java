package com.setedevs.notification.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingQueueConfiguration {

    @Bean
    public Queue bookingQueue() {
        return QueueBuilder.durable("booking.notifications")
                .withArgument("x-queue-type", "quorum")
                .build();
    }

    @Bean
    public Binding bookingQueueBinding(Queue bookingQueue, TopicExchange bookingExchange) {
        return BindingBuilder
                .bind(bookingQueue)
                .to(bookingExchange)
                .with("");
    }

}
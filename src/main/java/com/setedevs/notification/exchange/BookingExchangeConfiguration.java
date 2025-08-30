package com.setedevs.notification.exchange;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingExchangeConfiguration {

    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange("booking.exchange", true, false);
    }
}
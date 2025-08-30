package com.setedevs.notification.listener;

import com.setedevs.notification.stream.BookingRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = "booking.notifications")
    public void receiveMessage(BookingRequest request) {
        System.out.println("📢 Nova reserva recebida: " + request);
    }
}
package com.setedevs.notification.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingRequest implements Serializable {
    private String customerName;
    private String customerEmail;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int numberOfGuests;
    private String roomType;
}
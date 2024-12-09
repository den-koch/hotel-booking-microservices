package io.github.denkoch.hotel_booking_spring;

import io.github.denkoch.hotel_booking_spring.service.BookingService;
import io.github.denkoch.hotel_booking_spring.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelBookingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingSpringApplication.class, args);
    }

}

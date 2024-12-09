package io.github.denkoch.hotel_booking_spring.config;

import io.github.denkoch.hotel_booking_spring.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OnStartUpRunner implements ApplicationRunner {


    private final HistoryService historyService;

    public OnStartUpRunner(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        historyService.archiveAndDeleteOutdatedBookings();
    }

}

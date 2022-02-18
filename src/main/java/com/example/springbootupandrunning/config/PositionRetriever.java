package com.example.springbootupandrunning.config;

import com.example.springbootupandrunning.domain.Aircraft;
import com.example.springbootupandrunning.repository.AircraftRepository;
import com.example.springbootupandrunning.socket.WebSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Configuration
public class PositionRetriever {
    private final AircraftRepository aircraftRepository;
    private final WebSocketHandler webSocketHandler;

    @Bean
    Consumer<List<Aircraft>> retrieveAircraftPositions() {
        return acList -> {
            aircraftRepository.deleteAll();
            aircraftRepository.saveAll(acList);
            sendPositions();
//            aircraftRepository.findAll().forEach(System.out::println);
        };
    }

    private void sendPositions() {
        if (aircraftRepository.count() > 0) {
            for (WebSocketSession sessionInList : webSocketHandler.getSessionList()) {
                try {
                    sessionInList.sendMessage(
                            new TextMessage((aircraftRepository.findAll().toString()))
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

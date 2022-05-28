package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.util.CsvUtilFile;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CsvUtilFile csvUtilFile;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, CsvUtilFile csvUtilFile) {
        this.playerRepository = playerRepository;
        this.csvUtilFile = csvUtilFile;
    }

    public void saveAll() {
        Flux<Player> players = csvUtilFile.getPlayers();
        players.doOnNext(playerRepository::save)
                .onErrorResume(e -> Mono.error(new InterruptedException("")));
    }
}

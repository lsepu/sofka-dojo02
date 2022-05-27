package com.example.demo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import reactor.core.publisher.Flux;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtilFile {
    private CsvUtilFile(){}

    public static Flux<Player> getPlayers(){
        var uri =  CsvUtilFile.class.getClassLoader().getResource("data.csv");
        List<Player> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(uri.getFile()))) {
            List<String[]> registers = reader.readAll();

            List<Player> registerMap =  registers.stream()
                    .map( player -> {
                        return new Player(
                                Integer.parseInt(player[0].trim()),
                                player[1],
                                Integer.parseInt(Optional.of(player[2].trim()).filter(h -> !h.isBlank()).orElse("0")),
                                player[3],
                                player[4],
                                Integer.parseInt(player[5].trim()),
                                Integer.parseInt(player[6].trim()),
                                player[7]
                                );
                    }).collect(Collectors.toList());

            Flux<Player> fluxPlayer = Flux.fromIterable(registerMap);

            return fluxPlayer;

          /*  registers.forEach(strings -> list.add(new Player(
                    Integer.parseInt(strings[0].trim()),
                    strings[1],
                    Integer.parseInt(Optional.of(strings[2].trim()).filter(h -> !h.isBlank()).orElse("0")),
                    strings[3],
                    strings[4],
                    Integer.parseInt(strings[5].trim()),
                    Integer.parseInt(strings[6].trim()),
                    strings[7]
            ))); */

           //return registerMap;

        } catch (IOException | CsvException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

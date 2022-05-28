package com.example.demo.util;

import com.example.demo.model.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CsvUtilFile {

    private static final Logger Log = LoggerFactory.getLogger(CsvUtilFile.class);

    private CsvUtilFile() {
    }

//    public static List<Player> getPlayers(){
//        var uri =  CsvUtilFile.class.getClassLoader().getResource("data.csv");
//        List<Player> list = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new FileReader(uri.getFile()))) {
//            List<String[]> registers = reader.readAll();
//            registers.forEach(strings -> list.add(new Player(
//                    Integer.parseInt(strings[0].trim()),
//                    strings[1],
//                    Integer.parseInt(Optional.of(strings[2].trim()).filter(h -> !h.isBlank()).orElse("0")),
//                    strings[3],
//                    strings[4],
//                    Integer.parseInt(strings[5].trim()),
//                    Integer.parseInt(strings[6].trim()),
//                    strings[7]
//            )));
//           return list;
//
//        } catch (IOException | CsvException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//    }

    public Flux<Player> getPlayers() {
        var uri = CsvUtilFile.class.getClassLoader().getResource("data.csv");
        List<Player> list = new ArrayList<>();
        try {
            assert uri != null;
            CSVReader reader = new CSVReader(new FileReader(uri.getFile()));
            List<Player> players = reader.readAll().stream()
                    .map(lines -> new Player(
                                    Integer.parseInt(lines[0].trim()),
                                    lines[1],
                                    Integer.parseInt(Optional.of(lines[2].trim()).filter(h -> !h.isBlank())
                                            .orElse("0")),
                                    lines[3],
                                    lines[4],
                                    Integer.parseInt(lines[5].trim()),
                                    Integer.parseInt(lines[6].trim()),
                                    lines[7]
                            )
                    ).collect(Collectors.toUnmodifiableList());
            return Flux.fromIterable(list);
        } catch (IOException | CsvException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {
        getPlayers();
    }
}

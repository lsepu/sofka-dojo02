package services;

import com.example.demo.CsvUtilFile;
import com.example.demo.Player;
import reactor.core.publisher.Flux;

public class PlayerService {


    public Flux<Player> filtrarJugadoresMayoresA35(){
        //Flux<Player> list = Flux.just();
        Flux<Player> list = CsvUtilFile.getPlayers();
        return list.filter(player -> player.getAge() > 34 );
    }

}

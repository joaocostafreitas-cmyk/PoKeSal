package poke;

import model.PokeSal;
import model.TipoElemental;

import java.util.ArrayList;
import java.util.List;

public class PokemonRepository {

    public static List<PokeSal> poker = new ArrayList<>();

    static {
        // Pokemon / HP / ATK / DEF / SPD / TipoElemental
        poker.add(new PokeSal("BulbaSal", 200, 100, 50, 25, TipoElemental.PLANTA));
        poker.add(new PokeSal("CharSal", 200, 100, 50, 25, TipoElemental.FOGO));
        poker.add(new PokeSal("SquirtSal", 200, 100, 50, 25, TipoElemental.AGUA));
    }
}
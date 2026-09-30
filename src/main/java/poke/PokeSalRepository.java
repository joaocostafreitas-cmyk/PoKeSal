package poke;

import java.util.ArrayList;
import java.util.List;
import model.PokeSal;
import model.TipoElemental;

/** Repositório dos Pokémon Sal disponíveis no jogo. */
public class PokeSalRepository {
  
  public static List<PokeSal> poker = new ArrayList<>();

  static {
    // Pokemon / HP / ATK / DEF / SPD / TipoElemental
    poker.add(new PokeSal("BulbaSal", 200, 100, 50, 25, TipoElemental.PLANTA));
    poker.add(new PokeSal("CharSal", 200, 100, 50, 25, TipoElemental.FOGO));
    poker.add(new PokeSal("SquirtSal", 200, 100, 50, 25, TipoElemental.AGUA));
    poker.add(new PokeSal("ChikoSal", 200, 100, 50, 25, TipoElemental.PLANTA));
    poker.add(new PokeSal("CyndaSal", 200, 100, 50, 25, TipoElemental.FOGO));
    poker.add(new PokeSal("TotoSal", 200, 100, 50, 25, TipoElemental.AGUA));
  }

  /**
 * Cria uma cópia do Pokémon Sal escolhido.
 */
  public static PokeSal criarCopia(int escolha) {

    PokeSal original = poker.get(escolha);
    return new PokeSal(original.getPokeSal(), original.getHp(), original.getAtk(),
          original.getDef(), original.getSpd(), original.getTipoElemental()
    );
  }
}
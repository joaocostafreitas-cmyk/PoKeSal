package poke;

import java.util.ArrayList;
import java.util.List;
import model.Golpe;
import model.PokeSal;
import model.TipoElemental;

/** Repositório dos Pokémon Sal disponíveis no jogo. */
public class PokeSalRepository {
  
  public static List<PokeSal> poker = new ArrayList<>();

  static {
    // Pokemon / HP / ATK / DEF / SPD / TipoElemental / Golpe (nome, precisão)
    poker.add(new PokeSal("BulbaSal", 200, 100, 50, 25, TipoElemental.PLANTA,
        new Golpe("Chicote de Vinha", 95)));
    poker.add(new PokeSal("CharSal", 200, 100, 50, 25, TipoElemental.FOGO,
        new Golpe("Brasa", 90)));
    poker.add(new PokeSal("SquirtSal", 200, 100, 50, 25, TipoElemental.AGUA,
        new Golpe("Jato de Água", 100)));
    poker.add(new PokeSal("ChikoSal", 200, 100, 50, 25, TipoElemental.PLANTA,
        new Golpe("Folha Navalha", 95)));
    poker.add(new PokeSal("CyndaSal", 200, 100, 50, 25, TipoElemental.FOGO,
        new Golpe("Lança-Chamas", 85)));
    poker.add(new PokeSal("TotoSal", 200, 100, 50, 25, TipoElemental.AGUA,
        new Golpe("Onda", 90)));
  }

 
  public static PokeSal criarCopia(int escolha) {

    PokeSal original = poker.get(escolha);
    return new PokeSal(original.getPokeSal(), original.getHp(), original.getAtk(),
          original.getDef(), original.getSpd(), original.getTipoElemental(),
          original.getGolpe()
    );
  }
}
package poke;

import java.util.ArrayList;
import java.util.List;
import model.Acessorio;
import model.AtributoBonus;



public class AcessorioRepository {
  public static  List<Acessorio> acessorios = new ArrayList<>();

  static {
    acessorios.add(new Acessorio("Bandana De Fogo", AtributoBonus.ATAQUE, 15));
    acessorios.add(new Acessorio("Escudo De água", AtributoBonus.DEFESA, 25));
    acessorios.add(new Acessorio("Botas de velocidade", AtributoBonus.VELOCIDADE, 15));
  }
}

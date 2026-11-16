package model;


public final class RegrasBatalha {

  private RegrasBatalha() {
  }

 
  public static boolean agePrimeiro(PokeSal primeiro, PokeSal segundo) {
    return primeiro.getSpdComStatus() >= segundo.getSpdComStatus();
  }

 
  public static int calcularDano(PokeSal atacante, PokeSal defensor, Terreno terreno) {
    double efetividade = atacante.getTipoElemental().efetividadeContra(defensor.getTipoElemental());
    int dano = atacante.getAkcComStatus() - defensor.getDefComAcessorio();

    if (dano < 1) {
      dano = 1;
    }

    double bonus = terreno.bonusDano(atacante.getTipoElemental());
    return (int) (dano * efetividade * bonus);
  }

 
  public static StatusEfeito statusPorGolpe(PokeSal atacante, PokeSal defensor) {
    boolean superEfetivo =
        atacante.getTipoElemental().efetividadeContra(defensor.getTipoElemental()) > 1.0;
    boolean semStatus = defensor.getStatus() == StatusEfeito.NENHUM;

    if (superEfetivo && semStatus && defensor.getHp() > 0) {
      return atacante.getTipoElemental().statusQueAplica();
    }
    return StatusEfeito.NENHUM;
  }

  
  public static int calcularCuraTerreno(PokeSal pokemon, Terreno terreno, int hpMaximo) {
    if (pokemon.getHp() <= 0) {
      return 0;
    }
    return (int) (hpMaximo * terreno.recuperacaoHp(pokemon.getTipoElemental()));
  }

 
  public static int calcularDanoStatus(PokeSal pokemon, int hpMaximo) {
    if (pokemon.getStatus() == StatusEfeito.QUEIMADO) {
      return (int) (pokemon.getHp() * 0.05);
    }
    if (pokemon.getStatus() == StatusEfeito.ENVENENADO) {
      int turnosEnvenenado = pokemon.avancarTurnoStatus();
      return (int) (hpMaximo * 0.05 * turnosEnvenenado);
    }
    return 0;
  }
}
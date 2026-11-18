package model;

public final class RegrasBatalha {

  private static final int PRECISAO_MAXIMA = 100;
  private static final int CHANCE_CRITICO_UM_EM = 16;
  private static final double MULTIPLICADOR_CRITICO = 1.5;

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

  public static ResultadoGolpe resolverGolpe(PokeSal atacante, PokeSal defensor,
      Terreno terreno, GeradorAleatorio gerador) {
    boolean acertou = gerador.proximoInt(PRECISAO_MAXIMA) < atacante.getGolpe().getPrecisao();

    if (!acertou) {
      return new ResultadoGolpe(false, false, 0);
    }

    boolean critico = gerador.proximoInt(CHANCE_CRITICO_UM_EM) == 0;
    int dano = calcularDano(atacante, defensor, terreno);

    if (critico) {
      dano = (int) (dano * MULTIPLICADOR_CRITICO);
    }

    return new ResultadoGolpe(true, critico, dano);
  }

public static StatusEfeito statusPorGolpe(
    PokeSal atacante,
    PokeSal defensor
) {
  boolean superEfetivo =
      atacante.getTipoElemental()
          .efetividadeContra(defensor.getTipoElemental()) > 1.0;

  boolean semStatus =
      defensor.getStatus() == StatusEfeito.NENHUM;

  if (!superEfetivo || !semStatus || defensor.getHp() <= 0) {
    return StatusEfeito.NENHUM;
  }

  StatusEfeito novoStatus =
      atacante.getTipoElemental().statusQueAplica();

  boolean statusBloqueado =
      defensor.isImuneAQueimadoEEnvenenado()
          && (
              novoStatus == StatusEfeito.QUEIMADO
                  || novoStatus == StatusEfeito.ENVENENADO
          );

  if (statusBloqueado) {
    return StatusEfeito.NENHUM;
  }

  return novoStatus;
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
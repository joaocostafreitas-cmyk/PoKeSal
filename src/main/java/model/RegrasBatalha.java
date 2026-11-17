package model;

/** Regras de cálculo da batalha, sem leitura de teclado nem impressão no console. */
public final class RegrasBatalha {

  private static final int PRECISAO_MAXIMA = 100;
  private static final int CHANCE_CRITICO_UM_EM = 16;
  private static final double MULTIPLICADOR_CRITICO = 1.5;

  private RegrasBatalha() {
  }

  /** Indica se o primeiro pokémon age antes do segundo (maior SPD; empate favorece o primeiro). */
  public static boolean agePrimeiro(PokeSal primeiro, PokeSal segundo) {
    return primeiro.getSpdComStatus() >= segundo.getSpdComStatus();
  }

  /** Calcula o dano de um golpe, considerando defesa, tipo e terreno (mínimo de 1 antes dos multiplicadores). */
  public static int calcularDano(PokeSal atacante, PokeSal defensor, Terreno terreno) {
    double efetividade = atacante.getTipoElemental().efetividadeContra(defensor.getTipoElemental());
    int dano = atacante.getAkcComStatus() - defensor.getDefComAcessorio();

    if (dano < 1) {
      dano = 1;
    }

    double bonus = terreno.bonusDano(atacante.getTipoElemental());
    return (int) (dano * efetividade * bonus);
  }

  /**
   * Resolve um golpe: primeiro sorteia o acerto (precisão); se acertou, sorteia o crítico.
   * Se errar, o dano é zero e o sorteio do crítico não acontece.
   */
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

  /** Calcula o dano de status do fim do turno. Para veneno, avança o contador de turnos. */
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
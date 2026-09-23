package model;

public enum Terreno {
    ASFALTO_QUENTE,
    POCA_CHUVA,
    CANTEIRO_CENTRAL,
    NENHUM;

  public double bonusDano(TipoElemental tipo) {

    if (this == ASFALTO_QUENTE && tipo == TipoElemental.FOGO) {
      return 1.15;
    }
    if (this == POCA_CHUVA && tipo == TipoElemental.AGUA) {
      return 1.10;
    }
    return 1.0;
  }
  public double recuperacaoHp(TipoElemental tipo) {

    if (this == CANTEIRO_CENTRAL && tipo == TipoElemental.PLANTA) {
      return 0.05;
    }
    return 0.0;
  }
}
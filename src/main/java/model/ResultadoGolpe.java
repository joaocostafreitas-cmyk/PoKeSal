package model;


public final class ResultadoGolpe {

  private final boolean acertou;
  private final boolean critico;
  private final int dano;

  public ResultadoGolpe(boolean acertou, boolean critico, int dano) {
    this.acertou = acertou;
    this.critico = critico;
    this.dano = dano;
  }

  public boolean isAcertou() {
    return acertou;
  }

  public boolean isCritico() {
    return critico;
  }

  public int getDano() {
    return dano;
  }
}
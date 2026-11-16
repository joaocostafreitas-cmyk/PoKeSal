package model;

public enum TipoElemental {
    FOGO, AGUA, PLANTA;

  public double efetividadeContra(TipoElemental defensor) {
    if (this == AGUA && defensor == FOGO) {
      return 2.0;
    } 
    if (this == PLANTA && defensor == AGUA) {
      return 2.0;
    }
    if (this == FOGO && defensor == AGUA) {
      return 0.5;
    }
    if (this == FOGO && defensor == PLANTA) {
      return 2.0;
    }
    if (this == AGUA && defensor == PLANTA) {
      return 0.5;
    }
    if (this == PLANTA && defensor == FOGO) {
      return 0.5;
    }
    return 1.0;
  }
 public StatusEfeito statusQueAplica() {
    if (this == FOGO) {
      return StatusEfeito.QUEIMADO;
    }
    if (this == PLANTA) {
      return StatusEfeito.ENVENENADO;
    }
    return StatusEfeito.PARALISADO;
  }
}
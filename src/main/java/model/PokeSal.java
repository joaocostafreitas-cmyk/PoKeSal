package model;

public class PokeSal {

  private final String pokeSal;

  private int hp;
  private int atk;
  private int def;
  private int spd;

  private TipoElemental tipoElemental;

  private final Golpe golpe;
  private Acessorio acessorio;

  private StatusEfeito status = StatusEfeito.NENHUM;
  private int turnosComStatus = 0;

  /*
   * Nova regra:
   * depois de perder duas rodadas consecutivas,
   * o Pokésal fica imune aos status QUEIMADO e ENVENENADO.
   */
  private int derrotasConsecutivas = 0;
  private boolean imuneAQueimadoEEnvenenado = false;

  public PokeSal(
      String pokemon,
      int hp,
      int atk,
      int def,
      int spd,
      TipoElemental tipoElemental
  ) {
    this(
        pokemon,
        hp,
        atk,
        def,
        spd,
        tipoElemental,
        Golpe.BASICO
    );
  }

  public PokeSal(
      String pokemon,
      int hp,
      int atk,
      int def,
      int spd,
      TipoElemental tipoElemental,
      Golpe golpe
  ) {
    this.pokeSal = pokemon;
    this.hp = hp;
    this.atk = atk;
    this.def = def;
    this.spd = spd;
    this.tipoElemental = tipoElemental;
    this.golpe = golpe;
  }

  public String getPokeSal() {
    return pokeSal;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getAtk() {
    return atk;
  }

  public void setAtk(int atk) {
    this.atk = atk;
  }

  public int getDef() {
    return def;
  }

  public void setDefesa(int def) {
    this.def = def;
  }

  public int getSpd() {
    return spd;
  }

  public void setSpd(int spd) {
    this.spd = spd;
  }

  public TipoElemental getTipoElemental() {
    return tipoElemental;
  }

  public Golpe getGolpe() {
    return golpe;
  }

  public void setAcessorio(Acessorio acessorio) {
    this.acessorio = acessorio;
  }

  public Acessorio getAcessorio() {
    return acessorio;
  }

  public StatusEfeito getStatus() {
    return status;
  }

  public void setStatus(StatusEfeito status) {
    this.status = status;
    this.turnosComStatus = 0;
  }

  public int avancarTurnoStatus() {
    turnosComStatus++;
    return turnosComStatus;
  }

  public int getAkcComStatus() {
    int atkBase = status == StatusEfeito.QUEIMADO
        ? (int) (atk * 0.5)
        : atk;

    if (acessorio != null
        && acessorio.getAtributo() == AtributoBonus.ATAQUE) {
      atkBase += acessorio.getValorBonus();
    }

    return atkBase;
  }

  public int getSpdComStatus() {
    int spdBase = status == StatusEfeito.PARALISADO
        ? spd / 2
        : spd;

    if (acessorio != null
        && acessorio.getAtributo() == AtributoBonus.VELOCIDADE) {
      spdBase += acessorio.getValorBonus();
    }

    return spdBase;
  }

  public int getDefComAcessorio() {
    if (acessorio != null
        && acessorio.getAtributo() == AtributoBonus.DEFESA) {
      return def + acessorio.getValorBonus();
    }

    return def;
  }

  
  public void registrarDerrotaNaRodada() {
    derrotasConsecutivas++;

    if (derrotasConsecutivas >= 2) {
      imuneAQueimadoEEnvenenado = true;
    }
  }

public void registrarVitoriaNaRodada() {
    derrotasConsecutivas = 0;
  }

  
  public boolean isImuneAQueimadoEEnvenenado() {
    return imuneAQueimadoEEnvenenado;
  }

 
  public int getDerrotasConsecutivas() {
    return derrotasConsecutivas;
  }

 
  public void resetarResistenciaDeStatus() {
    derrotasConsecutivas = 0;
    imuneAQueimadoEEnvenenado = false;
  }

  @Override
public String toString() {
  return pokeSal + " [HP=" + hp + ", ATK=" + getAkcComStatus()
      + ", DEF=" + getDefComAcessorio() + ", SPD=" + getSpdComStatus()
      + ", Tipo=" + tipoElemental + ", Golpe=" + golpe + "]";
}
}

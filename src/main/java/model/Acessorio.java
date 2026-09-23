package model;

public class Acessorio {

  private String nome;
  private AtributoBonus atributo;
  private int valorBonus;

  public Acessorio(String nome,  AtributoBonus atributo, int valorBonus) {
    this.nome = nome;
    this.atributo = atributo;
    this.valorBonus = valorBonus;
  }

  public String getnome() {
    return nome;
  }

  public AtributoBonus getAtributo() {
    return atributo;
  }

  public int getValorBonus() {
    return valorBonus;
  }

  @Override
    public String toString() {
    return nome + " (+" + valorBonus + " " + atributo + ")";
  }
}

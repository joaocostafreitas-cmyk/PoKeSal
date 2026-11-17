package model;


public final class Golpe {

  public static final Golpe BASICO = new Golpe("Ataque Básico", 100);

  private final String nome;
  private final int precisao;

 
  public Golpe(String nome, int precisao) {
    if (precisao < 0 || precisao > 100) {
      throw new IllegalArgumentException("A precisão deve estar entre 0 e 100.");
    }
    this.nome = nome;
    this.precisao = precisao;
  }

  public String getNome() {
    return nome;
  }

  public int getPrecisao() {
    return precisao;
  }

  @Override
  public String toString() {
    return nome + " (precisão " + precisao + ")";
  }
}
package model;

public class Item {
  private String nome; 
  private int valorCura;
  private boolean removeStatus;

  public Item(String nome, int valorCura) {
    this(nome, valorCura, false);
  }

  public Item(String nome, int valorCura, boolean removeStatus) {
    this.nome = nome;
    this.valorCura = valorCura;
    this.removeStatus = removeStatus;
  }

  public String getnome() {
    return nome;
  }

  public void usar(PokeSal pokeSal, int hpMaximo) {
    int novohp = Math.min(hpMaximo, pokeSal.getHp() + valorCura);
    pokeSal.setHp(novohp);

    if (removeStatus) {
      pokeSal.setStatus(StatusEfeito.NENHUM);
    }
  }

  @Override
  public String toString() {
    if (removeStatus) {
      return nome + " (remove status)";
    }
    return nome + " (+" + valorCura + " HP)";
  }

}
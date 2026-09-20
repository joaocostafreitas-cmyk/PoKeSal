package model;

public class Item {
    private String nome;
    private int valorCura;

    public Item(String nome, int valorCura){
        this.nome = nome;
        this.valorCura= valorCura;

    }
    public String getnome(){
        return nome;
      }
    public void usar(PokeSal pokeSal, int hpMaximo){
        int novohp= Math.min(hpMaximo, pokeSal.getHP() + valorCura);
        pokeSal.setHP(novohp);
    }
    @Override
    public String toString(){
        return nome + " (+" + valorCura + " HP)";
    }

}



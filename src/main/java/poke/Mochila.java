package poke;

import model.Item;
import model.PokeSal;

public class Mochila {

  private static final int LIMITE_ITENS_POR_BATALHA = 2;

  private int itensUsados = 0;

  public boolean usarItem(Item item, PokeSal pokeSal, int hpMaximo) {

    if (itensUsados >= LIMITE_ITENS_POR_BATALHA) {
      System.out.println("Você já usou o limite máximo de 2 itens nesta batalha.");
      return false;
    }

    item.usar(pokeSal, hpMaximo);
    itensUsados++;

    System.out.println(
        pokeSal.getPokeSal() + " usou " + item.getnome() + "!");

    System.out.println("Itens usados: " + itensUsados + "/" + LIMITE_ITENS_POR_BATALHA);
    return true;

  }

  public int getItensUsados() {
    return itensUsados;
  }

  public boolean podeUsarItem() {
    return itensUsados < LIMITE_ITENS_POR_BATALHA;
  }

  public static Mochila[] criarMochilas(int quantidadeJogadores) {

    Mochila[] mochilas = new Mochila[quantidadeJogadores];

    for (int i = 0; i < quantidadeJogadores; i++) {
      mochilas[i] = new Mochila();
    }

    return mochilas;
  }
}


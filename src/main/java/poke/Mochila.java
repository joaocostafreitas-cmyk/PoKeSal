package poke;

import model.Item;
import model.PokeSal;

public class Mochila {

  public static final int LIMITE_ITENS_POR_BATALHA = 2;

  private int itensUsados = 0;

  /** Usa o item se o limite não foi atingido; devolve se o item foi usado. */
  public boolean usarItem(Item item, PokeSal pokeSal, int hpMaximo) {

    if (itensUsados >= LIMITE_ITENS_POR_BATALHA) {
      return false;
    }

    item.usar(pokeSal, hpMaximo);
    itensUsados++;
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
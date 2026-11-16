package poke;

import java.util.Scanner;
import model.Item;
import model.PokeSal;
import model.RegrasBatalha;
import model.StatusEfeito;
import model.Terreno;

public class Batalha {

  private PokeSal pokemon1;
  private PokeSal pokemon2;

  private int hpInicial1;
  private int hpInicial2;

  private int vitoriasTreinador1 = 0;
  private int vitoriasTreinador2 = 0;

  private Mochila mochila1;
  private Mochila mochila2;

  private final Scanner sc;

  public Batalha(PokeSal pokemon1, PokeSal pokemon2, Mochila mochila1, Mochila mochila2) {
    this(pokemon1, pokemon2, mochila1, mochila2, new Scanner(System.in));
  }

  public Batalha(PokeSal pokemon1, PokeSal pokemon2, Mochila mochila1, Mochila mochila2,
      Scanner sc) {
    this.pokemon1 = pokemon1;
    this.pokemon2 = pokemon2;

    this.mochila1 = mochila1;
    this.mochila2 = mochila2;

    this.hpInicial1 = pokemon1.getHp();
    this.hpInicial2 = pokemon2.getHp();

    this.sc = sc;
  }

  public void iniciarBatalha() {
    Terreno[] terrenos = { Terreno.ASFALTO_QUENTE, Terreno.POCA_CHUVA,
        Terreno.CANTEIRO_CENTRAL };

    int rodada = 0;

    while (vitoriasTreinador1 < 2 && vitoriasTreinador2 < 2) {
      Terreno terrenoAtual = terrenos[rodada % terrenos.length];
      rodada++;

      System.out.println("\n--- Rodada " + rodada + " | Terreno: " + terrenoAtual + " ---");

      pokemon1.setHp(hpInicial1);
      pokemon2.setHp(hpInicial2);
      pokemon1.setStatus(StatusEfeito.NENHUM);
      pokemon2.setStatus(StatusEfeito.NENHUM);

      jogarRodada(terrenoAtual);
    }

    System.out.println("\n=== FIM DA BATALHA ===");

    if (vitoriasTreinador1 == 2) {
      System.out.println("Treinador 1 venceu com o " + pokemon1.getPokeSal() + "!");
    } else {
      System.out.println("Treinador 2 venceu com o " + pokemon2.getPokeSal() + "!");
    }
  }

  private void jogarRodada(Terreno terreno) {
    while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
      PokeSal primeiro;
      PokeSal segundo;

      Mochila mochilaPrimeiro;
      Mochila mochilaSegundo;

      if (RegrasBatalha.agePrimeiro(pokemon1, pokemon2)) {
        primeiro = pokemon1;
        segundo = pokemon2;

        mochilaPrimeiro = mochila1;
        mochilaSegundo = mochila2;
      } else {
        primeiro = pokemon2;
        segundo = pokemon1;

        mochilaPrimeiro = mochila2;
        mochilaSegundo = mochila1;
      }

      boolean usouItem = escolherAcao(primeiro, mochilaPrimeiro);

      if (!usouItem) {
        atacar(primeiro, segundo, terreno);
      }

      if (segundo.getHp() <= 0) {
        break;
      }

      boolean segundoUsouItem = escolherAcao(segundo, mochilaSegundo);

      if (!segundoUsouItem) {
        atacar(segundo, primeiro, terreno);
      }

      aplicarEfeitosDeStatus(primeiro, hpMaximoDe(primeiro));
      aplicarEfeitosDeStatus(segundo, hpMaximoDe(segundo));

      aplicarRecuperacaoTerreno(primeiro, terreno, hpMaximoDe(primeiro));
      aplicarRecuperacaoTerreno(segundo, terreno, hpMaximoDe(segundo));
    }

    if (pokemon1.getHp() > 0) {
      vitoriasTreinador1++;
      System.out.println("Vencedor da rodada: " + pokemon1.getPokeSal());
    } else {
      vitoriasTreinador2++;
      System.out.println("Vencedor da rodada: " + pokemon2.getPokeSal());
    }

    System.out.println("Placar -> Treinador 1: " + vitoriasTreinador1 
        + " x " + vitoriasTreinador2 + " : Treinador 2");
  }

  private int hpMaximoDe(PokeSal pokemon) {
    return pokemon == pokemon1 ? hpInicial1 : hpInicial2;
  }

  private boolean escolherAcao(PokeSal pokemon, Mochila mochila) {
    System.out.println("\nTurno de " + pokemon.getPokeSal());
    System.out.println("1 - Atacar");
    System.out.println("2 - Usar item");

    int escolha = sc.nextInt();

    if (escolha == 2) {
      if (!mochila.podeUsarItem()) {
        System.out.println("Você já usou os 2 itens permitidos nesta batalha.");
        return false;
      }

      Item item = escolherItem();
      return mochila.usarItem(item, pokemon, pokemon == pokemon1 ? hpInicial1 : hpInicial2);
    }

    return false;
  }

  private Item escolherItem() {
    System.out.println("\nItens disponíveis:");

    for (int i = 0; i < ItemRepository.itens.size(); i++) {
      System.out.println((i + 1) + " - " + ItemRepository.itens.get(i));
    }

    while (true) {
      int escolha = sc.nextInt();

      if (escolha >= 1 && escolha <= ItemRepository.itens.size()) {
        return ItemRepository.itens.get(escolha - 1);
      }

      System.out.println("Opção inválida. Escolha novamente.");
    }
  }

  private void atacar(PokeSal atacante, PokeSal defensor, Terreno terreno) {
    int dano = RegrasBatalha.calcularDano(atacante, defensor, terreno);

    defensor.setHp(Math.max(0, defensor.getHp() - dano));

    System.out.println(atacante.getPokeSal() + " atacou " + defensor.getPokeSal() 
        + " e causou " + dano + " de dano! HP restante: " + defensor.getHp());

    aplicarStatusPorGolpe(atacante, defensor);
  }

  private void aplicarStatusPorGolpe(PokeSal atacante, PokeSal defensor) {
    StatusEfeito novoStatus = RegrasBatalha.statusPorGolpe(atacante, defensor);

    if (novoStatus != StatusEfeito.NENHUM) {
      defensor.setStatus(novoStatus);
      System.out.println(defensor.getPokeSal() + " ficou " + novoStatus + "!");
    }
  }

  private void aplicarRecuperacaoTerreno(PokeSal pokemon, Terreno terreno, int hpMaximo) {
    int cura = RegrasBatalha.calcularCuraTerreno(pokemon, terreno, hpMaximo);

    if (cura > 0) {
      pokemon.setHp(Math.min(hpMaximo, pokemon.getHp() + cura));
      System.out.println(pokemon.getPokeSal() 
          + " recuperou " + cura + " de HP no Canteiro Central!");
    }
  }

  private void aplicarEfeitosDeStatus(PokeSal pokemon, int hpMaximo) {
    if (pokemon.getHp() <= 0) {
      return;
    }

    int dano = RegrasBatalha.calcularDanoStatus(pokemon, hpMaximo);

    if (dano > 0) {
      pokemon.setHp(Math.max(0, pokemon.getHp() - dano));
      String causa = pokemon.getStatus() == StatusEfeito.QUEIMADO ? "da queimadura" : "do veneno";
      System.out.println(pokemon.getPokeSal() + " sofreu " + dano + " de dano " + causa + "!");
    }
  }
}
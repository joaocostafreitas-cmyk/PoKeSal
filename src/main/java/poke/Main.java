package poke;

import java.util.Scanner;

import model.PokeSal;

public class Main {

  public static void main(String[] args) {

    Treinador treinador = new Treinador();
    ModoAleatorio modoAleatorio = new ModoAleatorio();

    Scanner sc = new Scanner(System.in);

    int escolha;
    PokeSal[] pokeSals;
    Mochila[] mochilas;

    System.out.println("Escolha 1 para Modo Normal e 2 para Modo Aleatório:");

    escolha = sc.nextInt();

    if (escolha == 1) {

      pokeSals = treinador.escolhaSeuPokeSal();

      treinador.escolhaAcessorio(pokeSals);

            
      mochilas = Mochila.criarMochilas(2);

    } else if (escolha == 2) {

            
      pokeSals = modoAleatorio.sortearPokeSal();

      modoAleatorio.sortearAcessorio(pokeSals);

      mochilas = Mochila.criarMochilas(2);

    } else {

      System.out.println("Opção inválida.");
      sc.close();
      return;
    }

    System.out.println("\n=== INICIANDO BATALHA ===");

    Batalha batalha = new Batalha(pokeSals[0], pokeSals[1], mochilas[0], mochilas[1]);

    batalha.iniciarBatalha();

    sc.close();
  }
}

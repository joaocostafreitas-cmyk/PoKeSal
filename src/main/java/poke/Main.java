package poke;

import java.util.Scanner;
import model.GeradorAleatorio;
import model.PokeSal;
import model.RegistroBatalha;


public class Main {

  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    GeradorAleatorio gerador = new GeradorAleatorioPadrao();
    RegistroBatalha registro = new RegistroBatalhaConsole(System.out);

    Treinador treinador = new Treinador(sc);
    ModoAleatorio modoAleatorio = new ModoAleatorio(gerador);

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

    Batalha batalha = new Batalha(pokeSals[0], pokeSals[1], mochilas[0], mochilas[1],
        sc, gerador, registro);

    batalha.iniciarBatalha();

    sc.close();
  }
}
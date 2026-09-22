package poke;

import java.util.Scanner;
import model.PokeSal;

public class Main {

  public static void main(String[] args) {

    Treinador treinador = new Treinador();
    Scanner sc = new Scanner(System.in);

    int escolha;
    PokeSal[] pokeSals;

    System.out.println("Escolha 1 para Modo Normal e 2 para modo aleatorio ");
    escolha = sc.nextInt();

    if (escolha == 1) {

      pokeSals = treinador.escolhaSeuPokeSal();

      System.out.println("Pokemon do Jogador 1: " + pokeSals[0].getPokeSal());
      System.out.println("Pokemon do Jogador 2: " + pokeSals[1].getPokeSal());

      treinador.escolhaAcessorio(pokeSals);

    } else if (escolha == 2) {

      ModoAleatorio modo = new ModoAleatorio();

      pokeSals = modo.sortearPokeSal();
      modo.sortearAcessorio(pokeSals);

    } else {

      System.out.println("deu ruim");
      sc.close();
      return;
    }

    Batalha batalha = new Batalha(pokeSals[0], pokeSals[1]);
    batalha.iniciarBatalha();

    sc.close();
  }
}

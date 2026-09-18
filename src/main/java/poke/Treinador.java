package poke;

import model.PokeSal;

import java.util.Scanner;

public class Treinador {

    public PokeSal escolhaPokemon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha seu pokemon inicial: ");
        System.out.println("1 - " + PokemonRepository.poker.get(0).getPokemon());
        System.out.println("2 - " + PokemonRepository.poker.get(1).getPokemon());
        System.out.println("3 - " + PokemonRepository.poker.get(2).getPokemon());

        while (true) {
            int escolha = sc.nextInt();

            if (escolha >= 1 && escolha <= 3) {
                PokeSal escolhido = PokemonRepository.poker.get(escolha - 1);
                System.out.println("Você escolheu o " + escolhido.getPokemon() + "!");
                return escolhido;
            }
            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
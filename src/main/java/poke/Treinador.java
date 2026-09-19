package poke;

import model.PokeSal;

import java.util.Scanner;

public class Treinador {

    public PokeSal escolhaSeuPokesal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha seu pokemon inicial: ");
        System.out.println("1 - " + PokeSalRepository.poker.get(0).getPokeSal());
        System.out.println("2 - " + PokeSalRepository.poker.get(1).getPokeSal());
        System.out.println("3 - " + PokeSalRepository.poker.get(2).getPokeSal());
        System.out.println("4 - " + PokeSalRepository.poker.get(3).getPokeSal());
        System.out.println("5 - " + PokeSalRepository.poker.get(4).getPokeSal());
        System.out.println("6 - " + PokeSalRepository.poker.get(5).getPokeSal());




        while (true) {
            int escolha = sc.nextInt();

            if (escolha >= 1 && escolha <= 6) {
                PokeSal escolhido = PokeSalRepository.poker.get(escolha - 1);
                System.out.println("Você escolheu o " + escolhido.getPokeSal() + "!");
                return escolhido;
            }
            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
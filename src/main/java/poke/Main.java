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

            // Modo Normal
            pokeSals = treinador.escolhaSeuPokeSal();

            treinador.escolhaAcessorio(pokeSals);

            // Cria uma mochila para cada jogador
            mochilas = new Mochila[2];

            mochilas[0] = new Mochila();
            mochilas[1] = new Mochila();

        } else if (escolha == 2) {

            // Modo Aleatório
            pokeSals = modoAleatorio.sortearPokeSal();

            modoAleatorio.sortearAcessorio(pokeSals);

            // Cria uma mochila para cada jogador
            mochilas = modoAleatorio.criarMochilas();

        } else {

            System.out.println("Opção inválida.");
            sc.close();
            return;
        }

        System.out.println("\n=== INICIANDO BATALHA ===");

        Batalha batalha = new Batalha(
                pokeSals[0],
                pokeSals[1],
                mochilas[0],
                mochilas[1]
        );

        batalha.iniciarBatalha();

        sc.close();
    }
}


package poke;

import model.Acessorio;
import model.AtributoBonus;
import model.Item;
import model.PokeSal;

import java.util.Scanner;

public class Treinador {

    private Mochila mochila = new Mochila();

    public PokeSal[] escolhaSeuPokeSal() {

        int jogadores = 2;
        Scanner sc = new Scanner(System.in);

        PokeSal[] pokesalEscolhido = new PokeSal[jogadores];

        for (int i = 0; i < jogadores; i++) {

            System.out.println(
                    "Jogador numero " + (i + 1)
                            + " escolha seu pokemon inicial:"
            );

            System.out.println("1 - " + PokeSalRepository.poker.get(0));
            System.out.println("2 - " + PokeSalRepository.poker.get(1));
            System.out.println("3 - " + PokeSalRepository.poker.get(2));
            System.out.println("4 - " + PokeSalRepository.poker.get(3));
            System.out.println("5 - " + PokeSalRepository.poker.get(4));
            System.out.println("6 - " + PokeSalRepository.poker.get(5));

            while (true) {

                int escolha = sc.nextInt();

                if (escolha >= 1 && escolha <= 6) {

                    pokesalEscolhido[i] =
                            PokeSalRepository.poker.get(escolha - 1);

                    System.out.println(
                            "Você escolheu o "
                                    + pokesalEscolhido[i].getPokeSal()
                                    + "!"
                    );

                    break;
                }

                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        return pokesalEscolhido;
    }

    public void escolhaAcessorio(PokeSal[] pokesalEscolhido) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < pokesalEscolhido.length; i++) {

            System.out.println(
                    "Jogador numero " + (i + 1)
                            + ", escolha um acessório para "
                            + pokesalEscolhido[i].getPokeSal() + ":"
            );

            System.out.println(
                    "1 - " + AcessorioRepository.acessorios.get(0)
            );

            System.out.println(
                    "2 - " + AcessorioRepository.acessorios.get(1)
            );

            System.out.println(
                    "3 - " + AcessorioRepository.acessorios.get(2)
            );

            while (true) {

                int escolha = sc.nextInt();

                if (escolha >= 1 && escolha <= 3) {

                    Acessorio acessorioEscolhido =
                            AcessorioRepository.acessorios.get(escolha - 1);

                    pokesalEscolhido[i].getAcessorio(
                            acessorioEscolhido
                    );

                    if (acessorioEscolhido.getAtributo()
                            == AtributoBonus.ATAQUE) {

                        pokesalEscolhido[i].setAtk(
                                pokesalEscolhido[i].getAtk()
                                        + acessorioEscolhido.getValorBonus()
                        );
                    }

                    if (acessorioEscolhido.getAtributo()
                            == AtributoBonus.VELOCIDADE) {

                        pokesalEscolhido[i].setSpd(
                                pokesalEscolhido[i].getSpd()
                                        + acessorioEscolhido.getValorBonus()
                        );
                    }

                    if (acessorioEscolhido.getAtributo()
                            == AtributoBonus.DEFESA) {

                        pokesalEscolhido[i].setDefesa(
                                pokesalEscolhido[i].getDef()
                                        + acessorioEscolhido.getValorBonus()
                        );
                    }

                    System.out.println(
                            pokesalEscolhido[i].getPokeSal()
                                    + " equipou "
                                    + acessorioEscolhido.getnome()
                                    + "!"
                    );

                    System.out.println(
                            "Status do pokesal agora é: "
                                    + pokesalEscolhido[i]
                    );

                    break;
                }

                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public Mochila getMochila() {
        return mochila;
    }

    public Item escolherItem() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEscolha um item:");

        for (int i = 0; i < ItemRepository.itens.size(); i++) {

            System.out.println(
                    (i + 1) + " - " + ItemRepository.itens.get(i)
            );
        }

        while (true) {

            int escolha = sc.nextInt();

            if (escolha >= 1
                    && escolha <= ItemRepository.itens.size()) {

                return ItemRepository.itens.get(escolha - 1);
            }

            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}


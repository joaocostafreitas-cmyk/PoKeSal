package poke;

import model.Acessorio;
import model.AtributoBonus;
import model.PokeSal;

import java.util.Scanner;

public class Treinador {

    public PokeSal []  escolhaSeuPokeSal() {
        
        int jogadores=2;
        Scanner sc = new Scanner(System.in);

        PokeSal[]pokesalEscolhido = new PokeSal[jogadores];

        for(int i=0;i<jogadores;i++) {


            System.out.println("Jogado numero "+(i + 1)+" Escolha seu pokemon inicial: ");
            System.out.println("1 - " + PokeSalRepository.poker.get(0).getPokeSal());
            System.out.println("2 - " + PokeSalRepository.poker.get(1).getPokeSal());
            System.out.println("3 - " + PokeSalRepository.poker.get(2).getPokeSal());
            System.out.println("4 - " + PokeSalRepository.poker.get(3).getPokeSal());
            System.out.println("5 - " + PokeSalRepository.poker.get(4).getPokeSal());
            System.out.println("6 - " + PokeSalRepository.poker.get(5).getPokeSal());


        while (true) {
            int escolha = sc.nextInt();

                if (escolha >= 1 && escolha <= 6) {

                    pokesalEscolhido[i] = PokeSalRepository.poker.get(escolha - 1);
                    System.out.println("Você escolheu o " + pokesalEscolhido[i].getPokeSal() + "!");
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

            System.out.println("Jogador numero " + (i + 1) + ", escolha um acessório para " + pokesalEscolhido[i].getPokeSal() + ":");
            System.out.println("1 - " + AcessorioRepository.acessorios.get(0));
            System.out.println("2 - " + AcessorioRepository.acessorios.get(1));
            System.out.println("3 - " + AcessorioRepository.acessorios.get(2));

            while (true) {

                int escolha = sc.nextInt();

                if (escolha >= 1 && escolha <= 3) {

                    Acessorio acessorioEscolhido = AcessorioRepository.acessorios.get(escolha - 1);
                    pokesalEscolhido[i].getAcessorio(acessorioEscolhido);
                    if (acessorioEscolhido.getAtributo() == AtributoBonus.ATAQUE) {
                        pokesalEscolhido[i].setATK(pokesalEscolhido[i].getATK()+ acessorioEscolhido.getValorBonus());

                    }
                    if (acessorioEscolhido.getAtributo() == AtributoBonus.VELOCIDADE){
                        pokesalEscolhido[i].setSPD(pokesalEscolhido[i].getSPD()+ acessorioEscolhido.getValorBonus() );
                    }
                    if(acessorioEscolhido.getAtributo() == AtributoBonus.DEFESA){
                        pokesalEscolhido[i].setDefesa(pokesalEscolhido[i].getDEF() + acessorioEscolhido.getValorBonus() );
                    }
                    System.out.println(pokesalEscolhido[i].getPokeSal() + " equipou " + acessorioEscolhido.getnome() + "!");
                    break;
                }

                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
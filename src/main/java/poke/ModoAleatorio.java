package poke;

import model.Acessorio;
import model.AtributoBonus;
import model.PokeSal;

public class ModoAleatorio {

    public PokeSal[] sortearPokeSal() {

        int jogadores = 2;

        PokeSal[] pokesalEscolhido =
                new PokeSal[jogadores];

        for (int i = 0; i < jogadores; i++) {

            int sorteio =
                    (int) (Math.random()
                            * PokeSalRepository.poker.size());

            pokesalEscolhido[i] =
                    PokeSalRepository.poker.get(sorteio);

            System.out.println(
                    "Jogador " + (i + 1)
                            + " recebeu o Pokémon sorteado: "
                            + pokesalEscolhido[i].getPokeSal()
                            + "!"
            );
        }

        return pokesalEscolhido;
    }

    public void sortearAcessorio(
            PokeSal[] pokesalEscolhido) {

        for (int i = 0;
                i < pokesalEscolhido.length;
                i++) {

            int sorteio =
                    (int) (Math.random()
                            * AcessorioRepository.acessorios.size());

            Acessorio acessorioEscolhido =
                    AcessorioRepository.acessorios.get(sorteio);

            pokesalEscolhido[i].getAcessorio(
                    acessorioEscolhido
            );

            if (acessorioEscolhido.getAtributo()
                    == AtributoBonus.ATAQUE) {

                pokesalEscolhido[i].setATK(
                        pokesalEscolhido[i].getATK()
                                + acessorioEscolhido.getValorBonus()
                );
            }

            if (acessorioEscolhido.getAtributo()
                    == AtributoBonus.VELOCIDADE) {

                pokesalEscolhido[i].setSPD(
                        pokesalEscolhido[i].getSPD()
                                + acessorioEscolhido.getValorBonus()
                );
            }

            if (acessorioEscolhido.getAtributo()
                    == AtributoBonus.DEFESA) {

                pokesalEscolhido[i].setDefesa(
                        pokesalEscolhido[i].getDEF()
                                + acessorioEscolhido.getValorBonus()
                );
            }

            System.out.println(
                    "Jogador " + (i + 1)
                            + " teve sorteado o acessório: "
                            + acessorioEscolhido.getnome()
                            + " para o "
                            + pokesalEscolhido[i].getPokeSal()
                            + "!"
            );

            System.out.println(
                    "Status do pokesal agora é: "
                            + pokesalEscolhido[i]
            );
        }
    }

}


package poke;

import model.Acessorio;
import model.PokeSal;

/** Realiza sorteios aleatórios de Pokémon Sal e acessórios. */
public class ModoAleatorio {

  /** Sorteia um Pokémon Sal para cada jogador.*/
  public PokeSal[] sortearPokeSal() {

    int jogadores = 2;

    PokeSal[] pokesalEscolhido = new PokeSal[jogadores];

    for (int i = 0; i < jogadores; i++) {

      int sorteio = (int) (Math.random() * PokeSalRepository.poker.size());
      pokesalEscolhido[i] = PokeSalRepository.criarCopia(sorteio);

      System.out.println("Jogador " + (i + 1) + " recebeu o Pokémon sorteado: "
          + pokesalEscolhido[i].getPokeSal() + "!");
    }

    return pokesalEscolhido;
  }

  /** Sorteia um acessório para cada Pokémon Sal e o equipa. */
  public void sortearAcessorio(PokeSal[] pokesalEscolhido) {

    for (int i = 0; i < pokesalEscolhido.length; i++) {

      int sorteio = (int) (Math.random() * AcessorioRepository.acessorios.size());

      Acessorio acessorioEscolhido = AcessorioRepository.acessorios.get(sorteio);

      pokesalEscolhido[i].setAcessorio(acessorioEscolhido);

      System.out.println("Jogador " + (i + 1) + " teve sorteado o acessório: "
                  + acessorioEscolhido.getnome() + " para o "
                            + pokesalEscolhido[i].getPokeSal() + "!");

      System.out.println("Status do pokesal agora é: " + pokesalEscolhido[i]);
    }
  }

}
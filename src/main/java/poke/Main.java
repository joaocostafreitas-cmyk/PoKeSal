package poke;

import model.PokeSal;

public class Main {

    public static void main(String[] args) {
    	
        Treinador treinador = new Treinador();
      //  PokeSal meuPokeSal = treinador.escolhaPokemon();

       // System.out.println("Pronto para batalhar com " + meuPokeSal);
        PokeSal[] pokemons = treinador.escolhaPokemon();

        System.out.println("Pokemon do Jogador 1: " + pokemons[0].getPokeSal());
        System.out.println("Pokemon do Jogador 2: " + pokemons[1].getPokeSal());
    }
}
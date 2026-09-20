package poke;

import model.PokeSal;

public class Main {

    public static void main(String[] args) {
    	
        Treinador treinador = new Treinador();
      //  PokeSal meuPokeSal = treinador.escolhaPokesal();

       // System.out.println("Pronto para batalhar com " + meuPokeSal);
        PokeSal[] PokeSal = treinador.escolhaSeuPokeSal();

        System.out.println("Pokemon do Jogador 1: " + PokeSal[0].getPokeSal());
        System.out.println("Pokemon do Jogador 2: " + PokeSal[1].getPokeSal());

        treinador.escolhaAcessorio(PokeSal);
    }
}
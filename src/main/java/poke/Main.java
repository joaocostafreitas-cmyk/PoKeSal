package poke;

import model.PokeSal;

public class Main {

    public static void main(String[] args) {
        Treinador treinador = new Treinador();
        PokeSal meuPokeSal = treinador.escolhaSeuPokesal();

        System.out.println("Pronto para batalhar com " + meuPokeSal);
    }
}
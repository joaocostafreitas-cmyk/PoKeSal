package poke;

import model.PokeSal;
import model.Terreno;

public class Batalha {

    private PokeSal pokemon1;
    private PokeSal pokemon2;

    // Guardamos o HP inicial de cada um para resetar a cada rodada nova
    private int hpInicial1;
    private int hpInicial2;

    // Contador de vitórias de cada treinador
    private int vitoriasTreinador1 = 0;
    private int vitoriasTreinador2 = 0;

    public Batalha(PokeSal pokemon1, PokeSal pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;

        this.hpInicial1 = pokemon1.getHP();
        this.hpInicial2 = pokemon2.getHP();
    }

    // Método principal: roda as rodadas até alguém vencer 2 vezes
    public void iniciarBatalha() {

        // Terrenos já definidos, um para cada rodada (o jogador não escolhe)
        Terreno[] terrenos = {
                Terreno.ASFALTO_QUENTE,  // rodada 1
                Terreno.POCA_CHUVA,      // rodada 2
                Terreno.CANTEIRO_CENTRAL // rodada 3 (só acontece se der empate)
        };

        int rodada = 0;

        // Enquanto ninguém tiver 2 vitórias, continua jogando rodadas
        while (vitoriasTreinador1 < 2 && vitoriasTreinador2 < 2) {

            Terreno terrenoAtual = terrenos[rodada];
            rodada++;

            System.out.println("\n--- Rodada " + rodada + " | Terreno: " + terrenoAtual + " ---");

            // Reseta o HP dos pokemons no começo da rodada
            pokemon1.setHP(hpInicial1);
            pokemon2.setHP(hpInicial2);

            jogarRodada(terrenoAtual);
        }

        // Depois do while, alguém já tem 2 vitórias. Anuncia o vencedor:
        System.out.println("\n=== FIM DA BATALHA ===");
        if (vitoriasTreinador1 == 2) {
            System.out.println("Treinador 1 venceu com o " + pokemon1.getPokemon() + "!");
        } else {
            System.out.println("Treinador 2 venceu com o " + pokemon2.getPokemon() + "!");
        }
    }

    // Joga uma rodada inteira: os dois trocam ataques até um deles perder todo o HP
    private void jogarRodada(Terreno terreno) {

        // Enquanto os dois estiverem vivos, a batalha continua
        while (pokemon1.getHP() > 0 && pokemon2.getHP() > 0) {

            // Pokemon 1 ataca o Pokemon 2
            atacar(pokemon1, pokemon2, terreno);

            // Se o pokemon2 já morreu, não precisa ele atacar de volta
            if (pokemon2.getHP() <= 0) {
                break;
            }

            // Pokemon 2 ataca o Pokemon 1
            atacar(pokemon2, pokemon1, terreno);
        }

        // Verifica quem ganhou a rodada e soma o ponto
        if (pokemon1.getHP() > 0) {
            vitoriasTreinador1++;
            System.out.println("Vencedor da rodada: " + pokemon1.getPokemon());
        } else {
            vitoriasTreinador2++;
            System.out.println("Vencedor da rodada: " + pokemon2.getPokemon());
        }

        System.out.println("Placar -> Treinador 1: " + vitoriasTreinador1
                + " x " + vitoriasTreinador2 + " : Treinador 2");
    }

    // Um pokemon ataca o outro, aplicando o bônus de dano do terreno
    private void atacar(PokeSal atacante, PokeSal defensor, Terreno terreno) {

        // Dano simples: ataque menos defesa (nunca menor que 1)
        int dano = atacante.getATK() - defensor.getDEF();
        if (dano < 1) {
            dano = 1;
        }

        // Aplica o bônus do terreno (ex: fogo no asfalto quente causa mais dano)
        double bonus = terreno.bonusDano(atacante.getTipoElemental());
        dano = (int) (dano * bonus);

        // Aplica o dano no defensor
        int hpRestante = defensor.getHP() - dano;
        if (hpRestante < 0) {
            hpRestante = 0;
        }
        defensor.setHP(hpRestante);

        System.out.println(atacante.getPokemon() + " atacou " + defensor.getPokemon()
                + " e causou " + dano + " de dano! HP restante: " + defensor.getHP());
    }
}
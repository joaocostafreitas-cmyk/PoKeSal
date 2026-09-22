package poke;

import model.PokeSal;
import model.Terreno;


public class Batalha {

    private PokeSal pokemon1;
    private PokeSal pokemon2;

    private int hpInicial1;
    private int hpInicial2;

    private int vitoriasTreinador1 = 0;
    private int vitoriasTreinador2 = 0;

    public Batalha(PokeSal pokemon1, PokeSal pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;

        this.hpInicial1 = pokemon1.getHP();
        this.hpInicial2 = pokemon2.getHP();
    }

    public void iniciarBatalha() {

        Terreno[] terrenos = {
                Terreno.ASFALTO_QUENTE,
                Terreno.POCA_CHUVA,
                Terreno.CANTEIRO_CENTRAL
        };

        int rodada = 0;

        while (vitoriasTreinador1 < 2 && vitoriasTreinador2 < 2) {

            Terreno terrenoAtual = terrenos[rodada];
            rodada++;

            System.out.println("\n--- Rodada " + rodada + " | Terreno: " + terrenoAtual + " ---");

            pokemon1.setHP(hpInicial1);
            pokemon2.setHP(hpInicial2);

            jogarRodada(terrenoAtual);
        }

        System.out.println("\n=== FIM DA BATALHA ===");
        if (vitoriasTreinador1 == 2) {
            System.out.println("Treinador 1 venceu com o " + pokemon1.getPokeSal() + "!");
        } else {
            System.out.println("Treinador 2 venceu com o " + pokemon2.getPokeSal() + "!");
        }
    }

    private void jogarRodada(Terreno terreno) {

        while (pokemon1.getHP() > 0 && pokemon2.getHP() > 0) {

            PokeSal primeiro;
            PokeSal segundo;

            if (pokemon1.getSPDComStatus() >= pokemon2.getSPDComStatus()) {
                primeiro = pokemon1;
                segundo = pokemon2;
            } else {
                primeiro = pokemon2;
                segundo = pokemon1;
            }

            atacar(primeiro, segundo, terreno);
            aplicarRecuperacaoTerreno(primeiro, terreno, primeiro == pokemon1 ? hpInicial1 : hpInicial2);
            aplicarEfeitosDeStatus(primeiro);

            if (segundo.getHP() <= 0) {
                break;
            }

            atacar(segundo, primeiro, terreno);
            aplicarRecuperacaoTerreno(segundo, terreno, segundo == pokemon1 ? hpInicial1 : hpInicial2);
            aplicarEfeitosDeStatus(segundo);
        }

        if (pokemon1.getHP() > 0) {
            vitoriasTreinador1++;
            System.out.println("Vencedor da rodada: " + pokemon1.getPokeSal());
        } else {
            vitoriasTreinador2++;
            System.out.println("Vencedor da rodada: " + pokemon2.getPokeSal());
        }

        System.out.println("Placar -> Treinador 1: " + vitoriasTreinador1
                + " x " + vitoriasTreinador2 + " : Treinador 2");
    }

    private void atacar(PokeSal atacante, PokeSal defensor, Terreno terreno) {

        double efetividade = atacante.getTipoElemental().efetividadeContra(defensor.getTipoElemental());

        int dano = atacante.getATKComStatus() - defensor.getDEFComAcessorio();
        if (dano < 1) {
            dano = 1;
        }

        double bonus = terreno.bonusDano(atacante.getTipoElemental());
        dano = (int) (dano * efetividade * bonus);

        int hpRestante = defensor.getHP() - dano;
        if (hpRestante < 0) {
            hpRestante = 0;
        }
        defensor.setHP(hpRestante);

        System.out.println(atacante.getPokeSal() + " atacou " + defensor.getPokeSal()
                + " e causou " + dano + " de dano! HP restante: " + defensor.getHP());
    }

    private void aplicarRecuperacaoTerreno(PokeSal pokemon, Terreno terreno, int hpMaximo) {

        double percentualRecuperado = terreno.recuperacaoHp(pokemon.getTipoElemental());

        if (percentualRecuperado > 0) {
            int cura = (int) (hpMaximo * percentualRecuperado);
            int novoHP = Math.min(hpMaximo, pokemon.getHP() + cura);
            pokemon.setHP(novoHP);
            System.out.println(pokemon.getPokeSal() + " recuperou " + cura + " de HP no Canteiro Central!");
        }
    }

    private void aplicarEfeitosDeStatus(PokeSal pokemon) {
        switch (pokemon.getStatus()) {
            case QUEIMADO:
                int danoQueimadura = (int) (pokemon.getHP() * 0.05);
                pokemon.setHP(Math.max(0, pokemon.getHP() - danoQueimadura));
                System.out.println(pokemon.getPokeSal() + " sofreu " + danoQueimadura + " de dano da queimadura!");
                break;
            case ENVENENADO:
                int danoVeneno = (int) (pokemon.getHP() * 0.10);
                pokemon.setHP(Math.max(0, pokemon.getHP() - danoVeneno));
                System.out.println(pokemon.getPokeSal() + " sofreu " + danoVeneno + " de dano do veneno!");
                break;
            case PARALISADO:
                break;
            default:
                break;
        }
    }
}
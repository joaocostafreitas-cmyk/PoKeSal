package poke;

import java.util.Scanner;

import model.Item;
import model.PokeSal;
import model.Terreno;

public class Batalha {

    private PokeSal pokemon1;
    private PokeSal pokemon2;

    private int hpInicial1;
    private int hpInicial2;

    private int vitoriasTreinador1 = 0;
    private int vitoriasTreinador2 = 0;

    private Mochila mochila1;
    private Mochila mochila2;

    private Scanner sc = new Scanner(System.in);

    public Batalha(
            PokeSal pokemon1,
            PokeSal pokemon2,
            Mochila mochila1,
            Mochila mochila2) {

        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;

        this.mochila1 = mochila1;
        this.mochila2 = mochila2;

        this.hpInicial1 = pokemon1.getHp();
        this.hpInicial2 = pokemon2.getHp();
    }

    public void iniciarBatalha() {

        Terreno[] terrenos = {
                Terreno.ASFALTO_QUENTE,
                Terreno.POCA_CHUVA,
                Terreno.CANTEIRO_CENTRAL
        };

        int rodada = 0;

        while (vitoriasTreinador1 < 2
                && vitoriasTreinador2 < 2) {

            Terreno terrenoAtual = terrenos[rodada % terrenos.length];

            rodada++;

            System.out.println(
                    "\n--- Rodada " + rodada
                            + " | Terreno: " + terrenoAtual
                            + " ---"
            );

            pokemon1.setHp(hpInicial1);
            pokemon2.setHp(hpInicial2);

            jogarRodada(terrenoAtual);
        }

        System.out.println("\n=== FIM DA BATALHA ===");

        if (vitoriasTreinador1 == 2) {

            System.out.println(
                    "Treinador 1 venceu com o "
                            + pokemon1.getPokeSal() + "!"
            );

        } else {

            System.out.println(
                    "Treinador 2 venceu com o "
                            + pokemon2.getPokeSal() + "!"
            );
        }
    }

    private void jogarRodada(Terreno terreno) {

        while (pokemon1.getHp() > 0
                && pokemon2.getHp() > 0) {

            PokeSal primeiro;
            PokeSal segundo;

            Mochila mochilaPrimeiro;
            Mochila mochilaSegundo;

            if (pokemon1.getSPDComStatus()
                    >= pokemon2.getSPDComStatus()) {

                primeiro = pokemon1;
                segundo = pokemon2;

                mochilaPrimeiro = mochila1;
                mochilaSegundo = mochila2;

            } else {

                primeiro = pokemon2;
                segundo = pokemon1;

                mochilaPrimeiro = mochila2;
                mochilaSegundo = mochila1;
            }

            boolean usouItem =
                    escolherAcao(primeiro, mochilaPrimeiro);

            if (!usouItem) {

                atacar(primeiro, segundo, terreno);

                aplicarRecuperacaoTerreno(
                        primeiro,
                        terreno,
                        primeiro == pokemon1
                                ? hpInicial1
                                : hpInicial2
                );

                aplicarEfeitosDeStatus(primeiro);
            }

            if (segundo.getHp() <= 0) {
                break;
            }

            boolean segundoUsouItem =
                    escolherAcao(segundo, mochilaSegundo);

            if (!segundoUsouItem) {

                atacar(segundo, primeiro, terreno);

                aplicarRecuperacaoTerreno(
                        segundo,
                        terreno,
                        segundo == pokemon1
                                ? hpInicial1
                                : hpInicial2
                );

                aplicarEfeitosDeStatus(segundo);
            }
        }

        if (pokemon1.getHp() > 0) {

            vitoriasTreinador1++;

            System.out.println(
                    "Vencedor da rodada: "
                            + pokemon1.getPokeSal()
            );

        } else {

            vitoriasTreinador2++;

            System.out.println(
                    "Vencedor da rodada: "
                            + pokemon2.getPokeSal()
            );
        }

        System.out.println(
                "Placar -> Treinador 1: "
                        + vitoriasTreinador1
                        + " x "
                        + vitoriasTreinador2
                        + " : Treinador 2"
        );
    }

    private boolean escolherAcao(
            PokeSal pokemon,
            Mochila mochila) {

        System.out.println(
                "\nTurno de " + pokemon.getPokeSal()
        );

        System.out.println("1 - Atacar");
        System.out.println("2 - Usar item");

        int escolha = sc.nextInt();

        if (escolha == 2) {

            if (!mochila.podeUsarItem()) {

                System.out.println(
                        "Você já usou os 2 itens permitidos nesta batalha."
                );

                return false;
            }

            Item item = escolherItem();

            return mochila.usarItem(
                    item,
                    pokemon,
                    pokemon == pokemon1
                            ? hpInicial1
                            : hpInicial2
            );
        }

        return false;
    }

    private Item escolherItem() {

        System.out.println("\nItens disponíveis:");

        for (int i = 0;
                i < ItemRepository.itens.size();
                i++) {

            System.out.println(
                    (i + 1)
                            + " - "
                            + ItemRepository.itens.get(i)
            );
        }

        while (true) {

            int escolha = sc.nextInt();

            if (escolha >= 1
                    && escolha <= ItemRepository.itens.size()) {

                return ItemRepository.itens.get(escolha - 1);
            }

            System.out.println(
                    "Opção inválida. Escolha novamente."
            );
        }
    }

    private void atacar(
            PokeSal atacante,
            PokeSal defensor,
            Terreno terreno) {

        double efetividade =
                atacante.getTipoElemental()
                        .efetividadeContra(
                                defensor.getTipoElemental()
                        );

        int dano =
                atacante.getATKComStatus()
                        - defensor.getDEFComAcessorio();

        if (dano < 1) {
            dano = 1;
        }

        double bonus =
                terreno.bonusDano(
                        atacante.getTipoElemental()
                );

        dano = (int) (dano * efetividade * bonus);

        int hpRestante =
                defensor.getHp() - dano;

        if (hpRestante < 0) {
            hpRestante = 0;
        }

        defensor.setHp(hpRestante);

        System.out.println(
                atacante.getPokeSal()
                        + " atacou "
                        + defensor.getPokeSal()
                        + " e causou "
                        + dano
                        + " de dano! HP restante: "
                        + defensor.getHp()
        );
    }

    private void aplicarRecuperacaoTerreno(
            PokeSal pokemon,
            Terreno terreno,
            int hpMaximo) {

        double percentualRecuperado =
                terreno.recuperacaoHp(
                        pokemon.getTipoElemental()
                );

        if (percentualRecuperado > 0) {

            int cura =
                    (int) (
                            hpMaximo
                                    * percentualRecuperado
                    );

            int novoHP =
                    Math.min(
                            hpMaximo,
                            pokemon.getHp() + cura
                    );

            pokemon.setHp(novoHP);

            System.out.println(
                    pokemon.getPokeSal()
                            + " recuperou "
                            + cura
                            + " de HP no Canteiro Central!"
            );
        }
    }

    private void aplicarEfeitosDeStatus(
            PokeSal pokemon) {

        switch (pokemon.getStatus()) {

            case QUEIMADO:

                int danoQueimadura =
                        (int) (pokemon.getHp() * 0.05);

                pokemon.setHp(
                        Math.max(
                                0,
                                pokemon.getHp()
                                        - danoQueimadura
                        )
                );

                System.out.println(
                        pokemon.getPokeSal()
                                + " sofreu "
                                + danoQueimadura
                                + " de dano da queimadura!"
                );

                break;

            case ENVENENADO:

                int danoVeneno =
                        (int) (pokemon.getHp() * 0.10);

                pokemon.setHp(
                        Math.max(
                                0,
                                pokemon.getHp()
                                        - danoVeneno
                        )
                );

                System.out.println(
                        pokemon.getPokeSal()
                                + " sofreu "
                                + danoVeneno
                                + " de dano do veneno!"
                );

                break;

            case PARALISADO:
                break;

            default:
                break;
        }
    }
}


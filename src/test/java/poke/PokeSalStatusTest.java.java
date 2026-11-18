package poke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.PokeSal;
import model.RegrasBatalha;
import model.StatusEfeito;
import model.TipoElemental;
import org.junit.jupiter.api.Test;

class PokeSalStatusTest {

  @Test
  void deveAtivarImunidadeAposDuasDerrotasConsecutivas() {
    PokeSal pokemon = criarPokemon();

    pokemon.registrarDerrotaNaRodada();

    assertFalse(
        pokemon.isImuneAQueimadoEEnvenenado()
    );

    pokemon.registrarDerrotaNaRodada();

    assertTrue(
        pokemon.isImuneAQueimadoEEnvenenado()
    );

    assertEquals(
        2,
        pokemon.getDerrotasConsecutivas()
    );
  }

  @Test
  void vitoriaDeveZerarSequenciaDeDerrotas() {
    PokeSal pokemon = criarPokemon();

    pokemon.registrarDerrotaNaRodada();
    pokemon.registrarVitoriaNaRodada();

    assertEquals(
        0,
        pokemon.getDerrotasConsecutivas()
    );

    assertFalse(
        pokemon.isImuneAQueimadoEEnvenenado()
    );
  }

  @Test
  void resetarResistenciaDeveRemoverImunidade() {
    PokeSal pokemon = criarPokemon();

    pokemon.registrarDerrotaNaRodada();
    pokemon.registrarDerrotaNaRodada();

    pokemon.resetarResistenciaDeStatus();

    assertEquals(
        0,
        pokemon.getDerrotasConsecutivas()
    );

    assertFalse(
        pokemon.isImuneAQueimadoEEnvenenado()
    );
  }

  @Test
  void deveBloquearQueimadoQuandoPokemonEstiverImune() {
    PokeSal atacante = new PokeSal(
        "CharSal",
        200,
        100,
        50,
        25,
        TipoElemental.FOGO
    );

    PokeSal defensor = criarPokemon();

    defensor.registrarDerrotaNaRodada();
    defensor.registrarDerrotaNaRodada();

    StatusEfeito status =
        RegrasBatalha.statusPorGolpe(
            atacante,
            defensor
        );

    assertEquals(
        StatusEfeito.NENHUM,
        status
    );
  }

  @Test
  void deveContinuarPermitindoParalisia() {
    PokeSal atacante = new PokeSal(
        "SquirtSal",
        200,
        100,
        50,
        25,
        TipoElemental.AGUA
    );

    PokeSal defensor = new PokeSal(
        "CharSal",
        200,
        100,
        50,
        25,
        TipoElemental.FOGO
    );

    defensor.registrarDerrotaNaRodada();
    defensor.registrarDerrotaNaRodada();

    StatusEfeito status =
        RegrasBatalha.statusPorGolpe(
            atacante,
            defensor
        );

    assertEquals(
        StatusEfeito.PARALISADO,
        status
    );
  }

  private static PokeSal criarPokemon() {
    return new PokeSal(
        "Teste",
        200,
        100,
        50,
        25,
        TipoElemental.PLANTA
    );
  }
}

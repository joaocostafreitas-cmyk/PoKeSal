package poke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Item;
import model.PokeSal;
import model.StatusEfeito;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MochilaTest {

  private Mochila mochila;
  private PokeSal pokemon;

  @BeforeEach
  void configurar() {
    mochila = new Mochila();
    pokemon = new PokeSal("Teste", 200, 100, 50, 25, TipoElemental.FOGO);
  }

  @Test
  void permiteNoMaximoDoisItensPorBatalha() {
    Item potion = new Item("Potion", 20);

    assertTrue(mochila.usarItem(potion, pokemon, 200));
    assertTrue(mochila.usarItem(potion, pokemon, 200));

    assertFalse(mochila.podeUsarItem());
    assertFalse(mochila.usarItem(potion, pokemon, 200));
    assertEquals(2, mochila.getItensUsados());
  }

  @Test
  void itemNaoCuraAlemDoHpMaximo() {
    pokemon.setHp(190);

    mochila.usarItem(new Item("Potion", 20), pokemon, 200);

    assertEquals(200, pokemon.getHp());
  }

  @Test
  void antidotoRemoveOStatus() {
    pokemon.setStatus(StatusEfeito.QUEIMADO);

    mochila.usarItem(new Item("Antídoto", 0, true), pokemon, 200);

    assertEquals(StatusEfeito.NENHUM, pokemon.getStatus());
  }

  @Test
  void itemSemRemocaoDeStatusMantemOStatus() {
    pokemon.setStatus(StatusEfeito.ENVENENADO);

    mochila.usarItem(new Item("Potion", 20), pokemon, 200);

    assertEquals(StatusEfeito.ENVENENADO, pokemon.getStatus());
  }
}
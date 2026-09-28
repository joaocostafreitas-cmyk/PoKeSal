package poke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals; 

import java.io.ByteArrayInputStream;
import model.PokeSal;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TreinadorTest {

  Treinador treinador;

  @BeforeEach
    void configurar() {
    treinador = new Treinador();

  }

  @Test
public void testEscolhaSeuPokeSal() {

    System.out.println("1 Fase :");
    String entrada = "1\n1\n";
    System.setIn(new ByteArrayInputStream(entrada.getBytes()));

    System.out.println("2 entrada");

    PokeSal[] pokesalEscolhido = treinador.escolhaSeuPokeSal();

    System.out.println("3 escolha ");

    assertEquals("BulbaSal", pokesalEscolhido[0].getPokeSal());

    System.out.println("4 primeiro passou");

    assertEquals(200, pokesalEscolhido[0].getHp());
    assertEquals(100, pokesalEscolhido[0].getAtk());
    assertEquals(50, pokesalEscolhido[0].getDef());
    assertEquals(25, pokesalEscolhido[0].getSpd());
    assertEquals(TipoElemental.PLANTA, pokesalEscolhido[0].getTipoElemental());

    System.out.println("2 Fase :");
    assertNotEquals(pokesalEscolhido[1], pokesalEscolhido[0]);

    System.out.println("6  Fim'-' ");
  }
}
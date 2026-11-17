package poke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import model.PokeSal;
import model.TipoElemental;
import org.junit.jupiter.api.Test;

public class TreinadorTest {

  @Test
  void escolhaSeuPokeSalCriaUmaCopiaParaCadaJogador() {
    Scanner entrada = new Scanner(new ByteArrayInputStream("1\n1\n".getBytes()));
    Treinador treinador = new Treinador(entrada);

    PokeSal[] escolhidos = treinador.escolhaSeuPokeSal();

    assertEquals("BulbaSal", escolhidos[0].getPokeSal());
    assertEquals(200, escolhidos[0].getHp());
    assertEquals(100, escolhidos[0].getAtk());
    assertEquals(50, escolhidos[0].getDef());
    assertEquals(25, escolhidos[0].getSpd());
    assertEquals(TipoElemental.PLANTA, escolhidos[0].getTipoElemental());

    assertNotSame(escolhidos[1], escolhidos[0]);
  }
}
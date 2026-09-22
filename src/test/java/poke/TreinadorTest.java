package poke;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.PokeSal;
import model.TipoElemental;

public class TreinadorTest {

    Treinador treinador;

    @BeforeEach
    void configurar() {
        treinador = new Treinador();
    }

   @Test
public void testEscolhaSeuPokeSal() {

    String entrada = "1\n2\n";
    System.setIn(new ByteArrayInputStream(entrada.getBytes()));

    System.out.println("antes");
    PokeSal[] pokesalEscolhido = treinador.escolhaSeuPokeSal();
System.out.println("depois");

    assertEquals("BulbaSal", pokesalEscolhido[0].getPokeSal());
    System.out.println("1 passou");
    assertEquals(200, pokesalEscolhido[0].getHP());
    System.out.println("2 passou");
    assertEquals(100, pokesalEscolhido[0].getATK());
    System.out.println("3 passou");
    assertEquals(50, pokesalEscolhido[0].getDEF());
    System.out.println("4 passou");
    assertEquals(25, pokesalEscolhido[0].getSPD());
    System.out.println("5 passou");
    assertEquals(TipoElemental.PLANTA, pokesalEscolhido[0].getTipoElemental());
    System.out.println("6 passou");

}
    }

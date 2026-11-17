package poke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import model.Golpe;
import model.Item;
import model.PokeSal;
import model.ResultadoGolpe;
import model.StatusEfeito;
import model.Terreno;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistroBatalhaConsoleTest {

  private ByteArrayOutputStream buffer;
  private RegistroBatalhaConsole registro;
  private PokeSal atacante;
  private PokeSal defensor;

  @BeforeEach
  void configurar() {
    buffer = new ByteArrayOutputStream();
    registro = new RegistroBatalhaConsole(new PrintStream(buffer, true, StandardCharsets.UTF_8));
    atacante = new PokeSal("CharSal", 200, 100, 50, 25, TipoElemental.FOGO,
        new Golpe("Brasa", 90));
    defensor = new PokeSal("BulbaSal", 200, 100, 50, 25, TipoElemental.PLANTA);
  }

  private String saida() {
    return buffer.toString(StandardCharsets.UTF_8);
  }

  private String linha(String texto) {
    return texto + System.lineSeparator();
  }

  @Test
  void golpeQueErrouInformaOErro() {
    registro.registrarAtaque(atacante, defensor, new ResultadoGolpe(false, false, 0));

    assertEquals(linha("CharSal usou Brasa em BulbaSal, mas errou!"), saida());
  }

  @Test
  void golpeCriticoInformaOCritico() {
    registro.registrarAtaque(atacante, defensor, new ResultadoGolpe(true, true, 75));

    assertEquals(linha("CharSal usou Brasa em BulbaSal!") + linha("Golpe crítico!"), saida());
  }

  @Test
  void danoMostraOHpRestante() {
    defensor.setHp(150);

    registro.registrarDano(defensor, 50);

    assertEquals(linha("BulbaSal sofreu 50 de dano! HP restante: 150"), saida());
  }

  @Test
  void efeitosDeStatusEDeTerrenoSaoDescritos() {
    registro.registrarStatusAplicado(defensor, StatusEfeito.QUEIMADO);
    registro.registrarEfeitoStatus(defensor, StatusEfeito.QUEIMADO, 2);
    registro.registrarEfeitoTerreno(defensor, Terreno.CANTEIRO_CENTRAL, 10);

    assertEquals(linha("BulbaSal ficou QUEIMADO!")
        + linha("BulbaSal sofreu 2 de dano da queimadura!")
        + linha("BulbaSal recuperou 10 de HP no terreno CANTEIRO_CENTRAL!"), saida());
  }

  @Test
  void usoDeItemMostraOContador() {
    registro.registrarUsoItem(atacante, new Item("Potion", 20), 1, 2);

    assertEquals(linha("CharSal usou Potion!") + linha("Itens usados: 1/2"), saida());
  }
}
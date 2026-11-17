package poke;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;
import model.GeradorAleatorio;
import model.Item;
import model.PokeSal;
import model.RegistroBatalha;
import model.ResultadoGolpe;
import model.StatusEfeito;
import model.Terreno;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BatalhaRegistroTest {

  @Mock
  private GeradorAleatorio gerador;

  @Mock
  private RegistroBatalha registro;

  private PokeSal p1;
  private PokeSal p2;

  /** Roda uma batalha completa com entradas combinadas; os testes só conferem os eventos. */
  @BeforeEach
  void executarBatalha() {
    // sempre acerta (0 < 100) e nunca é crítico (1 != 0)
    when(gerador.proximoInt(100)).thenReturn(0);
    when(gerador.proximoInt(16)).thenReturn(1);

    p1 = new PokeSal("P1", 200, 100, 50, 25, TipoElemental.PLANTA);
    p2 = new PokeSal("P2", 200, 60, 50, 25, TipoElemental.PLANTA);

    // Rodada 1: P1 usa item (2, depois item 1) e depois só ataca; rodada 2: só ataques.
    String entrada = "2\n" + "1\n".repeat(16);

    Mochila[] mochilas = Mochila.criarMochilas(2);

    new Batalha(p1, p2, mochilas[0], mochilas[1], new Scanner(entrada), gerador, registro)
        .iniciarBatalha();
  }

  @Test
  void primeiroTurnoRegistraItemDepoisAtaqueEDanoNaOrdem() {
    InOrder ordem = inOrder(registro);

    ordem.verify(registro).registrarUsoItem(eq(p1), any(Item.class), eq(1),
        eq(Mochila.LIMITE_ITENS_POR_BATALHA));
    ordem.verify(registro).registrarAtaque(eq(p2), eq(p1), any(ResultadoGolpe.class));
    ordem.verify(registro).registrarDano(p1, 10);
    ordem.verify(registro).registrarAtaque(eq(p1), eq(p2), any(ResultadoGolpe.class));
    ordem.verify(registro).registrarDano(p2, 50);
  }

  @Test
  void todoGolpeQueAcertaGeraUmAtaqueEUmDano() {
    verify(registro, times(15)).registrarAtaque(any(PokeSal.class), any(PokeSal.class),
        any(ResultadoGolpe.class));
    verify(registro, times(15)).registrarDano(any(PokeSal.class), anyInt());
  }

  @Test
  void usoDeItemEhRegistradoUmaUnicaVez() {
    verify(registro, times(1)).registrarUsoItem(any(PokeSal.class), any(Item.class), anyInt(),
        anyInt());
  }

  @Test
  void semStatusNemCuraDeTerrenoNaoHaEventosDesseTipo() {
    verify(registro, never()).registrarStatusAplicado(any(PokeSal.class),
        any(StatusEfeito.class));
    verify(registro, never()).registrarEfeitoStatus(any(PokeSal.class),
        any(StatusEfeito.class), anyInt());
    verify(registro, never()).registrarEfeitoTerreno(any(PokeSal.class), any(Terreno.class),
        anyInt());
  }
}
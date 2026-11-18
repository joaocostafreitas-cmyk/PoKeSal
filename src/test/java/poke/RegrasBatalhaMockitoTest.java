package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegrasBatalhaMockitoTest {

  @Mock
  private GeradorAleatorio gerador;

  private PokeSal atacante() {
    return new PokeSal("Atacante", 200, 100, 50, 25, TipoElemental.FOGO, new Golpe("Brasa", 90));
  }

  private PokeSal alvo() {
    return new PokeSal("Alvo", 200, 100, 50, 25, TipoElemental.FOGO);
  }

  @Test
  void testAcertoCriticoComGeradorMockado() {
   
    when(gerador.proximoInt(anyInt())).thenReturn(0);

    ResultadoGolpe resultado =
        RegrasBatalha.resolverGolpe(atacante(), alvo(), Terreno.NENHUM, gerador);

    assertTrue(resultado.isAcertou());
    assertTrue(resultado.isCritico());
    assertEquals(75, resultado.getDano());  // dano base 50 x 1,5

    
    verify(gerador).proximoInt(100);
    verify(gerador).proximoInt(16);
  }

  @Test
  void testSemCriticoQuandoOSorteioEstaForaDaFaixa() {
    // retornos em sequência: 1ª chamada (acerto) devolve 0 e 2ª (crítico) devolve 1, que não é crítico
    when(gerador.proximoInt(anyInt())).thenReturn(0, 1);

    ResultadoGolpe resultado =
        RegrasBatalha.resolverGolpe(atacante(), alvo(), Terreno.NENHUM, gerador);

    assertTrue(resultado.isAcertou());
    assertFalse(resultado.isCritico());
    assertEquals(50, resultado.getDano());
  }
}
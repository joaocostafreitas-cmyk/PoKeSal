package poke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import model.Item;
import model.PokeSal;
import model.RegistroBatalha;
import model.StatusEfeito;
import model.Terreno;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicoBatalhaTurnoTest {

  private ServicoTerreno servicoTerreno;
  private RegistroBatalha registroBatalha;
  private ServicoBatalhaTurno servicoBatalhaTurno;

  private PokeSal atacante;
  private PokeSal defensor;
  private Item item;
  private Terreno terreno;

  @BeforeEach
  void configurar() {
    servicoTerreno = Mockito.mock(ServicoTerreno.class);
    registroBatalha = Mockito.mock(RegistroBatalha.class);

    servicoBatalhaTurno =
        new ServicoBatalhaTurno(
            servicoTerreno,
            registroBatalha
        );

    atacante = new PokeSal(
        "CharSal",
        200,
        100,
        50,
        25,
        TipoElemental.FOGO
    );

    defensor = new PokeSal(
        "BulbaSal",
        200,
        60,
        50,
        25,
        TipoElemental.PLANTA
    );

    item = new Item("Potion", 20);
    terreno = Terreno.ASFALTO_QUENTE;
  }

  @Test
  void deveRegistrarAtaqueDanoCuraEUsoDeItem() {
    when(
        servicoTerreno.bonusDano(
            terreno,
            TipoElemental.FOGO
        )
    ).thenReturn(1.15);

    when(
        servicoTerreno.calcularCuraTerreno(
            atacante,
            terreno,
            100
        )
    ).thenReturn(10);

    servicoBatalhaTurno.executarTurno(
        atacante,
        defensor,
        terreno,
        true,
        item,
        1,
        2
    );

    verify(registroBatalha)
        .registrarAtaque(
            atacante,
            defensor,
            null
        );

    verify(registroBatalha)
        .registrarDano(
            eq(defensor),
            anyInt()
        );

    verify(registroBatalha)
        .registrarEfeitoTerreno(
            atacante,
            terreno,
            10
        );

    verify(registroBatalha)
        .registrarUsoItem(
            atacante,
            item,
            1,
            2
        );
  }

  @Test
  void deveRegistrarEfeitoDeStatusQuandoGolpeForSuperEfetivo() {
    when(
        servicoTerreno.bonusDano(
            terreno,
            TipoElemental.FOGO
        )
    ).thenReturn(1.15);

    when(
        servicoTerreno.calcularCuraTerreno(
            atacante,
            terreno,
            100
        )
    ).thenReturn(0);

    servicoBatalhaTurno.executarTurno(
        atacante,
        defensor,
        terreno,
        false,
        null,
        0,
        2
    );

    verify(registroBatalha)
        .registrarEfeitoStatus(
            defensor,
            StatusEfeito.QUEIMADO,
            1
        );
  }

  @Test
  void naoDeveRegistrarStatusQuandoGolpeNaoForSuperEfetivo() {
    defensor = new PokeSal(
        "SquirtSal",
        200,
        60,
        50,
        25,
        TipoElemental.AGUA
    );

    when(
        servicoTerreno.bonusDano(
            terreno,
            TipoElemental.FOGO
        )
    ).thenReturn(1.15);

    when(
        servicoTerreno.calcularCuraTerreno(
            atacante,
            terreno,
            100
        )
    ).thenReturn(0);

    servicoBatalhaTurno.executarTurno(
        atacante,
        defensor,
        terreno,
        false,
        null,
        0,
        2
    );

    verify(registroBatalha, never())
        .registrarEfeitoStatus(
            any(),
            any(),
            anyInt()
        );
  }

  @Test
  void naoDeveRegistrarCuraQuandoTerrenoNaoCurar() {
    when(
        servicoTerreno.bonusDano(
            terreno,
            TipoElemental.FOGO
        )
    ).thenReturn(1.15);

    when(
        servicoTerreno.calcularCuraTerreno(
            atacante,
            terreno,
            100
        )
    ).thenReturn(0);

    servicoBatalhaTurno.executarTurno(
        atacante,
        defensor,
        terreno,
        false,
        null,
        0,
        2
    );

    verify(registroBatalha, never())
        .registrarEfeitoTerreno(
            any(),
            any(),
            anyInt()
        );
  }

  @Test
  void naoDeveRegistrarUsoDeItemQuandoNaoUsado() {
    when(
        servicoTerreno.bonusDano(
            terreno,
            TipoElemental.FOGO
        )
    ).thenReturn(1.15);

    when(
        servicoTerreno.calcularCuraTerreno(
            atacante,
            terreno,
            100
        )
    ).thenReturn(0);

    servicoBatalhaTurno.executarTurno(
        atacante,
        defensor,
        terreno,
        false,
        null,
        0,
        2
    );

    verify(registroBatalha, never())
        .registrarUsoItem(
            any(),
            any(),
            anyInt(),
            anyInt()
        );
  }

  @Test
  void deveAplicarBonusDeTerrenoAoDano() {
    when(
        servicoTerreno.bonusDano(
            terreno,
            TipoElemental.FOGO
        )
    ).thenReturn(1.5);

    when(
        servicoTerreno.calcularCuraTerreno(
            atacante,
            terreno,
            100
        )
    ).thenReturn(0);

    servicoBatalhaTurno.executarTurno(
        atacante,
        defensor,
        terreno,
        false,
        null,
        0,
        2
    );

    verify(registroBatalha)
        .registrarDano(
            defensor,
            171
        );
  }

  @Test
  void deveRejeitarDependenciasNulas() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ServicoBatalhaTurno(
            null,
            registroBatalha
        )
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> new ServicoBatalhaTurno(
            servicoTerreno,
            null
        )
    );
  }
}

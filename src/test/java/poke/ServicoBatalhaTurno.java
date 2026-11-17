package poke;

import model.Item;
import model.PokeSal;
import model.RegistroBatalha;
import model.ResultadoGolpe;
import model.StatusEfeito;
import model.Terreno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ServicoBatalhaTurnoTest {

    private ServicoTerreno servicoTerrenoMock;
    private RegistroBatalha registroBatalhaMock;
    private ServicoBatalhaTurno servicoBatalhaTurno;

    private PokeSal atacanteMock;
    private PokeSal defensorMock;
    private Terreno terrenoMock;
    private Item itemMock;
    private ResultadoGolpe resultadoGolpeMock;

    @BeforeEach
    void setUp() {
        servicoTerrenoMock = Mockito.mock(ServicoTerreno.class);
        registroBatalhaMock = Mockito.mock(RegistroBatalha.class);
        servicoBatalhaTurno = new ServicoBatalhaTurno(servicoTerrenoMock, registroBatalhaMock);

        atacanteMock = Mockito.mock(PokeSal.class);
        defensorMock = Mockito.mock(PokeSal.class);
        terrenoMock = Mockito.mock(Terreno.class);
        itemMock = Mockito.mock(Item.class);
        resultadoGolpeMock = Mockito.mock(ResultadoGolpe.class);
    }

    @Test
    void deveExecutarTurnoComSucessoERegistrarEventos() {
        when(servicoTerrenoMock.bonusDano(any(), any())).thenReturn(1.0);
        when(servicoTerrenoMock.calcularCuraTerreno(any(), any(), anyInt())).thenReturn(10);

        servicoBatalhaTurno.executarTurno(atacanteMock, defensorMock, terrenoMock, true, itemMock, 1, 3, resultadoGolpeMock);

        verify(registroBatalhaMock).registrarAtaque(atacanteMock, defensorMock, resultadoGolpeMock);
        verify(registroBatalhaMock).registrarDano(Mockito.eq(defensorMock), anyInt());
        verify(registroBatalhaMock).registrarEfeitoTerreno(Mockito.eq(atacanteMock), Mockito.eq(terrenoMock), Mockito.eq(10));
        verify(registroBatalhaMock).registrarUsoItem(atacanteMock, itemMock, 1, 3);
    }

    @Test
    void deveRegistrarEfeitoStatusQuandoAplicado() {
        when(servicoTerrenoMock.bonusDano(any(), any())).thenReturn(1.0);
        when(servicoTerrenoMock.calcularCuraTerreno(any(), any(), anyInt())).thenReturn(0);

        try (var mockedStatic = Mockito.mockStatic(model.RegrasBatalha.class)) {
            mockedStatic.when(() -> model.RegrasBatalha.calcularDano(any(), any(), any())).thenReturn(50);
            mockedStatic.when(() -> model.RegrasBatalha.statusPorGolpe(any(), any())).thenReturn(StatusEfeito.PARALISADO);

            servicoBatalhaTurno.executarTurno(atacanteMock, defensorMock, terrenoMock, false, null, 0, 0, resultadoGolpeMock);

            verify(registroBatalhaMock).registrarEfeitoStatus(Mockito.eq(defensorMock), Mockito.eq(StatusEfeito.PARALISADO), anyInt());
        }
    }

    @Test
    void naoDeveRegistrarEfeitoStatusQuandoNenhum() {
        when(servicoTerrenoMock.bonusDano(any(), any())).thenReturn(1.0);
        when(servicoTerrenoMock.calcularCuraTerreno(any(), any(), anyInt())).thenReturn(0);

        try (var mockedStatic = Mockito.mockStatic(model.RegrasBatalha.class)) {
            mockedStatic.when(() -> model.RegrasBatalha.calcularDano(any(), any(), any())).thenReturn(50);
            mockedStatic.when(() -> model.RegrasBatalha.statusPorGolpe(any(), any())).thenReturn(StatusEfeito.NENHUM);

            servicoBatalhaTurno.executarTurno(atacanteMock, defensorMock, terrenoMock, false, null, 0, 0, resultadoGolpeMock);

            verify(registroBatalhaMock, Mockito.never()).registrarEfeitoStatus(any(), any(), anyInt());
        }
    }

    @Test
    void naoDeveRegistrarEfeitoTerrenoQuandoCuraForZero() {
        when(servicoTerrenoMock.bonusDano(any(), any())).thenReturn(1.0);
        when(servicoTerrenoMock.calcularCuraTerreno(any(), any(), anyInt())).thenReturn(0);

        try (var mockedStatic = Mockito.mockStatic(model.RegrasBatalha.class)) {
            mockedStatic.when(() -> model.RegrasBatalha.calcularDano(any(), any(), any())).thenReturn(50);
            mockedStatic.when(() -> model.RegrasBatalha.statusPorGolpe(any(), any())).thenReturn(StatusEfeito.NENHUM);

            servicoBatalhaTurno.executarTurno(atacanteMock, defensorMock, terrenoMock, false, null, 0, 0, resultadoGolpeMock);

            verify(registroBatalhaMock, Mockito.never()).registrarEfeitoTerreno(any(), any(), anyInt());
        }
    }

    @Test
    void naoDeveRegistrarUsoDeItemQuandoFlagForFalsaOuItemNulo() {
        when(servicoTerrenoMock.bonusDano(any(), any())).thenReturn(1.0);
        when(servicoTerrenoMock.calcularCuraTerreno(any(), any(), anyInt())).thenReturn(0);

        try (var mockedStatic = Mockito.mockStatic(model.RegrasBatalha.class)) {
            mockedStatic.when(() -> model.RegrasBatalha.calcularDano(any(), any(), any())).thenReturn(50);
            mockedStatic.when(() -> model.RegrasBatalha.statusPorGolpe(any(), any())).thenReturn(StatusEfeito.NENHUM);

            servicoBatalhaTurno.executarTurno(atacanteMock, defensorMock, terrenoMock, false, null, 0, 0, resultadoGolpeMock);

            verify(registroBatalhaMock, Mockito.never()).registrarUsoItem(any(), any(), anyInt(), anyInt());
        }
    }

    @Test
    void deveAplicarBonusDeTerrenoCorretamenteNoDanoFinal() {
        when(servicoTerrenoMock.bonusDano(any(), any())).thenReturn(1.5);
        when(servicoTerrenoMock.calcularCuraTerreno(any(), any(), anyInt())).thenReturn(0);

        try (var mockedStatic = Mockito.mockStatic(model.RegrasBatalha.class)) {
            mockedStatic.when(() -> model.RegrasBatalha.calcularDano(any(), any(), any())).thenReturn(100);
            mockedStatic.when(() -> model.RegrasBatalha.statusPorGolpe(any(), any())).thenReturn(StatusEfeito.NENHUM);

            servicoBatalhaTurno.executarTurno(atacanteMock, defensorMock, terrenoMock, false, null, 0, 0, resultadoGolpeMock);

            verify(registroBatalhaMock).registrarDano(defensorMock, 150);
        }
    }

    @Test
    void deveLancarExcecaoQuandoDependenciasForemNulasNoContrutor() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ServicoBatalhaTurno(null, null);
        });
    }
}

class ServicoBatalhaTurno {

    private final ServicoTerreno servicoTerreno;
    private final RegistroBatalha registroBatalha;

    ServicoBatalhaTurno(ServicoTerreno servicoTerreno, RegistroBatalha registroBatalha) {
        if (servicoTerreno == null || registroBatalha == null) {
            throw new IllegalArgumentException("As dependências não podem ser nulas");
        }
        this.servicoTerreno = servicoTerreno;
        this.registroBatalha = registroBatalha;
    }

    void executarTurno(PokeSal atacante, PokeSal defensor, Terreno terreno,
                       boolean usarItem, Item item, int quantidadeItem, int turno,
                       ResultadoGolpe resultadoGolpe) {
        registroBatalha.registrarAtaque(atacante, defensor, resultadoGolpe);

        int danoBase = model.RegrasBatalha.calcularDano(atacante, defensor, terreno);
        int danoFinal = (int) (danoBase * servicoTerreno.bonusDano(terreno, atacante.getTipoElemental()));
        registroBatalha.registrarDano(defensor, danoFinal);

        StatusEfeito status = model.RegrasBatalha.statusPorGolpe(atacante, defensor);
        if (status != null && status != StatusEfeito.NENHUM) {
            registroBatalha.registrarEfeitoStatus(defensor, status, danoFinal);
        }

        int cura = servicoTerreno.calcularCuraTerreno(atacante, terreno, danoFinal);
        if (cura > 0) {
            registroBatalha.registrarEfeitoTerreno(atacante, terreno, cura);
        }

        if (usarItem && item != null) {
            registroBatalha.registrarUsoItem(atacante, item, quantidadeItem, turno);
        }
    }
}
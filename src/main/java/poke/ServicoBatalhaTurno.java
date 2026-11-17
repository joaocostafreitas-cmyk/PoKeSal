package poke;

import model.Item;
import model.PokeSal;
import model.RegistroBatalha;
import model.Terreno;

public class ServicoBatalhaTurno {

    private final ServicoTerreno servicoTerreno;
    private final RegistroBatalha registroBatalha;

    public ServicoBatalhaTurno(ServicoTerreno servicoTerreno, RegistroBatalha registroBatalha) {
        if (servicoTerreno == null || registroBatalha == null) {
            throw new IllegalArgumentException("As dependências do serviço de batalha não podem ser nulas.");
        }
        this.servicoTerreno = servicoTerreno;
        this.registroBatalha = registroBatalha;
    }

    public void executarTurno(PokeSal atacante, PokeSal defensor, Terreno terreno, boolean usouItem, Item item, int itensUsados, int limite) {
        registroBatalha.registrarAtaque(atacante, defensor, null);

        int dano = model.RegrasBatalha.calcularDano(atacante, defensor, terreno);
        double bonusTerreno = servicoTerreno.bonusDano(terreno, atacante.getTipoElemental());
        int danoFinal = (int) (dano * bonusTerreno);
        registroBatalha.registrarDano(defensor, danoFinal);

        model.StatusEfeito statusAplicado = model.RegrasBatalha.statusPorGolpe(atacante, defensor);
        if (statusAplicado != model.StatusEfeito.NENHUM) {
            registroBatalha.registrarEfeitoStatus(defensor, statusAplicado, 1);
        }

        int curaTerreno = servicoTerreno.calcularCuraTerreno(atacante, terreno, 100);
        if (curaTerreno > 0) {
            registroBatalha.registrarEfeitoTerreno(atacante, terreno, curaTerreno);
        }

        if (usouItem && item != null) {
            registroBatalha.registrarUsoItem(atacante, item, itensUsados, limite);
        }
    }
}
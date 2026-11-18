package poke;

import java.time.Clock;
import java.time.LocalTime;
import model.PokeSal;
import model.Terreno;

public class ServicoTerreno {

    private final Clock clock;

    public ServicoTerreno(Clock clock) {
        if (clock == null) {
            throw new IllegalArgumentException("O relógio (Clock) não pode ser nulo.");
        }
        this.clock = clock;
    }

    public boolean isDia() {
        LocalTime agora = LocalTime.now(clock);
        int hora = agora.getHour();
        return hora >= 6 && hora < 18;
    }

    public double bonusDano(Terreno terreno, model.TipoElemental tipo) {
        if (terreno == null) {
            return 1.0;
        }
        
        if (terreno == Terreno.ASFALTO_QUENTE && !isDia()) {
            return 1.0;
        }
        
        return terreno.bonusDano(tipo);
    }

    public int calcularCuraTerreno(PokeSal pokemon, Terreno terreno, int hpMaximo) {
        if (pokemon.getHp() <= 0 || terreno == null) {
            return 0;
        }
        
        double recuperacao = terreno.recuperacaoHp(pokemon.getTipoElemental());
        
        if (terreno == Terreno.ASFALTO_QUENTE && !isDia()) {
            recuperacao = 0.0;
        }
        
        return (int) (hpMaximo * recuperacao);
    }
}

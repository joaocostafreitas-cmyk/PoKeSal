package poke;

import model.PokeSal;
import java.util.List;

public interface RepositorioResultados {
    void salvar(Treinador vencedor, Treinador perdedor, int turnos, List<PokeSal> pokemonsUtilizados);
}
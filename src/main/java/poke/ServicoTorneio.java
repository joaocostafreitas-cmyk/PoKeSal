package poke;

import model.PokeSal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoTorneio {

    private static final Logger LOGGER = Logger.getLogger(ServicoTorneio.class.getName());
    private final RepositorioResultados repositorioResultados;

    public ServicoTorneio(RepositorioResultados repositorioResultados) {
        if (repositorioResultados == null) {
            throw new IllegalArgumentException("O repositório de resultados não pode ser nulo.");
        }
        this.repositorioResultados = repositorioResultados;
    }

    public void registrarResultadoBatalha(Treinador vencedor, Treinador perdedor, int turnos, List<PokeSal> pokemonsUtilizados) {
        try {
            repositorioResultados.salvar(vencedor, perdedor, turnos, pokemonsUtilizados);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar o resultado da batalha no repositório. A batalha continuará.", e);
        }
    }
}
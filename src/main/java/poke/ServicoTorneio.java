package poke;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PokeSal;

public class ServicoTorneio {

  private static final Logger LOGGER =
      Logger.getLogger(ServicoTorneio.class.getName());

  private final RepositorioResultados repositorioResultados;

  public ServicoTorneio(RepositorioResultados repositorioResultados) {
    if (repositorioResultados == null) {
      throw new IllegalArgumentException(
          "O repositório de resultados não pode ser nulo."
      );
    }
    this.repositorioResultados = repositorioResultados;
  }

 
  public void registrarResultadoBatalha(
      Treinador vencedor,
      Treinador perdedor,
      int turnos,
      List<PokeSal> pokemonsUtilizados
  ) {
    try {
      repositorioResultados.salvar(
          vencedor,
          perdedor,
          turnos,
          pokemonsUtilizados
      );
    } catch (RuntimeException e) {
      LOGGER.log(
          Level.WARNING,
          "Não foi possível salvar o resultado; a batalha continuará.",
          e
      );
    }
  }

 
  public void registrarResultadoSeBatalhaTerminou(
      boolean terminou,
      Treinador vencedor,
      Treinador perdedor,
      int turnos,
      List<PokeSal> pokemonsUtilizados
  ) {
    if (terminou) {
      registrarResultadoBatalha(
          vencedor,
          perdedor,
          turnos,
          pokemonsUtilizados
      );
    }
  }

  
  public Treinador finalizarBatalha(
      boolean terminou,
      Treinador vencedor,
      Treinador perdedor,
      int turnos,
      List<PokeSal> pokemonsUtilizados
  ) {
    registrarResultadoSeBatalhaTerminou(
        terminou,
        vencedor,
        perdedor,
        turnos,
        pokemonsUtilizados
    );
    return vencedor;
  }
}

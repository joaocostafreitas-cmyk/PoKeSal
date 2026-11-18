package poke;

import java.util.ArrayList;
import java.util.List;


public class ChaveamentoTorneio {

  @FunctionalInterface
  public interface ExecutorPartida {
    Treinador executar(Treinador primeiro, Treinador segundo);
  }

  private final ExecutorPartida executorPartida;
  private final List<List<Treinador>> confrontos = new ArrayList<>();

  public ChaveamentoTorneio(ExecutorPartida executorPartida) {
    if (executorPartida == null) {
      throw new IllegalArgumentException("O executor de partidas é obrigatório.");
    }
    this.executorPartida = executorPartida;
  }

  public Treinador executar(List<Treinador> participantes) {
    validarParticipantes(participantes);
    confrontos.clear();

    List<Treinador> vencedores = executarFase(participantes);
    while (vencedores.size() > 1) {
      vencedores = executarFase(vencedores);
    }
    return vencedores.get(0);
  }

  public List<List<Treinador>> getConfrontos() {
    return confrontos.stream().map(List::copyOf).toList();
  }

  private List<Treinador> executarFase(List<Treinador> participantes) {
    List<Treinador> vencedores = new ArrayList<>();
    for (int i = 0; i < participantes.size(); i += 2) {
      Treinador primeiro = participantes.get(i);
      Treinador segundo = participantes.get(i + 1);
      confrontos.add(List.of(primeiro, segundo));
      Treinador vencedor = executorPartida.executar(primeiro, segundo);
      if (vencedor == null || (vencedor != primeiro && vencedor != segundo)) {
        throw new IllegalStateException("A partida deve retornar um dos participantes.");
      }
      vencedores.add(vencedor);
    }
    return vencedores;
  }

  private void validarParticipantes(List<Treinador> participantes) {
    if (participantes == null || participantes.size() != 8) {
      throw new IllegalArgumentException("O torneio exige exatamente 8 treinadores.");
    }
    if (participantes.stream().anyMatch(java.util.Objects::isNull)) {
      throw new IllegalArgumentException("Os participantes não podem ser nulos.");
    }
  }
}

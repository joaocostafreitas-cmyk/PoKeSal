package poke;

import java.io.PrintStream;
import model.Item;
import model.PokeSal;
import model.RegistroBatalha;
import model.ResultadoGolpe;
import model.StatusEfeito;
import model.Terreno;

/** Registro da batalha que escreve os eventos em um PrintStream (o console, no jogo). */
public class RegistroBatalhaConsole implements RegistroBatalha {

  private final PrintStream saida;

  public RegistroBatalhaConsole(PrintStream saida) {
    this.saida = saida;
  }

  @Override
  public void registrarAtaque(PokeSal atacante, PokeSal defensor, ResultadoGolpe resultado) {
    String golpe = atacante.getGolpe().getNome();

    if (!resultado.isAcertou()) {
      saida.println(atacante.getPokeSal() + " usou " + golpe + " em "
          + defensor.getPokeSal() + ", mas errou!");
      return;
    }

    saida.println(atacante.getPokeSal() + " usou " + golpe + " em "
        + defensor.getPokeSal() + "!");

    if (resultado.isCritico()) {
      saida.println("Golpe crítico!");
    }
  }

  @Override
  public void registrarDano(PokeSal alvo, int dano) {
    saida.println(alvo.getPokeSal() + " sofreu " + dano
        + " de dano! HP restante: " + alvo.getHp());
  }

  @Override
  public void registrarStatusAplicado(PokeSal pokemon, StatusEfeito status) {
    saida.println(pokemon.getPokeSal() + " ficou " + status + "!");
  }

  @Override
  public void registrarEfeitoStatus(PokeSal pokemon, StatusEfeito status, int dano) {
    String causa = status == StatusEfeito.QUEIMADO ? "da queimadura" : "do veneno";
    saida.println(pokemon.getPokeSal() + " sofreu " + dano + " de dano " + causa + "!");
  }

  @Override
  public void registrarEfeitoTerreno(PokeSal pokemon, Terreno terreno, int cura) {
    saida.println(pokemon.getPokeSal() + " recuperou " + cura
        + " de HP no terreno " + terreno + "!");
  }

  @Override
  public void registrarUsoItem(PokeSal pokemon, Item item, int itensUsados, int limite) {
    saida.println(pokemon.getPokeSal() + " usou " + item.getnome() + "!");
    saida.println("Itens usados: " + itensUsados + "/" + limite);
  }
}
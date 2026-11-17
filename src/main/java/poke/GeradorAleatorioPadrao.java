package poke;

import java.util.Random;
import model.GeradorAleatorio;


public class GeradorAleatorioPadrao implements GeradorAleatorio {

  private final Random random = new Random();

  @Override
  public int proximoInt(int limiteExclusivo) {
    return random.nextInt(limiteExclusivo);
  }
}
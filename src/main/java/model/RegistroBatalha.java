package model;

/** Recebe os eventos de cada turno da batalha. */
public interface RegistroBatalha {

  
  void registrarAtaque(PokeSal atacante, PokeSal defensor, ResultadoGolpe resultado);

  
  void registrarDano(PokeSal alvo, int dano);

 
  void registrarStatusAplicado(PokeSal pokemon, StatusEfeito status);

 
  void registrarEfeitoStatus(PokeSal pokemon, StatusEfeito status, int dano);

  
  void registrarEfeitoTerreno(PokeSal pokemon, Terreno terreno, int cura);

  
  void registrarUsoItem(PokeSal pokemon, Item item, int itensUsados, int limite);
}
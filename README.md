# PokeSal - Simulador de Batalha

Projeto acadêmico desenvolvido em Java para simular batalhas entre Pokésals, seguindo as regras de negócio definidas no projeto da disciplina.

## Sobre o Projeto

O PokeSal é um sistema de simulação de batalhas por turnos entre treinadores. Cada treinador deve escolher um Pokésal inicial e participar de batalhas considerando atributos, tipos elementais, vantagens e desvantagens, velocidade, itens e efeitos do terreno.

O projeto foi desenvolvido com foco em boas práticas de programação, organização do código, testes automatizados e qualidade de software.

## Pokésals Iniciais

O treinador pode escolher apenas um dos seguintes Pokésals:

- BulbaSal
- CharSal
- SquirtSal
- ChikoSal
- CyndaSal
- TotoSal

Cada Pokésal possui atributos como:

- HP
- ATK
- DEF
- SPD (Velocidade)
- Tipo Elemental

## Tipos Elementais

O sistema possui três tipos elementais:

- 🔥 Fogo
- 💧 Água
- 🌱 Planta

As vantagens e desvantagens funcionam da seguinte forma:

| Tipo | Forte contra | Fraco contra |
|------|--------------|--------------|
| Fogo | Planta | Água |
| Água | Fogo | Planta |
| Planta | Água | Fogo |

Os golpes super efetivos possuem multiplicador de dano de `2.0x`, enquanto os golpes pouco efetivos possuem multiplicador de `0.5x`.

## Sistema de Batalha

As batalhas acontecem por turnos e a ordem dos ataques é determinada pelo atributo de velocidade (`SPD`) dos Pokésals.

O sistema também contempla efeitos de status aplicados ao final dos turnos, como:

- Queimado
- Envenenado
- Paralisado

Cada efeito possui impacto específico nos atributos ou HP do Pokésal.

## Efeitos do Terreno

As batalhas podem sofrer influência do ambiente do estacionamento da UCSal:

- **Asfalto Quente:** aumenta em 15% o dano de golpes do tipo Fogo.
- **Poça de Chuva / Piso Escorregadio:** golpes de Água recebem efeito adicional de 10% de precisão ou dano.
- **Canteiro Central:** Pokésals do tipo Planta recuperam 5% do HP máximo ao final de cada turno.

## Sistema de Itens

Cada treinador possui uma mochila para gerenciamento de itens de batalha.

O treinador pode utilizar no máximo **2 itens por batalha** e o uso de um item consome o turno.

Entre os exemplos de itens estão:

- Potion
- Super Potion
- Antidote

## Estrutura do Projeto

O projeto possui classes responsáveis pela representação dos Pokésals, batalhas, treinadores, itens, mochila e diferentes modos de batalha.

Entre as principais classes estão:

```text
model/
├── Acessorio.java
├── AtributoBonus.java
├── Item.java
├── PokeSal.java
├── StatusEfeito.java
├── Terreno.java
├── TipoElemental.java
└── ...

poke/
├── AcessorioRepository.java
├── Batalha.java
├── ItemRepository.java
├── Main.java
├── Mochila.java
├── ModoAleatorio.java
├── PokeSalRepository.java
└── Treinador.java

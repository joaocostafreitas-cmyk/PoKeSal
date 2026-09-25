
# 🎮 PokeSal - Simulador de Batalha

Link do video não listado do Youtube 🔴: https://youtu.be/c-tsDHOyI7E

Link do diagrama UML 📋: https://app.diagrams.net/#G1lv2FYsnngrp2-LyfjmSByS8Oz3fnPhVd#%7B"pageId"%3A"q2NDj1pSNoo5LTh0LEM2"%7D

O **PokeSal** é um sistema backend e simulador de batalhas por turnos desenvolvido em Java. Ele permite a simulação de combates entre treinadores e seus Pokésals, levando em consideração atributos, tipos elementais, vantagens e desvantagens, ordem de velocidade, itens de consumo e efeitos de terreno.

O projeto foi desenvolvido com foco em boas práticas de programação, organização de código, testes automatizados e qualidade de software.

---

## ✨ Funcionalidades e Métodos Principais

### ➕ `criarTreinador` / `escolherPokeSal`
Esta função é responsável por inicializar um novo treinador e registrar sua escolha de Pokésal inicial. O treinador deve escolher **apenas um** Pokésal para iniciar sua jornada.

### ⚔️ `executarTurno` / `atacar`
Gerencia a mecânica de batalhas por turnos. A ordem dos ataques é determinada primariamente pelo atributo de velocidade (`SPD`) de cada Pokésal. 

* **Super efetivo (`2.0x`):** Aplica o dobro de dano.
* **Pouco efetivo (`0.5x`):** Reduz o dano pela metade.

### 🎒 `usarItem`
Permite que o treinador utilize um item de sua mochila durante a batalha (como *Potion*, *Super Potion* ou *Antidote*).
> **Regra de Negócio:** Cada treinador pode usar no máximo **2 itens por batalha**, e a ação de utilizar um item consome o turno.

### 🧪 `aplicarStatus`
Aplica e calcula os efeitos de status negativos ao final de cada turno:
* 🔥 **Queimado:** Causa dano contínuo ao HP.
* ☠️ **Envenenado:** Reduz gradativamente a vida do Pokésal.
* ⚡ **Paralisado:** Afeta a velocidade (`SPD`) ou impede ações no turno.

### 🗺️ `aplicarEfeitoTerreno`
Modifica os atributos ou a eficiência dos ataques com base na ambientação (estacionamento da UCSal):
* 🌋 **Asfalto Quente:** Aumenta em 15% o dano de golpes do tipo Fogo.
* 🌧️ **Poça de Chuva / Piso Escorregadio:** Concede efeito adicional de 10% de precisão ou dano para golpes do tipo Água.
* 🌿 **Canteiro Central:** Pokésals do tipo Planta recuperam 5% do HP máximo ao final de cada turno.

---

## 🐾 Pokésals Iniciais e Atributos

Os treinadores podem escolher um dos seguintes Pokésals iniciais:

* 🟢 **BulbaSal** *(Planta)*
* 🔥 **CharSal** *(Fogo)*
* 💧 **SquirtSal** *(Água)*
* 🌿 **ChikoSal** *(Planta)*
* 🌋 **CyndaSal** *(Fogo)*
* 🌊 **TotoSal** *(Água)*

### Atributos Base
* **HP:** Pontos de Vida (*Health Points*)
* **ATK:** Poder de Ataque (*Attack*)
* **DEF:** Defesa (*Defense*)
* **SPD:** Velocidade (*Speed*)
* **Tipo Elemental:** Categoria elementar do Pokésal

---

## 📊 Tabela Elemental

| Tipo | Forte Contra (2.0x) | Fraco Contra (0.5x) |
| :--- | :--- | :--- |
| **Fogo** | Planta | Água |
| **Água** | Fogo | Planta |
| **Planta** | Água | Fogo |

---

## 📁 Estrutura do Projeto

```text
model/
├── Acessorio.java
├── AtributoBonus.java
├── Item.java
├── PokeSal.java
├── StatusEfeito.java
├── Terreno.java
└── TipoElemental.java

poke/
├── AcessorioRepository.java
├── Batalha.java
├── ItemRepository.java
├── Main.java
├── Mochila.java
├── ModoAleatorio.java
├── PokeSalRepository.java
└── Treinador.java
```

---

## 🚀 Como Usar

Para executar este projeto localmente, você precisa ter o **JDK (Java Development Kit)** instalado em sua máquina.

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/pokesal.git
   ```

2. **Navegue até o diretório do projeto:**
   ```bash
   cd pokesal
   ```

3. **Compile o projeto:**
   ```bash
   javac -d bin poke/*.java model/*.java
   ```

4. **Execute a aplicação:**
   ```bash
   java -cp bin poke.Main
   ```

---

## 🤝 Contribuindo

Contribuições são sempre bem-vindas! Siga estas etapas para contribuir:

1. Faça um **Fork** do repositório.
2. Crie uma **Branch** para a sua funcionalidade (`git checkout -b feature/NovaFuncionalidade`).
3. Confirme suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Envie para a sua **Branch** (`git push origin feature/NovaFuncionalidade`).
5. Abra um **Pull Request** detalhado explicando as mudanças efetuadas.

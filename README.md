PokeSal - Simulador de BatalhaO PokeSal é um sistema backend e simulador de batalhas por turnos desenvolvido em Java. Ele permite a simulação de combates entre treinadores e seus Pokésals, levando em consideração atributos, tipos elementais, vantagens e desvantagens, ordem de velocidade, itens de consumo e efeitos de terreno.O projeto foi desenvolvido com foco em boas práticas de programação, organização de código, testes automatizados e qualidade de software.Funcionalidades e Métodos Principais➕ criarTreinador / escolherPokeSalEsta função é responsável por inicializar um novo treinador e registrar sua escolha de Pokésal inicial. O treinador deve escolher apenas um Pokésal para iniciar sua jornada.⚔️ executarTurno / atacarGerencia a mecânica de batalhas por turnos. A ordem dos ataques é determinada primariamente pelo atributo de velocidade (SPD) de cada Pokésal. As interações consideram multiplicadores do tipo elemental:Super efetivo (2.0x): Aplica o dobro de dano.Pouco efetivo (0.5x): Reduz o dano pela metade.🎒 usarItemPermite que o treinador utilize um item de sua mochila durante a batalha (como Potion, Super Potion ou Antidote).Regra de Negócio: Cada treinador pode usar no máximo 2 itens por batalha, e a ação de utilizar um item consome a jogada do turno.🧪 aplicarStatusAplica e calcula os efeitos de status negativos ao final de cada turno:🔥 Queimado: Causa dano contínuo ao HP.☠️ Envenenado: Reduz gradativamente a vida do Pokésal.⚡ Paralisado: Afecta a velocidade (SPD) ou impede ações no turno.🗺️ aplicarEfeitoTerrenoModifica os atributos ou a eficiência dos ataques com base na ambientação (estacionamento da UCSal):Asfalto Quente: Aumenta em 15% o dano de golpes do tipo Fogo.Poça de Chuva / Piso Escorregadio: Concede efeito adicional de 10% de precisão ou dano para golpes do tipo Água.Canteiro Central: Pokésals do tipo Planta recuperam 5% do HP máximo ao final de cada turno.Pokésals Iniciais e AtributosOs treinadores podem escolher um dos seguintes Pokésals iniciais:🟢 BulbaSal (Planta)🔥 CharSal (Fogo)💧 SquirtSal (Água)🌿 ChikoSal (Planta)🌋 CyndaSal (Fogo)🌊 TotoSal (Água)Cada unidade possui os seguintes atributos fundamentais:HP: Pontos de Vida (Health Points)ATK: Poder de Ataque (Attack)DEF: Defesa (Defense)SPD: Velocidade (Speed)Tipo Elemental: Categoria elementar do PokésalTabela ElementalTipoForte Contra (2.0x)Fraco Contra (0.5x)FogoPlantaÁguaÁguaFogoPlantaPlantaÁguaFogoEstrutura do ProjetoO projeto é organizado seguindo a separação entre modelos de domínio (model) e regras/repositórios (poke):model/
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
Como UsarPara executar este projeto localmente, você precisa ter o JDK (Java Development Kit) instalado em sua máquina.Clone o repositório:git clone https://github.com/seu-usuario/pokesal.git
Navegue até o diretório do projeto:cd pokesal
Compile o projeto:javac -d bin poke/*.java model/*.java
Execute a aplicação:java -cp bin poke.Main
ContribuindoContribuições são sempre bem-vindas! Por favor, siga estes passos antes de submeter alterações:Faça um Fork do repositório.Crie uma Branch para a sua funcionalidade (git checkout -b feature/NovaFuncionalidade).Confirme suas alterações (git commit -m 'Adiciona nova funcionalidade').Envie para a sua Branch (git push origin feature/NovaFuncionalidade).Abra um Pull Request detalhado explicando as mudanças efetuadas.

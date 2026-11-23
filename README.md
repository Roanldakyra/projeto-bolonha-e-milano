# Bolonha e Milano

Jogo de estratégia e administração medieval em Java 17. De 1 a 4 jogadores governam cidades, administram recursos, fazem colheitas, arrecadam impostos, constroem estruturas e recrutam soldados. A partida acontece em rodadas anuais, de 1400 até 1410.

## Requisitos
- JDK 17 ou superior.
- Não utiliza bibliotecas externas nem JUnit.

## Estrutura
```text
bolonha-e-milano/
├── README.md
├── src/
│   └── bolonhaemilano/
│       ├── Main.java
│       ├── Menu.java
│       ├── Jogador.java
│       ├── Cidade.java
│       ├── Partida.java
│       ├── Mercado.java
│       ├── Agricultura.java
│       ├── Governo.java
│       ├── Construcoes.java
│       ├── Nobreza.java
│       └── Testes.java
├── uml/
│   ├── Diagrama-de-classes.puml
│   └── diagrama-sequencia-fim-do-ano.puml
└── casos-de-uso/
    └── diagrama-caso-de-uso.puml
```

## Como compilar
Na pasta raiz do projeto:

```bash
javac -encoding UTF-8 -d bin src/bolonhaemilano/*.java
```

## Como executar
```bash
java -cp bin bolonhaemilano.Main
```

## Como jogar
No menu principal:
1. **Iniciar novo jogo:** escolha de 1 a 4 jogadores e informe os nomes. As cidades são Bolonha, Milano, Florença e Veneza.
2. **Ver status:** consulta os recursos, governo, defesa e ações anuais da cidade da vez.
3. **Mercado:** comprar/vender grãos e terras.
4. **Agricultura:** colher uma vez por ano e distribuir alimentos.
5. **Governo:** arrecadar impostos uma vez por ano, definir taxa (até 50%) e nível de justiça.
6. **Construções:** construir estruturas ou recrutar soldados.
7. **Encerrar turno:** passa a vez. Quando o último jogador encerra, o ano avança para todas as cidades e são verificadas as promoções.
8. **Classificação:** mostra título, prestígio e pontos que faltam para o próximo título.
9. **Sobre:** informações do jogo.
0. **Sair:** encerra o programa.

## Regras principais
- Colheita e arrecadação de impostos só podem ocorrer uma vez por cidade em cada ano.
- O ano começa em 1400. A partida termina quando alguém alcança Rei/Rainha ou quando o ano passa de 1410.
- O vencedor é quem possui o título mais alto; em caso de empate, vence quem tem maior prestígio.
- Prestígio = construções × 10 + soldados × 2 + servos + terras ÷ 100. Florins não contam.
- Títulos: Cavaleiro, Barão (400), Conde (550), Marquês (700), Duque (850), Rei/Rainha (1000). No máximo uma promoção por fechamento de ano.

## Como executar os testes
Compile com o mesmo comando e execute:

```bash
java -cp bin bolonhaemilano.Testes
```

A classe `Testes` usa o padrão preparar, executar e verificar. Ela não é necessária para jogar. O resultado esperado é **37 verificações aprovadas e zero reprovadas**.

## Diagramas
Os diagramas PlantUML finais estão em `uml/` e `casos-de-uso/`.

## GitHub: finalização da entrega

O ZIP original recebido não continha a pasta `.git`, então não foi possível preservar nem reconstruir os commits das Etapas 1 a 6. Depois de colocar estes arquivos no repositório original, registre a Etapa 7 e a Etapa 8 em commits separados conforme o histórico real do seu trabalho. Para a última etapa:

```bash
git add README.md src uml casos-de-uso
git commit -m "Etapa 8: integração, testes e entrega final"
git tag etapa-8
git push
git push origin etapa-8
```

Se ainda não existir um commit separado da Etapa 7 no seu repositório, faça-o a partir da versão correspondente à Etapa 7 antes de registrar a Etapa 8.

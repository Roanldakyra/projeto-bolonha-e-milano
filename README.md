Bolonha e Milano

Jogo de estratégia econômica medieval desenvolvido em Java 17.

Etapas desenvolvidas





Etapa 1

Classe Main.

Classe Menu.

Menu interativo.





Etapa 2

Classe Jogador.

Classe Cidade.

Cadastro do jogador e cidade.

Status da cidade.





Etapa 3

Sistema de economia.

Florins, terras e grãos.

Classe Mercado.

Compra e venda de grãos.

Compra e venda de terras.

Validação dos recursos.





Etapa 4

Sistema de agricultura e população.

Adição de servos na cidade.

População inicial de 200 servos.

Classe Agricultura.

Sistema de colheita e produção de grãos.

Distribuição de alimentos.

Crescimento ou redução da população conforme a alimentação.

Atualização do status da cidade com a quantidade de servos.





Como jogar

Execute o programa.

Escolha a opção Novo jogo.

Informe o nome do jogador e da cidade.

Utilize o menu principal para acessar as funcionalidades:

Ver status da cidade: consulta o ano atual, o governante e os recursos disponíveis.

Acessar mercado: permite comprar e vender grãos e terras utilizando florins.

Acessar agricultura: permite realizar colheitas e distribuir alimentos para a população.

Sobre o jogo: apresenta informações sobre o projeto.

Sair: encerra o jogo.

Agricultura

Colher: realiza o cultivo das terras e produz novos grãos, utilizando sementes.

Distribuir alimentos: permite escolher um percentual entre 20% e 80% dos grãos disponíveis.

A população cresce quando recebe alimentos suficientes e diminui quando há falta de alimentos.





Estrutura

src/bolonhaemilano/: código Java.

uml/: diagramas de classes.

casos-de-uso/: diagramas de casos de uso.

Como compilar

javac -encoding UTF-8 -d bin src/bolonhaemilano/*.java

Como executar

java -cp bin bolonhaemilano.Main

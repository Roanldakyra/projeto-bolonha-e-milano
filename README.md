# Bolonha e Milano — Etapa 6

Jogo de estratégia econômica medieval desenvolvido em Java 17.

## Etapas contempladas

- Etapas 1 e 2: estrutura inicial, menu, jogador e cidade.
- Etapa 3: recursos e mercado.
- Etapa 4: agricultura.
- Etapa 5: impostos e governo.
- Etapa 6: construções e soldados.

## Novidades da Etapa 6

- A cidade começa com 0 soldados e 0 construções.
- Recrutar cada soldado custa 5 florins e 1 servo.
- O recrutamento valida florins e servos antes de remover qualquer recurso.
- Cada construção custa 20 florins.
- O status da cidade exibe soldados e construções.
- A opção 6 do menu abre o submenu de construções.

## Estrutura

- `src/bolonhaemilano/`: classes Java.
- `uml/`: espaço para o diagrama cumulativo de classes atualizado.
- `casos-de-uso/`: espaço para o diagrama cumulativo de casos de uso atualizado.

## Como compilar e executar

A partir da pasta do projeto:

```bash
javac -encoding UTF-8 -d bin src/bolonhaemilano/*.java
java -cp bin bolonhaemilano.Main
```

## Observação

Este ZIP parte da versão simplificada da Etapa 5 disponível nesta conversa. Compare com seu repositório original antes de substituir arquivos, especialmente se você já implementou regras adicionais nas etapas anteriores.

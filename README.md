# Sistema de Reservas – Pousada do Sol e Mar

Projeto de conclusão da disciplina **Linguagem de Programação II (LPII)**.

Aplicação de console desenvolvida em Java e baseada nos princípios da **Programação Orientada a Objetos (POO)**. O sistema tem como objetivo auxiliar a recepção de uma pousada no gerenciamento de quartos e reservas, permitindo cadastros, consultas, listagens e cancelamentos.

Os dados são armazenados em arquivo e recuperados automaticamente quando o programa é iniciado novamente.

## Funcionalidades

| Opção | Funcionalidade | Descrição |
|:---:|---|---|
| 1 | **Cadastrar quarto** | Cadastra quartos comuns ou de luxo, com informações de número, capacidade e valor da diária. Quartos de luxo possuem jacuzzi. |
| 2 | **Fazer reserva** | Solicita CPF do cliente, número do quarto e datas de entrada e saída. Verifica a disponibilidade e gera um código de reserva de 6 dígitos. |
| 3 | **Cancelar reserva** | Cancela uma reserva utilizando seu código de identificação. |
| 4 | **Listar quartos** | Exibe todos os quartos cadastrados e suas respectivas reservas. |
| 5 | **Consultar quarto** | Apresenta os dados de um quarto específico e todas as suas reservas. |
| 0 | **Sair** | Encerra o programa. |

## Regras de negócio

O sistema implementa as seguintes validações:

- O número de cada quarto deve ser único.
- A data de entrada não pode ser anterior à data atual.
- A data de saída deve ser posterior à data de entrada.
- Um quarto não pode possuir duas reservas com períodos conflitantes.
- As datas informadas devem ser válidas, impedindo entradas como `31/02/2026`.
- Cada reserva recebe um código de identificação de 6 dígitos.
- Os quartos podem ser classificados como comuns ou de luxo.

## Estrutura do projeto

```text
Sistema-de-Reservas/
│
├── principal/
│   └── Main.java                  # Menu principal
│
├── funcoes/
│   ├── Hotel.java                 # Regras e gerenciamento do sistema
│   ├── Quarto.java                # Classe de quarto comum
│   ├── QuartoLuxo.java            # Quarto de luxo (herança)
│   ├── Reserva.java               # Representação das reservas
│   └── Leitura.java               # Leitura e validação de entradas
│
├── arquivo/
│   └── Arquivo.java               # Persistência dos dados
│
├── docs/
│   ├── Documentacao_Projeto.pdf   # Requisitos, UML, mockups e Kanban
│   └── diagrama_de_classes.png    # Diagrama de classes
│
└── README.md
```

## Como executar

### Pré-requisitos

- JDK 11 ou superior instalado.
- Terminal ou ambiente de desenvolvimento, como o Visual Studio Code.

### Compilação

Na pasta raiz do projeto, execute o comando:

```bash
javac -encoding UTF-8 -d out funcoes/*.java arquivo/*.java principal/*.java
```

### Execução

Após a compilação, execute:

```bash
java -cp out principal.Main
```

**Visual Studio Code:** também é possível abrir a pasta do projeto e executar o arquivo `Main.java` utilizando o botão **Run**.

## Armazenamento de dados

Os dados do sistema são armazenados no arquivo `hotel.dat`, criado automaticamente na pasta a partir da qual o programa é executado.

As informações são recuperadas na inicialização do sistema, permitindo a continuidade do uso mesmo após o encerramento do programa.

Para iniciar o sistema sem os dados anteriores, basta excluir o arquivo `hotel.dat`.

## Diagrama de classes

O diagrama de classes apresenta a estrutura do projeto, suas classes, atributos, métodos e relacionamentos, incluindo a herança entre `Quarto` e `QuartoLuxo`.

![Diagrama de Classes](docs/diagrama_de_classes.png)

## Gerenciamento do projeto

O desenvolvimento foi organizado utilizando a metodologia **Kanban**, com o auxílio do Trello para distribuição de tarefas, acompanhamento das atividades e organização das etapas do projeto.

## Equipe

Projeto desenvolvido por:

- Artur Matias Salles
- Maria Eduarda Mucelini
- Mariana Santos Silva
- Augusto Maximo
- Arthur Tozzato
- Pedro Henrique Carvalho

---

*Projeto acadêmico desenvolvido como trabalho de conclusão da disciplina Linguagem de Programação II (LPII) - FAESA.*

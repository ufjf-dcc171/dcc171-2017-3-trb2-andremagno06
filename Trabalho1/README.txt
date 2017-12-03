Trabalho 

Aluno: Andr� Magno Ribeiro
Matr�cula: 20136060
Curso: Sistema de Informa��o


Cen�rio 

Esse sistema tem o intuito de gerenciar os pedidos feitos em uma lanchonete. Neste sistema pode incluir e excluir Mesa e Item. Pode ser gerado um relat�rio de todos os pedidos feito por uma Mesa(Cliente).

Modelo de dados

Item 
    String nome
    double preco

Mesa 
    String codigo
    List<Pedido> pedido 
    String aberto
    String fechado
    int estado

Pedido
    Item item
    int quantidade
    double preco


Constru��o das Janelas

Tabela dela de Pedidos
Combobox para selecionar item
Caixa de texto para inserir a quantidade
Bot�o de Inserir Pedido
Bot�o de Excluir Pedido
Bot�o para abrir o Pedido
Bot�o para fechar o Pedido





Tabela de Itens
Bot�o para Inserir item 
Bot�o para Excluir Item 
Campo texto para nome do Item 
Campo Texto para o pre�o do Item 

Tabela de Mesas
Bot�o para inserir Item 
Bot�o para excluir Item 





Funcionamento do programa

Os passos para que o programa funcione:

Inserir Pedidos:

1� Passo:  Selecione uma mesa na tabela de Mesas

2� Passo: Logo depois voc� selecione o bot�o �Abrir Pedido�

3� Passo: Selecione o Item a ser inserido no combo-box

4� Passo: Digite a quantidade que voc� quer inserir deste produto.

5� Passo:Clique no Bot�o �Inserir Item no Pedido� e visualize que o Item carregou na Tabela de Pedidos


Excluir Pedido:

1�Passo: Selecione o pedido na �Tabela Pedidos� e clique no bot�o �Excluir Pedido�

Excluir Item:
1�Passo: Selecione o pedido na �Tabela Item� e clique no bot�o �Excluir Item�

Excluir Mesa:

1� Passo: Selecione o pedido na �Tabela Mesa� e clique no bot�o �Excluir Mesa�

Inserir uma Mesa:

1� Passo: Clique no bot�o inserir Mesa, digite no campo o nome da Mesa que voc� quer inserir e clique em ok.

Inserir uma Item:

1� Passo: Clique no bot�o inserir Item, digite o nome do seu produto e o Pre�o e clique em Criar Pedido.

Gerar Relat�rio:

1� Passo: Para gerar relat�rio voc� tem que ter fechado uma mesa, ap�s fechar uma mesa voc� pode perceber que ele gera o relat�rio automaticamente, e caso voc� queira gera-lo de novo apenas clique em no bot�o �Gerar Relat�rio�.

Limpar Pedidos da Mesa:

1� Passo: Selecione uma Mesa e clique em �Limpar Pedido da Mesa�



Dificuldade

Tive dificuldade na parte de ajustar layout, pois existem muitos par�metros a se definir tive dificuldade tamb�m para entender o JTable, acabei optando usar a JList.

Melhorias

Pode ser usado JTable ao inv�s de JList para que as informa��es seja melhor visualizadas, colocar o cadastro de Mesas e Item em outra janela e reajustar a tabela de pedidos para que fique mais usual.

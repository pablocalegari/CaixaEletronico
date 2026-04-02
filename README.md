Atividade 1 - Caixa eletrônico (1,5 pontos)
Faça um programa para controlar um caixa eletrônico. Existem 6 tipos de notas: de 2, de 5,de 10, de
20, de 50, de 100. O programa deve inicialmente ler uma quantidade de notas de cada tipo,
simulando o abastecimento inicial do caixa eletrônico. Depois disto, o caixa entra em operação
contínua atendendo um cliente após o outro. Para sacar, o cliente fornece o valor do saque a ser
efetuado e como resultado da operação, o programa deverá então escrever na tela a quantidade de
notas de cada tipo que será dada ao cliente a fim de atender ao seu saque. Sempre que um saque
for efetuado por um cliente, a quantidade inicial de dinheiro que foi colocada no caixa é
decrementada. O programa deve pagar sempre com as maiores notas possíveis. Sempre que não
for possível pagar somente com notas de 100, então o programa tentará complementar com notas
de 50, depois com notas de 20, 10, 5 e 2. Antes de efetuar um saque para um cliente, ou seja,
escrever na tela as notas que ele irá receber, o programa deve ter certeza que é possível pagá-lo,
senão emitirá uma mensagem do tipo “Não Temos Notas Para Este Saque”. Caso o caixa fique
abaixo de um certo mínimo, o algoritmo deverá parar de atender aos clientes e emitir uma
mensagem do tipo “Caixa Vazio: Chame o Operador”.
A interface com usuário (figura 1) do caixa eletrônico já é fornecido para você, juntamente com um
contrato (Programa 2) para utilização da interface, que segue abaixo.
Figura 1 _ interface de utilização do caixa eletrônico

Programa 3 _ Classe CaixaEletronico implementando o contrato com ICaixaEletronico.
Observe que o método main de CaixaEletronico já está se comunicando com a interface
gráfica.
Essa classe deve trabalhar com uma matriz 6 x 2, responsável por guardar a quantidade de cédulas
disponível de cada valor. Veja a tabela a seguir:
Coluna 0 (valor das células) Coluna 1 (quantidade de cédulas)
100 100
50 200
20 300
10 350
5 450
2 500
• O botão Efetuar saque deve fazer uma simulação de saque no caixa eletrônico. Quando o
usuário escolher esta opção, o programa deverá solicitar o valor do saque e em seguida
efetuar o saque , mostrando na tela quantas cédulas de cada valor foram emitidas.
o O programa deve fazer o cálculo de quais cédulas serão emitidas visando emitir o
menor número de notas possível, dando prioridade para as cédulas de maior valor.
Para simular o saque, o programa deve fazer a devida atualização na matriz de
quantidades de cédulas disponíveis.
o Se as notas de algum valor acabarem, o programa deve tentar efetuar o saque através
das demais notas existentes, caso seja possível, sempre visando emitir o menor
número de cédulas.
o Se não for possível a realização do saque solicitado com a quantidade de notas
existentes, o programa deverá emitir a mensagem “Saque não realizado por falta de
cédulas”.
o O programa não deverá permitir que mais de 30 cédulas sejam emitidas,
impossibilitando os saques nesses casos.
• O botão Relatório Cédulas o programa deverá mostrar a matriz de quantidades de cédulas,
informando quantas notas estão disponíveis para cada valor no compartimento.
• O botão Valor total disponível deverá apresentar o valor total em reais disponível no caixa.
• O botão Reposição de Cédulas deve possibilitar que o usuário faça a reposição das
cédulas.
• O botão Conta Mínima deve possibilitar armazenar o valor da conta mínima. Caso o caixa
fique abaixo da cota mínima, o algoritmo deverá parar de atender aos clientes e emitir uma
mensagem do tipo “Caixa Vazio: Chame o Operador”.

IMPORTANTE:
Ao clicar no botão sair deve ser apresentado um extrato com todos os saques e atualização de
saldo, cada grupo é responsável pelo layout do extrato.
Regras para implementação dos Programas:
Vocês precisaram construir interface com usuário.
Vocês precisaram implementar a interface ICaixaEletronico fornecida para testar seu Projeto com
uma interface gráfica disponível;
Vocês não poderão mudar os métodos da interface ICaixaEletronico;
Na ICaixaEletronico está documentado exatamente o que cada método deverá fazer;
Vocês poderão conversar sobre o problema mas não poderão trocar códigos, isso poderá acarretar
em nota zero para o Projeto.

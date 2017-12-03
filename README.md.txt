Aluno: André Magno
Matricula: 201376060
Curso: Sistema de Infromação

Esse sistema tem por objetivo o gerenciamento de Comandas em uma lanchonete.

Foi usado um protocolo de persistencia com JAXBContext para fazer a comunicação com os arquivos XML.


Classes Utilizadas para fazer a persistencia:
JAXBContext ctx = JAXBContext.newInstance(new Class[]{ComandaWrapper.class, Comanda.class});
StreamSource entrada = new StreamSource("Comanda.xml");
Unmarshaller unmarshaller = ctx.createUnmarshaller();


Futuras melhoras no sistema: 

Pode ser feito possivel validações de texto, e de botões para que o sistema não venha travar.



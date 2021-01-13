create table cliente (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	email varchar(255) not null,
	telefone varchar(20) not null,
	
	primary key (id)
);
/* No Flyweight quando se cria um arquivo, por padrão o nome do arquivo começa com 'V' e um número sequencial.
Flyweight é um programa de versionamento de banco de dados. */ 
CREATE TABLE categoria(
    id uuid primary key ,
    nome varchar(255)
);

CREATE table produtos(
    id uuid primary key ,
    nome varchar(255) not null ,
    preco numeric(10,2) not null ,
    quantidade_estoque integer not null ,
    categoria_id uuid not null references categoria(id)
);
create table sel_regiao
(
    id           bigint generated by default as identity,
    municipio    varchar(255),
    sigla_estado varchar(255),
    sigla_regiao varchar(255),
    primary key (id)
);

create table sel_revenda
(
    id        bigint generated by default as identity,
    cnpj      varchar(255),
    nome      varchar(255),
    regiao_id bigint,
    primary key (id)
);

create table sel_transacao
(
    id                bigint generated by default as identity,
    bandeira          varchar(255),
    data_da_coleta    date,
    produto           varchar(255),
    unidade_de_medida varchar(255),
    valor_compra      double,
    valor_venda       double,
    revenda_id        bigint,
    primary key (id)
);

create table usuario
(
    id    bigint generated by default as identity,
    email varchar(255),
    nome  varchar(255),
    primary key (id)
);

alter table sel_revenda
    add constraint FKc5632qpjgvfqrwdpg80wdst7r
        foreign key (regiao_id)
            references sel_regiao;

alter table sel_transacao
    add constraint FKjyuckuqb04ojg52p5vyaw6r19
        foreign key (revenda_id)
            references sel_revenda;
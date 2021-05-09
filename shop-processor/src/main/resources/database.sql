create schema if not exists stock;

create table stock.products (
    id bigserial primary key auto_increment,
    product_identifier varchar(100) not null,
    amount int not null
);
create table if not exists authors(
    id serial primary key,
    first_name varchar(255),
    last_name varchar(255)
);

insert into authors(first_name,last_name)
values ('author1','author1');
insert into authors(first_name,last_name)
values ('author2','author2');
insert into authors(first_name,last_name)
values ('author3','author4');
insert into authors(first_name,last_name)
values ('author4','author4');

delete from books;

alter table books DROP COLUMN author;
alter table books
    add author_id integer,
    add foreign key (author_id) references authors(id) on delete cascade;
insert into books(isbn,title,author_id,price) values ('11-11','title1',
                                                   1,20);
insert into books(isbn,title,author_id,price) values ('22-22','title2',
                                                   2,20);
insert into books(isbn,title,author_id,price) values ('33-33','title3',
                                                   3,20);
insert into books(isbn,title,author_id,price) values ('44-44','title4',
                                                   4,20);

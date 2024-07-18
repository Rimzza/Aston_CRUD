CRUD приложение, в которой реализована  связь OneToMany. Есть две таблицы: users, books. Один юзер может иметь множество книг.
Создание таблицы users

create table users

(
    id integer generated always as identit primary key,
    
    first_name  varchar(30) not null,
    
    second_name varchar(30) not null,
    
    patronymic  varchar(30) not null,
    
    age         integer     not null
    
        constraint users_age_check
            check (age > 7)
);

Создание таблицы books

create table books

(
    id integer generated always as identity primary key,
    
    author    varchar(30)  not null,
    
    title     varchar(100) not null,
    
    taken     boolean default false,
    
    who_taken integer references users on delete set null
    
);

Основные url: http://localhost:8080/users - вывод всех пользователей в формате JSON

http://localhost:8080/users/id - вывод определенного пользователя со списком его книг(id - индекс пользователя в бд);

http://localhost:8080/books - вывод всех книг в формате JSON

http://localhost:8080/books/id - вывод определенноq книги (id - индекс книги в бд);





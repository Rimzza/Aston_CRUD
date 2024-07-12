package by.rimza.library.repositories.connectionDB;

import by.rimza.library.entities.BookEntity;
import by.rimza.library.entities.UserEntity;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PrepareStatementDB {

    @SneakyThrows
    public PreparedStatement createUser(UserEntity uzer) {
        PreparedStatement prStatement = ConnectionToDB.getConnection()
                .prepareStatement("insert into users(first_name, second_name, patronymic, age) values (?,?,?,?)");
        prStatement.setString(1, uzer.getFirstName());
        prStatement.setString(2, uzer.getSecondName());
        prStatement.setString(3, uzer.getPatronymic());
        prStatement.setInt(4, uzer.getAge());
        return prStatement;

    }

    @SneakyThrows
    public PreparedStatement deleteUser(int id) {
        PreparedStatement prStatement = ConnectionToDB.getConnection()
                .prepareStatement("delete from users where id = ?");
        prStatement.setInt(1, id);

        return prStatement;
    }

    @SneakyThrows
    public PreparedStatement updateUser(int id, UserEntity updatedUser) {
        PreparedStatement prStatement = ConnectionToDB.getConnection()
                .prepareStatement("update users set first_name = ?, second_name = ?, patronymic = ?, age = ? where id = ?");
        prStatement.setString(1, updatedUser.getFirstName());
        prStatement.setString(2, updatedUser.getSecondName());
        prStatement.setString(3, updatedUser.getPatronymic());
        prStatement.setInt(4, updatedUser.getAge());
        prStatement.setInt(5, id);
        return prStatement;
    }

    @SneakyThrows
    public PreparedStatement readUser(int id) {
        Connection connection = ConnectionToDB.getConnection();
        PreparedStatement prStatement = connection.prepareStatement("select * from users u join books b on u.id = b.who_taken where u.id = ?");
        prStatement.setInt(1, id);
        return prStatement;
    }

    @SneakyThrows
    public PreparedStatement readUser(String firstName) {
        PreparedStatement psStatement = ConnectionToDB.getConnection()
                .prepareStatement("select * from users where first_name = ?");
        psStatement.setString(1, firstName);
        return psStatement;
    }

    @SneakyThrows
    public PreparedStatement readBook(int id) {
        PreparedStatement psStatement = ConnectionToDB.getConnection()
                .prepareStatement("select * from books where id = ?");
        psStatement.setInt(1, id);
        return psStatement;
    }

    @SneakyThrows
    public PreparedStatement readBook(String title) {
        PreparedStatement psStatement = ConnectionToDB.getConnection()
                .prepareStatement("select * from books where title = ?");
        psStatement.setString(1, title);
        return psStatement;
    }

    @SneakyThrows
    public PreparedStatement deleteBook(int id) {
        PreparedStatement psStatement = ConnectionToDB.getConnection()
                .prepareStatement("delete from books where id = ?");
        psStatement.setInt(1, id);
        return psStatement;
    }

    @SneakyThrows
    public PreparedStatement createBook(BookEntity book) {
        PreparedStatement psStatement = ConnectionToDB.getConnection()
                .prepareStatement("insert into books (author, title) VALUES (?, ?)");
        psStatement.setString(1, book.getAuthor());
        psStatement.setString(2, book.getTitle());
        /*psStatement.setBoolean(3,book.isTaken());
        psStatement.setInt(4,book.getWho_taken());*/
        return psStatement;
    }

    @SneakyThrows
    public PreparedStatement updateBook(int id, BookEntity updatedBook) {
        PreparedStatement psStatement = null;
        if (updatedBook.getWho_taken() == 0) {
            psStatement = ConnectionToDB.getConnection()
                    .prepareStatement("update books set author = ?, title = ?, taken = ? where id = ?");
            psStatement.setInt(4, id);
        } else {
            psStatement = ConnectionToDB.getConnection()
                    .prepareStatement("update books set author = ?, title = ?, taken = ?, who_taken = ? where id = ?");
            psStatement.setInt(4, updatedBook.getWho_taken());
            psStatement.setInt(5, id);
        }
        psStatement.setString(1, updatedBook.getAuthor());
        psStatement.setString(2, updatedBook.getTitle());
        psStatement.setBoolean(3, updatedBook.isTaken());
        return psStatement;

    }

    @SneakyThrows
    public PreparedStatement assignOwner(int id,int idOwner){
        PreparedStatement preparedStatement = ConnectionToDB.getConnection()
                .prepareStatement("update books set who_taken = ?, taken = ? where id = ?");
        preparedStatement.setInt(1,idOwner);
        preparedStatement.setBoolean(2,true);
        preparedStatement.setInt(3,id);
        return preparedStatement;

    }

    @SneakyThrows
    public PreparedStatement removeOwner(int id) {
        PreparedStatement preparedStatement = ConnectionToDB.getConnection()
                .prepareStatement("update books set who_taken = null, taken = default where id = ?");
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }

    @SneakyThrows
    public PreparedStatement removeOwnerByIdOwner(int idOwner) {
        PreparedStatement preparedStatement = ConnectionToDB.getConnection()
                .prepareStatement("update books set taken = default where who_taken = ?");
        preparedStatement.setInt(1,idOwner);
        return preparedStatement;
    }

}

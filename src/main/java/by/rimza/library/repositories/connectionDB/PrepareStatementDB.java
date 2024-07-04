package by.rimza.library.repositories.connectionDB;

import by.rimza.library.entities.UserEntity;
import lombok.SneakyThrows;
import java.sql.PreparedStatement;

public class PrepareStatementDB {

    @SneakyThrows
    public PreparedStatement createUser( UserEntity uzer) {
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
    public PreparedStatement updateUser( int id, UserEntity updatedUser) {
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
    public PreparedStatement readUser(int id){
        PreparedStatement prStatement = ConnectionToDB.getConnection()
                .prepareStatement("select * from users where id = ?");
        prStatement.setInt(1,id);
        return prStatement;
    }

    @SneakyThrows
    public PreparedStatement readUser(String firstName){
        PreparedStatement psStatement = ConnectionToDB.getConnection()
                .prepareStatement("select * from users where first_name = ?");
        psStatement.setString(1,firstName);
        return psStatement;
    }
}

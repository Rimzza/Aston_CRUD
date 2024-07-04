package by.rimza.library.repositories.DAO;

import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.repositories.connectionDB.StatementDB;
import by.rimza.library.repositories.mappers.MapperUtils;
import lombok.Setter;
import lombok.SneakyThrows;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Setter
public class UserDAO implements DAO<UserEntity> {

    private StatementDB statementDB;
    private PrepareStatementDB prStatement;

    @SneakyThrows
    @Override
    public List<UserEntity> index() {
        checkStatement();
        List<UserEntity> users = new ArrayList<>();
        Statement statement = statementDB.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from users");
        while (resultSet.next()) {
            users.add(MapperUtils.toUserEntity(resultSet));
        }
        return users;
    }

    @SneakyThrows
    @Override
    public boolean update(int id, UserEntity updatedUser) {
        checkPrStatement();
        PreparedStatement ps = prStatement.updateUser(id, updatedUser);
        ps.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public UserEntity read(int id) {
        checkPrStatement();
        UserEntity user = null;
        PreparedStatement ps = prStatement.readUser(id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            user = MapperUtils.toUserEntity(resultSet);
        }
        return user;
    }

    @SneakyThrows
    public UserEntity read(String firstName){
        checkPrStatement();
        UserEntity user = null;
        PreparedStatement ps = prStatement.readUser(firstName);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            user = MapperUtils.toUserEntity(resultSet);
        }
        return user;

    }

    @SneakyThrows
    @Override
    public boolean delete(int id) {
        checkPrStatement();
        PreparedStatement ps = prStatement.deleteUser(id);
        ps.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public boolean create(UserEntity userEntity) {
        checkPrStatement();
        PreparedStatement ps = prStatement.createUser(userEntity);
        ps.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public void clear() {
        checkStatement();
        statementDB.createStatement().executeUpdate("truncate table users cascade ");
    }

    private void checkStatement() {
        if (statementDB == null)
            statementDB = new StatementDB();
    }

    private void checkPrStatement() {
        if (prStatement == null)
            prStatement = new PrepareStatementDB();
    }
}

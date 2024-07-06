package by.rimza.library.repositories.connectionDB;

import lombok.SneakyThrows;
import java.sql.Statement;

public class StatementDB {


    @SneakyThrows
    public Statement createStatement() {
        return ConnectionToDB.getConnection()
                .createStatement();

    }

}

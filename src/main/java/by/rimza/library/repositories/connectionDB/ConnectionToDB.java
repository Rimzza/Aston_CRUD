package by.rimza.library.repositories.connectionDB;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import java.sql.Connection;
import java.util.ResourceBundle;


public class ConnectionToDB {

    private static HikariDataSource ds;


    static {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(resource.getString("url"));
        config.setUsername(resource.getString("username"));
        config.setPassword(resource.getString("password"));
        ds = new HikariDataSource(config);
    }


    @SneakyThrows
    public static Connection getConnection(){
        return ds.getConnection();
    }
}

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
        config.setDriverClassName(org.postgresql.Driver.class.getName());
        config.setJdbcUrl(resource.getString("url"));
        config.setUsername(resource.getString("username"));
        config.setPassword(resource.getString("password"));
        config.setLeakDetectionThreshold(48000);
        config.setMaximumPoolSize(48);
        ds = new HikariDataSource(config);
    }


    @SneakyThrows
    public static Connection getConnection(){
        return ds.getConnection();
    }


    public static void close() throws Exception {
        ds.close();
    }
}

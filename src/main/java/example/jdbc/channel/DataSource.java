package example.jdbc.channel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author trink
 */
public class DataSource {

    private LinkedList<Connection> connectionsPool = new LinkedList<>();

    public DataSource() {
        try {
            for (int i = 0; i < 10; i++) {
                connectionsPool.addLast(createConnection());
            }
        } catch (SQLException e) {
            throw new ExceptionInInitializerError();
        }
    }

    public Connection getConnection() {
        return connectionsPool.removeFirst();
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/house?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";
        return DriverManager.getConnection(url, "root", "123123");
    }
}

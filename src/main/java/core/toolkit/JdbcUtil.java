package core.toolkit;

import java.sql.*;

public class JdbcUtil {

    private static String host   = "localhost";
    private static String dbName = "test";
    private static String port   = "3306";
    private static String user   = "root";
    private static String pass   = "123123";

    private static JdbcUtil instance = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private JdbcUtil() {

    }

    public static JdbcUtil getInstance() {
        if (instance == null) {
            synchronized (JdbcUtil.class) {
                if (instance == null) {
                    instance = new JdbcUtil();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";
        return DriverManager.getConnection(url, user, pass);
    }

    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Statement stat) {
        try {
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(ResultSet rs, Statement stat) {
        try {
            close(rs);
        } finally {
            close(stat);
        }
    }

    public void close(Statement stat, Connection conn) {
        try {
            close(stat);
        } finally {
            close(conn);
        }
    }

    public void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            close(rs);
        } finally {
            close(stat, conn);
        }
    }

    public void setHost(String host) {
        JdbcUtil.host = host;
    }

    public void setPort(String port) {
        JdbcUtil.port = port;
    }

    public void setDbName(String dbName) {
        JdbcUtil.dbName = dbName;
    }

    public void setUser(String user) {
        JdbcUtil.user = user;
    }

    public void setPass(String pass) {
        JdbcUtil.pass = pass;
    }
}

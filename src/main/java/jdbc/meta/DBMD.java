package jdbc.meta;

import core.toolkit.JdbcUtil;

import java.sql.*;
import java.util.*;

public class DBMD {

    private static JdbcUtil jdbcUtil = JdbcUtil.getInstance();

    public static void getInfo() {
        Connection conn = null;
        try {
            conn = jdbcUtil.getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println(dbmd.getMaxColumnsInIndex());
            System.out.println(dbmd.getDatabaseProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(conn);
        }
    }

    public static List<Map<String, Object>> getColumnList(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            conn = jdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();
            String[] labels = new String[columnCount + 1];
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-40s", rsmd.getColumnClassName(i));
                System.out.printf("%-30s", rsmd.getColumnName(i));
                System.out.printf("%-30s\n", rsmd.getColumnLabel(i));
                labels[i] = rsmd.getColumnLabel(i);
            }

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    data.put(labels[i], rs.getObject(i));
                }
                list.add(data);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, ps, conn);
        }
        return list;
    }
}

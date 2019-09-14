package jdbc.orm;

import core.toolkit.CaseUtil;
import core.toolkit.JdbcUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ORM {

    private static JdbcUtil jdbcUtil = JdbcUtil.getInstance();

    public static <Model> List<Model> getModelList(String model) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Model> list = new ArrayList<>();
        try {
            conn = jdbcUtil.getConnection();
            String sql = "select * from `" + model + "`";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] labels = new String[columnCount + 1];
            for (int i = 1; i <= columnCount; i++) {
                labels[i] = rsmd.getColumnLabel(i);
            }

            String modelName = CaseUtil.underlineToHump(model);
            modelName = "demo.orm.models." + CaseUtil.upperFirstCase(modelName);
            Model mod = (Model) Class.forName(modelName).newInstance();

            while (rs.next()) {
                for (int i = 1; i < columnCount; i++) {
                    String colName = labels[i];
                    String methodName = CaseUtil.underlineToHump(colName);
                    methodName = "set" + CaseUtil.upperFirstCase(methodName);

                    Method[] methods = mod.getClass().getMethods();

                    for (Method m : methods) {
                        if (methodName.equals(m.getName())) {
                            m.invoke(mod, rs.getObject(colName));
                        }
                    }
                }
                list.add(mod);
            }
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, ps, conn);
        }
        return list;
    }
}

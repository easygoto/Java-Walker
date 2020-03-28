package example.jdbc;

import example.jdbc.meta.DBMD;
import org.junit.Test;

import java.util.List;

public class DBMDTest {

    @Test
    public void testGetInfo() {
        DBMD.getInfo();
    }

    @Test
    public void testGetColumn() {
        String sql = "select * from `house`";
        List result = DBMD.getColumnList(sql);
        System.out.println(result);
    }
}

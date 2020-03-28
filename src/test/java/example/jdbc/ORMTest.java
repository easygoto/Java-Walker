package example.jdbc;

import example.jdbc.orm.ORM;
import org.junit.Test;

public class ORMTest {

    @Test
    public void testGetModelList() {
        System.out.println(ORM.getModelList("house"));
    }
}

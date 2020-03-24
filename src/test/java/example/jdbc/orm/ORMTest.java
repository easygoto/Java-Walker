package example.jdbc.orm;

import org.junit.Test;

public class ORMTest {

    @Test
    public void testGetModelList() {
        System.out.println(ORM.getModelList("house"));
    }
}

package jdbc.simple;

import org.junit.Test;

public class CURDTest {

    private long id = 1;

    @Test
    public void testCreate() {
        CURD.create();
    }

    @Test
    public void testUpdate() {
        CURD.update(id);
    }

    @Test
    public void testGetAll() {
        CURD.select();
    }

    @Test
    public void testGet() {
        CURD.select(id);
    }

    @Test
    public void testDelete() {
        CURD.delete(id);
    }
}

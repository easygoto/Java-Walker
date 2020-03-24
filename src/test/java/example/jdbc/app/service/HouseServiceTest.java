package example.jdbc.app.service;

import core.toolkit.DateUtil;
import example.jdbc.app.domain.House;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HouseServiceTest {

    private long         id           = 1;
    private HouseService houseService = new HouseService();
    private House        house        = new House(DateUtil.getDateSn(), new Random().nextFloat() * 10000, DateUtil.getDate());

    @Test
    public void testAdd() {
        System.out.println(houseService.add(house));
    }

    @Test
    public void testAddByProcedure() {
        System.out.println(houseService.addByProcedure(house));
    }

    @Test
    public void testAddBatch() {
        ArrayList<House> houses = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            houses.add(new House(DateUtil.getDateSn(), new Random().nextFloat() * 10000, DateUtil.getDate()));
        }
        System.out.println(Arrays.toString(houseService.addBatch(houses)));
    }

    @Test
    public void testEdit() {
        System.out.println(houseService.edit(id, house));
    }

    @Test
    public void testGet() {
        System.out.println(houseService.get(id));
    }

    @Test
    public void testDelete() {
        System.out.println(houseService.delete(id));
    }
}

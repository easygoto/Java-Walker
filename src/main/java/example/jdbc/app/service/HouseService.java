package example.jdbc.app.service;

import example.jdbc.app.dao.HouseDao;
import example.jdbc.app.dao.impl.HouseDaoJdbcImpl;
import example.jdbc.app.domain.House;

import java.util.ArrayList;

public class HouseService {

    private HouseDao houseDao = new HouseDaoJdbcImpl();

    public long add(House house) {
        return houseDao.add(house);
    }

    public long addByProcedure(House house) {
        return houseDao.addByProcedure(house);
    }

    public int[] addBatch(ArrayList<House> houses) {
        return houseDao.addBatch(houses);
    }

    public boolean edit(long id, House house) {
        return houseDao.edit(id, house);
    }

    public House get(long id) {
        return houseDao.get(id);
    }

    public boolean delete(long id) {
        return houseDao.delete(id);
    }
}

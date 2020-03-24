package example.jdbc.app.dao;

import example.jdbc.app.domain.House;

import java.util.ArrayList;

/**
 * @author trink
 */
public interface HouseDao {

    public long add(House house);

    public long addByProcedure(House house);

    public int[] addBatch(ArrayList<House> houses);

    public House get(long id);

    public boolean edit(long id, House house);

    public boolean delete(long id);
}

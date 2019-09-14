package jdbc.app.dao.impl;

import core.toolkit.DateUtil;
import core.toolkit.JdbcUtil;
import jdbc.app.dao.HouseDao;
import jdbc.app.domain.House;

import java.sql.*;
import java.util.ArrayList;

public class HouseDaoJdbcImpl implements HouseDao {

    private JdbcUtil jdbcUtil = JdbcUtil.getInstance();

    @Override
    public long add(House house) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Savepoint sp = null;
        try {
            conn = jdbcUtil.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint(); // 保存点

            String sql = "insert into `house` (`name`,`money`,`release_time`,`created_at`,`updated_at`,`status`,`is_delete`) values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setHouse(ps, house);

            int rows = ps.executeUpdate();
            if (rows <= 0) {
                return 0;
            }

            rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                return 0;
            }

            long id = rs.getLong(1);
            conn.commit();

            return id;
        } catch (SQLException e) {
            try {
                if (conn != null && sp != null) {
                    conn.rollback(sp); // 回滚到保存点
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public long addByProcedure(House house) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = jdbcUtil.getConnection();

            String sql = "{call add_house(?,?,?,?)}";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(4, Types.BIGINT);

            cs.setString(1, house.getName());
            cs.setFloat(2, house.getMoney());
            cs.setTimestamp(3, Timestamp.valueOf(DateUtil.getDateString(house.getReleaseTime())));

            int row = cs.executeUpdate();
            if (row <= 0) {
                return 0;
            }

            return cs.getLong(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(cs, conn);
        }
        return 0;
    }

    @Override
    public int[] addBatch(ArrayList<House> houses) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcUtil.getConnection();

            String sql = "insert into `house` (`name`,`money`,`release_time`,`created_at`,`updated_at`,`status`,`is_delete`) values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            for (House house : houses) {
                setHouse(ps, house);
                ps.addBatch();
            }

            return ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(ps, conn);
        }

        return new int[0];
    }

    @Override
    public House get(long id) {
        if (id <= 0) {
            return null;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        House house = new House();
        try {
            conn = jdbcUtil.getConnection();

            String sql = "select * from house where `is_delete`=? and `id`=? limit 1";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setLong(2, id);

            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return house.setId(rs.getLong("id"))
                    .setName(rs.getString("name"))
                    .setMoney(rs.getFloat("money"))
                    .setReleaseTime(rs.getDate("release_time"))
                    .setCreatedAt(rs.getDate("created_at"))
                    .setUpdatedAt(rs.getDate("updated_at"))
                    .setStatus(rs.getInt("status"))
                    .setDelete(rs.getBoolean("is_delete"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public boolean edit(long id, House house) {
        if (id <= 0) {
            return false;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcUtil.getConnection();

            String sql = "update `house` set `name`=?,`money`=?,`release_time`=?,`created_at`=?,`updated_at`=?,`status`=?,`is_delete`=? where id=?";
            ps = conn.prepareStatement(sql);
            setHouse(ps, house);
            ps.setLong(8, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(ps, conn);
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        if (id <= 0) {
            return false;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcUtil.getConnection();

            String sql = "delete from house where `id`=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            int row = ps.executeUpdate();

            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(ps, conn);
        }
        return false;
    }

    private void setHouse(PreparedStatement ps, House house) throws SQLException {
        ps.setString(1, house.getName());
        ps.setFloat(2, house.getMoney());
        ps.setTimestamp(3, Timestamp.valueOf(DateUtil.getDateString(house.getReleaseTime())));
        ps.setTimestamp(4, Timestamp.valueOf(DateUtil.getDateString(house.getCreatedAt())));
        ps.setTimestamp(5, Timestamp.valueOf(DateUtil.getDateString(house.getUpdatedAt())));
        ps.setInt(6, house.getStatus());
        ps.setBoolean(7, house.isDelete());
    }
}

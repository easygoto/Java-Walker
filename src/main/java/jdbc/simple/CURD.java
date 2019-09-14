package jdbc.simple;

import core.toolkit.DateUtil;
import core.toolkit.FileUtil;
import core.toolkit.JdbcUtil;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class CURD {

    private static Random   random   = new Random();
    private static JdbcUtil jdbcUtil = JdbcUtil.getInstance();

    public static void create() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BufferedReader bufr = null;
        BufferedInputStream bufin = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "insert into user (`real_name`,`nickname`,`gender`,`salary`,`created_at`,`updated_at`,`operated_at`,`last_login_at`,`status`,`is_delete`,`description`,`avatar`) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, "");
            ps.setString(2, "");
            ps.setInt(3, random.nextBoolean() ? (short) 1 : (short) 0);
            ps.setFloat(4, random.nextFloat() * 10000);
            ps.setTimestamp(5, Timestamp.valueOf(DateUtil.getDateString()));
            ps.setTimestamp(6, Timestamp.valueOf(DateUtil.getDateString()));
            ps.setTimestamp(7, Timestamp.valueOf(DateUtil.getDateString()));
            ps.setTimestamp(8, Timestamp.valueOf(DateUtil.getDateString()));
            ps.setInt(9, 0);
            ps.setBoolean(10, false);

            bufr = new BufferedReader(new FileReader(new File("src/big-text.txt")));
            ps.setCharacterStream(11, bufr);

            bufin = new BufferedInputStream(new FileInputStream(new File("src/big-image.jpg")));
            ps.setBlob(12, bufin);

            int affectRows = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            ArrayList<Long> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getLong(1));
            }

            System.out.println("affect rows: " + affectRows);
            idList.forEach(System.out::println);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, ps, conn);
            FileUtil.close(bufr);
            FileUtil.close(bufin);
        }
    }

    public static void update(long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "update `user` set real_name=?,nickname=? where id=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, "admin");
            ps.setString(2, "admin");
            ps.setLong(3, id);

            int affectRows = ps.executeUpdate();

            System.out.println("affect rows: " + affectRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(ps, conn);
        }
    }

    public static void select(long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BufferedReader bufr = null;
        BufferedWriter bufw = null;
        BufferedInputStream bufin = null;
        BufferedOutputStream bufout = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "select * from `user` where id=? and is_delete=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setBoolean(2, false);
            rs = ps.executeQuery();
            if (rs.next()) {
                Clob clob = rs.getClob("description");
                bufr = new BufferedReader(clob.getCharacterStream());
                bufw = new BufferedWriter(new FileWriter(new File("src/temp/" + DateUtil.getDateSn() + ".txt")));

                char[] chars = new char[1024];
                for (int len; (len = bufr.read(chars)) > 0; ) {
                    bufw.write(chars, 0, len);
                }

                Blob blob = rs.getBlob("avatar");
                bufin = new BufferedInputStream(blob.getBinaryStream());
                bufout = new BufferedOutputStream(new FileOutputStream(new File("src/temp/" + DateUtil.getDateSn() + ".jpg")));

                byte[] bytes = new byte[102400];
                for (int len; (len = bufin.read(bytes)) > 0; ) {
                    bufout.write(bytes, 0, len);
                }

                System.out.println("id: " + rs.getLong("id"));
                System.out.println("real_name: " + rs.getString("real_name"));
                System.out.println("nickname: " + rs.getString("nickname"));
                System.out.println("description: " + clob);
                System.out.println("gender: " + rs.getInt("gender"));
                System.out.println("salary: " + rs.getDouble("salary"));
                System.out.println("avatar: " + rs.getBlob("avatar"));
                System.out.println("created_at: " + rs.getTimestamp("created_at"));
                System.out.println("updated_at: " + rs.getTimestamp("updated_at"));
                System.out.println("operated_at: " + rs.getTimestamp("operated_at"));
                System.out.println("last_login_at: " + rs.getTimestamp("last_login_at"));
                System.out.println("status: " + rs.getInt("status"));
                System.out.println("is_delete: " + rs.getBoolean("is_delete"));
            } else {
                System.out.println("not found !");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, ps, conn);
            FileUtil.close(bufr);
            FileUtil.close(bufw);
            FileUtil.close(bufin);
            FileUtil.close(bufout);
        }
    }

    public static void select() {
        Connection conn = null;
        PreparedStatement countPs = null;
        ResultSet countRs = null;
        PreparedStatement listPs = null;
        ResultSet listRs = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "select count(1) as total from `user` where is_delete=?";
            countPs = conn.prepareStatement(sql);
            countPs.setBoolean(1, false);
            countRs = countPs.executeQuery();
            countRs.next();
            System.out.println(countRs.getInt("total"));

            sql = "select `id`,`real_name`,`nickname`,`gender`,`salary`,`created_at`,`updated_at`,`operated_at`,`last_login_at`,`status`,`is_delete` from `user` where `is_delete`=?";
            listPs = conn.prepareStatement(sql);
            listPs.setBoolean(1, false);
            listRs = listPs.executeQuery();
            while (listRs.next()) {
                System.out.println(listRs.getRow() + ": " +
                        listRs.getLong("id") + ", " +
                        listRs.getString("real_name") + ", " +
                        listRs.getString("nickname") + ", " +
                        listRs.getInt("gender") + ", " +
                        listRs.getDouble("salary") + ", " +
                        listRs.getTimestamp("created_at") + ", " +
                        listRs.getTimestamp("updated_at") + ", " +
                        listRs.getTimestamp("operated_at") + ", " +
                        listRs.getTimestamp("last_login_at") + ", " +
                        listRs.getInt("status") + ", " +
                        listRs.getBoolean("is_delete")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(listRs, listPs);
            jdbcUtil.close(countRs, countPs);
            jdbcUtil.close(conn);
        }
    }

    public static void delete(long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "delete from user where id = ?";
            ps = conn.prepareStatement(sql);

            ps.setLong(1, id);

            int affectRows = ps.executeUpdate();

            System.out.println("affect rows: " + affectRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(ps, conn);
        }
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Uloge;
import util.DbUtil;

public class UlogaDao {
private Connection con;
PreparedStatement ps;
Statement st;
ResultSet rs;

    public UlogaDao() {
        con = DbUtil.getConnection();
    }
    // add uloga
    public void addUloga(Uloge uloga) {
        try {
            ps = con.prepareStatement("insert into uloge(naziv, ovlascenja) values (?, ?)");
            ps.setString(1, uloga.getNaziv());
            ps.setString(2, uloga.getOvlascenja());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // delete uloga
    public void deleteUloga(int uloga_id) {
        try {
            ps = con.prepareStatement("delete from uloge where uloga_id=?");
            ps.setInt(1, uloga_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // update uloga
    public void updateUloga(Uloge uloga) {
        try {
            ps = con.prepareStatement("update uloge set naziv=?, ovlascenja=?" +
                            "where uloga_id=?");
            ps.setString(1, uloga.getNaziv());
            ps.setString(2, uloga.getOvlascenja());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // get all uloge
    public List<Uloge> getAllUloge() {
        List<Uloge> uloga = new ArrayList<Uloge>();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from uloge");
            while (rs.next()) {
                Uloge ulo = new Uloge();
                ulo.setUloga_id(rs.getInt("uloga_id"));
                ulo.setNaziv(rs.getString("naziv"));
                ulo.setOvlascenja(rs.getString("ovlascenja"));
                uloga.add(ulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uloga;
    }
    // get uloga by ID
    public Uloge getUlogaById(int uloga_id) {
        Uloge uloga = new Uloge();
        try {
            ps = con.prepareStatement("select * from uloge where uloga_id=?");
            ps.setInt(1, uloga_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                uloga.setUloga_id(rs.getInt("uloga_id"));
                uloga.setNaziv(rs.getString("naziv"));
                uloga.setOvlascenja(rs.getString("ovlascenja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uloga;
    }
}

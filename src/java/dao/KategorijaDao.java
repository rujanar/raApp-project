package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Kategorije;
import util.DbUtil;

public class KategorijaDao {
    private Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public KategorijaDao() {
        con = DbUtil.getConnection();
    }
    // add kategorija
    public void addKategorija(Kategorije kategorija) {
        try {
            ps = con.prepareStatement("insert into kategorije (naziv, email) values (?, ?)");
            ps.setString(1, kategorija.getNaziv());
            ps.setString(2, kategorija.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // delete kategorija
    public void deleteKategorija(int kategorija_id) {
        try {
            ps = con.prepareStatement("delete from kategorije where kategorija_id=?");
            ps.setInt(1, kategorija_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // update kategorija
    public void updateKategorija(Kategorije kategorija) {
        try {
            ps = con.prepareStatement("update kategorije set naziv=?, email=? " +
                            "where kategorija_id=?");
            ps.setString(1, kategorija.getNaziv());
            ps.setString(2, kategorija.getEmail());
            ps.setInt(3, kategorija.getKategorija_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // get all kategorije
    public List<Kategorije> getAllKategorije() {
        List<Kategorije> kategorija = new ArrayList<Kategorije>();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from kategorije");
            while (rs.next()) {
                Kategorije kat = new Kategorije();
                kat.setKategorija_id(rs.getInt("kategorija_id"));
                kat.setNaziv(rs.getString("naziv"));
                kat.setEmail(rs.getString("email"));
                kategorija.add(kat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kategorija;
    }
    // get kategorija by ID
    public Kategorije getKategorijaById(int kategorija_id) {
        Kategorije kategorija = new Kategorije();
        try {
            ps = con.prepareStatement("select * from kategorije where kategorija_id=?");
            ps.setInt(1, kategorija_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                kategorija.setKategorija_id(rs.getInt("kategorija_id"));
                kategorija.setNaziv(rs.getString("naziv"));
                kategorija.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kategorija;
    }
}


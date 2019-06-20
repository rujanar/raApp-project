package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Korisnici;
import util.DbUtil;

public class KorisnikDao {
    private Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public KorisnikDao() {
        con = DbUtil.getConnection();
    }
    // add korisnik
    public void addKorisnik(Korisnici korisnik) {
        try {
            ps = con.prepareStatement("insert into korisnici(ime, prezime, email, username, password, kategorija_id, uloga_id) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, korisnik.getIme());
            ps.setString(2, korisnik.getPrezime());
            ps.setString(3, korisnik.getEmail());
            ps.setString(4, korisnik.getUsername());
            ps.setString(5, korisnik.getPassword());
            ps.setInt(6, korisnik.getKor_kategorija_id());
            ps.setInt(7, korisnik.getKor_uloga_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // delete korisnik
    public void deleteKorisnik(int korisnik_id) {
        try {
            ps = con.prepareStatement("delete from korisnici where korisnik_id=?");
            ps.setInt(1, korisnik_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // update korisnik
    public void updateKorisnik(Korisnici korisnik) {
        try {
            ps = con.prepareStatement("update korisnici set ime=?, prezime=?, email=?, username=?, password=?, kategorija_id=?, uloga_id=? " +
                            "where korisnik_id=?");
            ps.setString(1, korisnik.getIme());
            ps.setString(2, korisnik.getPrezime());
            ps.setString(3, korisnik.getEmail());
            ps.setString(4, korisnik.getUsername());
            ps.setString(5, korisnik.getPassword());
            ps.setInt(6, korisnik.getKor_kategorija_id());
            ps.setInt(7, korisnik.getKor_uloga_id());
            ps.setInt(8, korisnik.getKorisnik_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // get all korisnici
    public List<Korisnici> getAllKorisnici() {
        List<Korisnici> korisnik = new ArrayList<Korisnici>();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from korisnici");
            while (rs.next()) {
                Korisnici kor = new Korisnici();
                kor.setKorisnik_id(rs.getInt("korisnik_id"));
                kor.setIme(rs.getString("ime"));
                kor.setPrezime(rs.getString("prezime"));
                kor.setEmail(rs.getString("email"));
                kor.setUsername(rs.getString("username"));
                kor.setPassword(rs.getString("password"));
                kor.setKor_kategorija_id(rs.getInt("kategorija_id"));
                kor.setKor_uloga_id(rs.getInt("uloga_id"));
                korisnik.add(kor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return korisnik;
    }
    // get korisnik by ID
    public Korisnici getKorisnikById(int korisnik_id) {
        Korisnici korisnik = new Korisnici();
        try {
            ps = con.prepareStatement("select * from korisnici where korisnik_id=?");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                korisnik.setKorisnik_id(rs.getInt("korisnik_id"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setEmail(rs.getString("email"));
                korisnik.setUsername(rs.getString("username"));
                korisnik.setPassword(rs.getString("password"));
                korisnik.setKor_kategorija_id(rs.getInt("kategorija_id"));
                korisnik.setKor_uloga_id(rs.getInt("uloga_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return korisnik;
    }
    // get korisnik by username and password
    public Korisnici getKorisnikLogin (Korisnici korisnik){
        try {
            ps = con.prepareStatement("select * from korisnici where username=? and password=? limit 1");
            ps.setString(1, korisnik.getUsername());
            ps.setString(2, korisnik.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                korisnik.setKorisnik_id(rs.getInt("korisnik_id"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setEmail(rs.getString("email"));
                korisnik.setUsername(rs.getString("username"));
                korisnik.setPassword(rs.getString("password"));
                korisnik.setKor_kategorija_id(rs.getInt("kategorija_id"));
                korisnik.setKor_uloga_id(rs.getInt("uloga_id"));
                korisnik.setValid(true);
            } else {
                korisnik.setValid(false);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return korisnik;
    }
}
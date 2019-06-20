package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aktivnosti;
import util.DbUtil;

public class AktivnostDao {
    private Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public AktivnostDao() {
        con = DbUtil.getConnection();
    }
    // add aktivnost
    public void addAktivnost(Aktivnosti aktivnost) {
        try {
            ps = con.prepareStatement("insert into aktivnosti(zadatak_id, aktivnost_opis, potrebno_vreme, aktivnost_rok, korisnik_id, status) values (?, ?, ?, str_to_date(?, '%d.%m.%Y.'), ?, ?)");
            ps.setInt(1, aktivnost.getAkt_zadatak_id());
            ps.setString(2, aktivnost.getAktivnost_opis());
            ps.setString(3, aktivnost.getPotrebno_vreme());
            ps.setString(4, aktivnost.getAktivnost_rok());
            ps.setInt(5, aktivnost.getAkt_korisnik_id());
            ps.setString(6, aktivnost.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // delete aktivnost
    public void deleteAktivnost(int aktivnost_id) {
        try {
            ps = con.prepareStatement("delete from aktivnosti where aktivnost_id=?");
            ps.setInt(1, aktivnost_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // update aktivnost
    public void updateAktivnost(Aktivnosti aktivnost) {
        try {
            ps = con.prepareStatement("update aktivnosti set zadatak_id=?, aktivnost_opis=?, potrebno_vreme=?, aktivnost_rok=str_to_date(?, '%d.%m.%Y.'), korisnik_id=?, status=? where aktivnost_id=?");
            ps.setInt(1, aktivnost.getAkt_zadatak_id());
            ps.setString(2, aktivnost.getAktivnost_opis());
            ps.setString(3, aktivnost.getPotrebno_vreme());
            ps.setString(4, aktivnost.getAktivnost_rok());
            ps.setInt(5, aktivnost.getAkt_korisnik_id());
            ps.setString(6, aktivnost.getStatus());
            ps.setInt(7, aktivnost.getAktivnost_id());
            
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // get all aktivnosti
    public List<Aktivnosti> getAllAktivnosti() {
        List<Aktivnosti> aktivnost = new ArrayList<Aktivnosti>();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from aktivnosti");
            while (rs.next()) {
                Aktivnosti akt = new Aktivnosti();
                akt.setAktivnost_id(rs.getInt("aktivnost_id"));
                akt.setAkt_zadatak_id(rs.getInt("zadatak_id"));
                akt.setAktivnost_opis(rs.getString("aktivnost_opis"));
                akt.setPotrebno_vreme(rs.getString("potrebno_vreme"));
                String datumbaza = rs.getString("aktivnost_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                akt.setAktivnost_rok(datum);
                akt.setAkt_korisnik_id(rs.getInt("korisnik_id"));
                akt.setStatus(rs.getString("status"));
                aktivnost.add(akt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aktivnost;
    }
    // get aktivnost by ID
    public Aktivnosti getAktivnostById(int aktivnost_id) {
        Aktivnosti aktivnost = new Aktivnosti();
        try {
            ps = con.prepareStatement("select * from aktivnosti where aktivnost_id=?");
            ps.setInt(1, aktivnost_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                aktivnost.setAktivnost_id(rs.getInt("aktivnost_id"));
                aktivnost.setAkt_zadatak_id(rs.getInt("zadatak_id"));
                aktivnost.setAktivnost_opis(rs.getString("aktivnost_opis"));
                aktivnost.setPotrebno_vreme(rs.getString("potrebno_vreme"));
                aktivnost.setAktivnost_rok(rs.getString("aktivnost_rok"));
                aktivnost.setAkt_korisnik_id(rs.getInt("korisnik_id"));
                aktivnost.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aktivnost;
    }
    // get aktivnosti by trenutni korisnik
    public List<Aktivnosti> getAllAktivnostiByTrenutniKorisnik(int korisnik_id) {
        List<Aktivnosti> aktivnost = new ArrayList<Aktivnosti>();
        try {
            ps = con.prepareStatement("select * from aktivnosti where korisnik_id=?");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Aktivnosti akt = new Aktivnosti();
                akt.setAktivnost_id(rs.getInt("aktivnost_id"));
                akt.setAkt_zadatak_id(rs.getInt("zadatak_id"));
                akt.setAktivnost_opis(rs.getString("aktivnost_opis"));
                akt.setPotrebno_vreme(rs.getString("potrebno_vreme"));
                String datumbaza = rs.getString("aktivnost_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                akt.setAktivnost_rok(datum);
                akt.setAkt_korisnik_id(rs.getInt("korisnik_id"));
                akt.setStatus(rs.getString("status"));
                aktivnost.add(akt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aktivnost;
    }
    // update status nezapočeto -> u toku
    public void updateStatusUtoku(int aktivnost_id) {
        try {
            ps = con.prepareStatement("update aktivnosti set status='U toku' where aktivnost_id=?");
            ps.setInt(1, aktivnost_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update status u toku -> završeno
    public void updateStatusZavrseno(int aktivnost_id) {
        try {
            ps = con.prepareStatement("update aktivnosti set status='Završeno' where aktivnost_id=?");
            ps.setInt(1, aktivnost_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Zadaci;
import util.DbUtil;

public class ZadatakDao {

    private Connection con;
    public int lastId;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public ZadatakDao() {
        con = DbUtil.getConnection();
        
    }
    
    // add zadatak
    public void addZadatak(Zadaci zadatak) {
        try {
            ps = con.prepareStatement("insert into zadaci(opis, korisnik_id, zadatak_rok) values (?, ?, str_to_date(?, '%d.%m.%Y.'))");
            ps.setString(1, zadatak.getOpis());
            ps.setInt(2, zadatak.getZad_korisnik_id());
            ps.setString(3, zadatak.getZadatak_rok());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // delete zadatak and aktivnosti by zadatak_id
    public void deleteZadatak(int zadatak_id) {
        try {
            Statement s1 = con.createStatement();
            Statement s2 = con.createStatement();
            ps = con.prepareStatement("delete zadaci, aktivnosti from zadaci left join aktivnosti on zadaci.zadatak_id=aktivnosti.zadatak_id where zadaci.zadatak_id=?");
            ps.setInt(1, zadatak_id);
            s1.executeQuery("SET SQL_SAFE_UPDATES = 0");
            ps.executeUpdate();
            s2.executeQuery("SET SQL_SAFE_UPDATES = 1");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // update zadatak
    public void updateZadatak(Zadaci zadatak) {
        try {
            ps = con.prepareStatement("update zadaci set opis=?, korisnik_id=?, zadatak_rok=str_to_date(?, '%d.%m.%Y.') " +
                            "where zadatak_id=?");
            ps.setString(1, zadatak.getOpis());
            ps.setInt(2, zadatak.getZad_korisnik_id());
            ps.setString(3, zadatak.getZadatak_rok());
            ps.setInt(4, zadatak.getZadatak_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // get all zadaci
    public List<Zadaci> getAllZadaci() {
        List<Zadaci> zadatak = new ArrayList<Zadaci>();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from zadaci");
            while (rs.next()) {
                Zadaci zad = new Zadaci();
                zad.setZadatak_id(rs.getInt("zadatak_id"));
                zad.setOpis(rs.getString("opis"));
                zad.setZad_korisnik_id(rs.getInt("korisnik_id"));
                String datumbaza = rs.getString("zadatak_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                zad.setZadatak_rok(datum);
                zadatak.add(zad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zadatak;
    }
    // get zadatak by ID
    public Zadaci getZadatakById(int zadatak_id) {
        Zadaci zadatak = new Zadaci();
        try {
            ps = con.prepareStatement("select * from zadaci where zadatak_id=?");
            ps.setInt(1, zadatak_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                zadatak.setZadatak_id(rs.getInt("zadatak_id"));
                zadatak.setOpis(rs.getString("opis"));
                zadatak.setZad_korisnik_id(rs.getInt("korisnik_id"));
                String datumbaza = rs.getString("zadatak_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                zadatak.setZadatak_rok(datum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zadatak;
    }
    // get zadaci by logged in korisnik - Nezapočeto
    public List<Zadaci> getAllZadaciByTrNezapoceto(int korisnik_id) {
        List<Zadaci> zadatak = new ArrayList<Zadaci>();
        try {
            ps = con.prepareStatement("select zadaci.zadatak_id, zadaci.opis, zadaci.korisnik_id, zadaci.zadatak_rok from zadaci left join aktivnosti on zadaci.zadatak_id=aktivnosti.zadatak_id where aktivnosti.status='Nezapočeto' and aktivnosti.korisnik_id=? group by zadaci.zadatak_id");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zadaci zad = new Zadaci();
                zad.setZadatak_id(rs.getInt("zadatak_id"));
                zad.setOpis(rs.getString("opis"));
                zad.setZad_korisnik_id(rs.getInt("korisnik_id"));
                String datumbaza = rs.getString("zadatak_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                zad.setZadatak_rok(datum);
                zadatak.add(zad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zadatak;
    }
    // get zadaci by logged in korisnik - U toku
    public List<Zadaci> getAllZadaciByTrUtoku(int korisnik_id) {
        List<Zadaci> zadatak = new ArrayList<Zadaci>();
        try {
            ps = con.prepareStatement("select zadaci.zadatak_id, zadaci.opis, zadaci.korisnik_id, zadaci.zadatak_rok from zadaci left join aktivnosti on zadaci.zadatak_id=aktivnosti.zadatak_id where aktivnosti.status='U toku' and aktivnosti.korisnik_id=? group by zadaci.zadatak_id");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zadaci zad = new Zadaci();
                zad.setZadatak_id(rs.getInt("zadatak_id"));
                zad.setOpis(rs.getString("opis"));
                zad.setZad_korisnik_id(rs.getInt("korisnik_id"));
                String datumbaza = rs.getString("zadatak_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                zad.setZadatak_rok(datum);
                zadatak.add(zad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zadatak;
    }
    // get zadaci by logged in korisnik - Završeno
    public List<Zadaci> getAllZadaciByTrZavrseno(int korisnik_id) {
        List<Zadaci> zadatak = new ArrayList<Zadaci>();
        try {
            ps = con.prepareStatement("select zadaci.zadatak_id, zadaci.opis, zadaci.korisnik_id, zadaci.zadatak_rok from zadaci left join aktivnosti on zadaci.zadatak_id=aktivnosti.zadatak_id where aktivnosti.status='Završeno' and aktivnosti.korisnik_id=? group by zadaci.zadatak_id");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zadaci zad = new Zadaci();
                zad.setZadatak_id(rs.getInt("zadatak_id"));
                zad.setOpis(rs.getString("opis"));
                zad.setZad_korisnik_id(rs.getInt("korisnik_id"));
                String datumbaza = rs.getString("zadatak_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                zad.setZadatak_rok(datum);
                zadatak.add(zad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zadatak;
    }
    
    // get last inserted id in zadaci
    public int getLastInsertedId() throws SQLException{
        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
        rs = ps.executeQuery();
            if (rs.next())
                {
                lastId = rs.getInt("last_insert_id()");            
                }
        return lastId;
    }
    
    // pretraga zadaci
    public List<Zadaci> pretragaZadaci(Zadaci zadatak, String[] period) {
        List<Zadaci> zadatak2 = new ArrayList<Zadaci>();
        try {
            int id = zadatak.getZad_korisnik_id();
            String a = period[0];
            String b = period[1];
            String opis = zadatak.getOpis();
            
            if(!opis.isEmpty()){
            
            if (id == 0 && (a.isEmpty() || b.isEmpty())){
                ps = con.prepareStatement("select * from zadaci where opis like ?");
                ps.setString(1, "%" + zadatak.getOpis() + "%");
                rs = ps.executeQuery();
            } else if(id != 0 && (a.isEmpty() || b.isEmpty())){
                ps = con.prepareStatement("select * from zadaci where opis like ? and zadaci.korisnik_id=?");
                ps.setString(1, "%" + zadatak.getOpis() + "%");
                ps.setInt(2, zadatak.getZad_korisnik_id());
                rs = ps.executeQuery();
            } else if(id == 0 && !a.isEmpty() && !b.isEmpty()){
                ps = con.prepareStatement("select * from zadaci where opis like ? and zadatak_rok between str_to_date(?, '%d.%m.%Y.') and str_to_date(?, '%d.%m.%Y.')");
                ps.setString(1, "%" + zadatak.getOpis() + "%");
                ps.setString(2, a);
                ps.setString(3, b);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement("select * from zadaci where opis like ? and zadaci.korisnik_id=? and zadatak_rok between str_to_date(?, '%d.%m.%Y.') and str_to_date(?, '%d.%m.%Y.')");
                ps.setString(1, "%" + zadatak.getOpis() + "%");
                ps.setInt(2, zadatak.getZad_korisnik_id());
                ps.setString(3, a);
                ps.setString(4, b);
                rs = ps.executeQuery();
            }
            } else {
                
            if (id == 0 && (a.isEmpty() || b.isEmpty())){
                return null;
            } else if(id != 0 && (a.isEmpty() || b.isEmpty())){
                ps = con.prepareStatement("select * from zadaci where zadaci.korisnik_id=?");
                ps.setInt(1, zadatak.getZad_korisnik_id());
                rs = ps.executeQuery();
            } else if(id == 0 && !a.isEmpty() && !b.isEmpty()){
                ps = con.prepareStatement("select * from zadaci where zadatak_rok between str_to_date(?, '%d.%m.%Y.') and str_to_date(?, '%d.%m.%Y.')");
                ps.setString(1, a);
                ps.setString(2, b);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement("select * from zadaci where zadaci.korisnik_id=? and zadatak_rok between str_to_date(?, '%d.%m.%Y.') and str_to_date(?, '%d.%m.%Y.')");
                ps.setInt(1, zadatak.getZad_korisnik_id());
                ps.setString(2, a);
                ps.setString(3, b);
                rs = ps.executeQuery();
            }
            
            }
            while (rs.next()) {
                Zadaci zad = new Zadaci();
                zad.setZadatak_id(rs.getInt("zadatak_id"));
                zad.setOpis(rs.getString("opis"));
                zad.setZad_korisnik_id(rs.getInt("korisnik_id"));
                String datumbaza = rs.getString("zadatak_rok");
                char[] datumbazaniz = datumbaza.toCharArray();
                String datum = String.valueOf(datumbazaniz[8]) + String.valueOf(datumbazaniz[9]) + "." + String.valueOf(datumbazaniz[5]) + String.valueOf(datumbazaniz[6]) + "." + String.valueOf(datumbazaniz[0]) + String.valueOf(datumbazaniz[1]) + String.valueOf(datumbazaniz[2]) + String.valueOf(datumbazaniz[3]) + ".";
                zad.setZadatak_rok(datum);
                zadatak2.add(zad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zadatak2;
    }
}
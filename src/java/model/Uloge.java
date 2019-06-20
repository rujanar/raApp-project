
package model;

public class Uloge {

    private int uloga_id;
    private String naziv;
    private String ovlascenja;

    public int getUloga_id() {
        return uloga_id;
    }

    public void setUloga_id(int uloga_id) {
        this.uloga_id = uloga_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOvlascenja() {
        return ovlascenja;
    }

    public void setOvlascenja(String ovlascenja) {
        this.ovlascenja = ovlascenja;
    }

    @Override
    public String toString() {
        return "Uloge{" + "uloga_id=" + uloga_id + ", naziv=" + naziv + ", ovlascenja=" + ovlascenja + '}';
    }

    
    

    
}

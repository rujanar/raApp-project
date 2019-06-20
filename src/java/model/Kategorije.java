package model;


public class Kategorije {

    private int kategorija_id;
    private String naziv;
    private String email;

    public int getKategorija_id() {
        return kategorija_id;
    }

    public void setKategorija_id(int kategorija_id) {
        this.kategorija_id = kategorija_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Kategorije{" + "kategorija_id=" + kategorija_id + ", naziv=" + naziv + ", email=" + email + '}';
    }

   

    

   
}

    
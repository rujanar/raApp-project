package model;

public class Aktivnosti {
    
    private int aktivnost_id;
    private int akt_zadatak_id;
    private String aktivnost_opis;
    private String potrebno_vreme;
    private String aktivnost_rok;
    private int akt_korisnik_id;
    private String status;

    public int getAktivnost_id() {
        return aktivnost_id;
    }

    public void setAktivnost_id(int aktivnost_id) {
        this.aktivnost_id = aktivnost_id;
    }

    public int getAkt_zadatak_id() {
        return akt_zadatak_id;
    }

    public void setAkt_zadatak_id(int akt_zadatak_id) {
        this.akt_zadatak_id = akt_zadatak_id;
    }

    public String getAktivnost_opis() {
        return aktivnost_opis;
    }

    public void setAktivnost_opis(String aktivnost_opis) {
        this.aktivnost_opis = aktivnost_opis;
    }

    public String getPotrebno_vreme() {
        return potrebno_vreme;
    }

    public void setPotrebno_vreme(String potrebno_vreme) {
        this.potrebno_vreme = potrebno_vreme;
    }

    public String getAktivnost_rok() {
        return aktivnost_rok;
    }

    public void setAktivnost_rok(String aktivnost_rok) {
        this.aktivnost_rok = aktivnost_rok;
    }

    public int getAkt_korisnik_id() {
        return akt_korisnik_id;
    }

    public void setAkt_korisnik_id(int akt_korisnik_id) {
        this.akt_korisnik_id = akt_korisnik_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aktivnosti{" + "aktivnost_id=" + aktivnost_id + ", akt_zadatak_id=" + akt_zadatak_id + ", aktivnost_opis=" + aktivnost_opis + ", potrebno_vreme=" + potrebno_vreme + ", aktivnost_rok=" + aktivnost_rok + ", akt_korisnik_id=" + akt_korisnik_id + ", status=" + status + '}';
    }

    
}
package model;

public class Zadaci {

    private int zadatak_id;
    private String opis;
    private int zad_korisnik_id;
    private String zadatak_rok;

    public int getZadatak_id() {
        return zadatak_id;
    }

    public void setZadatak_id(int zadatak_id) {
        this.zadatak_id = zadatak_id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getZad_korisnik_id() {
        return zad_korisnik_id;
    }

    public void setZad_korisnik_id(int zad_korisnik_id) {
        this.zad_korisnik_id = zad_korisnik_id;
    }

    public String getZadatak_rok() {
        return zadatak_rok;
    }

    public void setZadatak_rok(String zadatak_rok) {
        this.zadatak_rok = zadatak_rok;
    }

    @Override
    public String toString() {
        return "Zadaci{" + "zadatak_id=" + zadatak_id + ", opis=" + opis + ", zad_korisnik_id=" + zad_korisnik_id + ", zadatak_rok=" + zadatak_rok + '}';
    }


}

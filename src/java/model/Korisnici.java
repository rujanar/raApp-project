
package model;

public class Korisnici {

    private int korisnik_id;
    private String ime;
    private String prezime;
    private String email;
    private String username;
    private String password;
    private int kor_kategorija_id;
    private int kor_uloga_id;
    public boolean valid;

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKor_kategorija_id() {
        return kor_kategorija_id;
    }

    public void setKor_kategorija_id(int kor_kategorija_id) {
        this.kor_kategorija_id = kor_kategorija_id;
    }

    public int getKor_uloga_id() {
        return kor_uloga_id;
    }

    public void setKor_uloga_id(int kor_uloga_id) {
        this.kor_uloga_id = kor_uloga_id;
    }
    
    public boolean isValid(){
        return valid;
    }

    public void setValid(boolean newValid) {
        valid = newValid;
    }

    @Override
    public String toString() {
        return "Korisnici{" + "korisnik_id=" + korisnik_id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", username=" + username + ", password=" + password + ", kor_kategorija_id=" + kor_kategorija_id + ", kor_uloga_id=" + kor_uloga_id + '}';
    }
    


}
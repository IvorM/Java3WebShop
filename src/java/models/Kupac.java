package models;

public class Kupac {
    private int IDKupac;
    private String KorisnickoIme;
    private String Lozinka;

    public Kupac(int IDKupac, String KorisnickoIme, String Lozinka) {
        this.IDKupac = IDKupac;
        this.KorisnickoIme = KorisnickoIme;
        this.Lozinka = Lozinka;
    }

    public Kupac() {      
    }

    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String KorisnickoIme) {
        this.KorisnickoIme = KorisnickoIme;
    }

    public int getIDKupac() {
        return IDKupac;
    }

    public void setIDKupac(int IDKupac) {
        this.IDKupac = IDKupac;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String Lozinka) {
        this.Lozinka = Lozinka;
    }
}

package models;

public class Stavka {
    private int IDStavka;
    private int RacunID;
    private int ProizvodID;
    private int Kolicina;
    private double CijenaKomad;
    private double CijenaUkupno;
    private String Naziv;

    public Stavka(int IDStavka, int RacunID, int ProizvodID, int Kolicina, double CijenaKomad, double CijenaUkupno) {
        this.IDStavka = IDStavka;
        this.RacunID = RacunID;
        this.ProizvodID = ProizvodID;
        this.Kolicina = Kolicina;
        this.CijenaKomad = CijenaKomad;
        this.CijenaUkupno = CijenaUkupno;
    }

    public Stavka() {
    }
    

    public int getIDStavka() {
        return IDStavka;
    }

    public void setIDStavka(int IDStavka) {
        this.IDStavka = IDStavka;
    }
    
     public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public int getRacunID() {
        return RacunID;
    }

    public void setRacunID(int RacunID) {
        this.RacunID = RacunID;
    }

    public int getProizvodID() {
        return ProizvodID;
    }

    public void setProizvodID(int ProizvodID) {
        this.ProizvodID = ProizvodID;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    public double getCijenaKomad() {
        return CijenaKomad;
    }

    public void setCijenaKomad(double CijenaKomad) {
        this.CijenaKomad = CijenaKomad;
    }

    public double getCijenaUkupno() {
        return CijenaUkupno;
    }

    public void setCijenaUkupno(double CijenaUkupno) {
        this.CijenaUkupno = CijenaUkupno;
    }

    public Stavka(int IDStavka, int RacunID, int ProizvodID,String Naziv, int Kolicina, double CijenaKomad, double CijenaUkupno) {
        this.IDStavka = IDStavka;
        this.RacunID = RacunID;
        this.ProizvodID = ProizvodID;
        this.Kolicina = Kolicina;
        this.CijenaKomad = CijenaKomad;
        this.CijenaUkupno = CijenaUkupno;
        this.Naziv = Naziv;
    }
    
    
}

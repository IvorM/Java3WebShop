package models;

import java.util.Date;
import java.util.List;

public class Racun {

    private int IDRacun;
    private int KupacID;
    private Date Datum;
    private List<Stavka> stavkeRacuna;


    public Racun(List<Stavka> stavke) {
        this.stavkeRacuna = stavke;
    }

    public Racun(int idracun, int kupacid) {
        this.IDRacun = idracun;
        this.KupacID = kupacid;
    }

    public int getIDRacun() {
        return IDRacun;
    }

    public void setIDRacun(int IDRacun) {
        this.IDRacun = IDRacun;
    }


      public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date Datum) {
        this.Datum = Datum;
    }
    
    public int getKupacID() {
        return KupacID;
    }

    public void setKupacID(int KupacID) {
        this.KupacID = KupacID;
    }

    public List<Stavka> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(List<Stavka> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }
}

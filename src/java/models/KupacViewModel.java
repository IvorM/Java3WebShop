package models;

import java.util.ArrayList;
import java.util.List;



public class KupacViewModel {
    private int IDKupac;
    private String KorisnickoIme;
    private String Lozinka;
    private boolean Administrator;

    /**
     * @return the IDKupac
     */
    public int getIDKupac() {
        return IDKupac;
    }

    /**
     * @param IDKupac the IDKupac to set
     */
    public void setIDKupac(int IDKupac) {
        this.IDKupac = IDKupac;
    }

    /**
     * @return the KorisnickoIme
     */
    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    /**
     * @param KorisnickoIme the KorisnickoIme to set
     */
    public void setKorisnickoIme(String KorisnickoIme) {
        this.KorisnickoIme = KorisnickoIme;
    }

    /**
     * @return the Lozinka
     */
    public String getLozinka() {
        return Lozinka;
    }

    /**
     * @param Lozinka the Lozinka to set
     */
    public void setLozinka(String Lozinka) {
        this.Lozinka = Lozinka;
    }

    /**
     * @return the Administrator
     */
    public boolean isAdministrator() {
        return Administrator;
    }

    /**
     * @param Administrator the Administrator to set
     */
    public void setAdministrator(boolean Administrator) {
        this.Administrator = Administrator;
    }

    public KupacViewModel() {
      
    }
    
}

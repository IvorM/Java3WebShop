/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Ivor
 */
public class RacunViewModel {
    private int IDRacun;
    private int KupacID;
    private Date Datum;
    private Double Cijena;

    public RacunViewModel(int IDRacun, int KupacID, Date Datum) {
        this.IDRacun = IDRacun;
        this.KupacID = KupacID;
        this.Datum = Datum;
    }

 
    public int getIDRacun() {
        return IDRacun;
    }

    public void setIDRacun(int IDRacun) {
        this.IDRacun = IDRacun;
    }

    public int getKupacID() {
        return KupacID;
    }

    public void setKupacID(int KupacID) {
        this.KupacID = KupacID;
    }

    public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date Datum) {
        this.Datum = Datum;
    }

    public Double getCijena() {
        return Cijena;
    }

    public void setCijena(Double Cijena) {
        this.Cijena = Cijena;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Ivor
 */
public class StavkaViewModel {
    private int proizvodID;
    private int kolicina;
    private double cijenaKomad; 

    /**
     * @return the proizvodID
     */
    public int getProizvodID() {
        return proizvodID;
    }

    /**
     * @param proizvodID the proizvodID to set
     */
    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    /**
     * @return the kolicina
     */
    public int getKolicina() {
        return kolicina;
    }

    /**
     * @param kolicina the kolicina to set
     */
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    /**
     * @return the cijenaKomad
     */
    public double getCijenaKomad() {
        return cijenaKomad;
    }

    /**
     * @param cijenaKomad the cijenaKomad to set
     */
    public void setCijenaKomad(double cijenaKomad) {
        this.cijenaKomad = cijenaKomad;
    }
    
}

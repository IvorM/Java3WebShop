package models;

public class KosaricaViewModel {
    private int Stavka;
    private int Kolicina;
    private double Cijena;
    private String Naziv;

    /**
     * @return the int
     */
    public int getStavka() {
        return Stavka;
    }

    /**
     * @param Stavka the int to set
     */
    public void setStavka(int Stavka) {
        this.Stavka = Stavka;
    }

     public String getNaziv() {
        return Naziv;
    }

  
    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }
    /**
     * @return the Kolicina
     */
    public int getKolicina() {
        return Kolicina;
    }

    /**
     * @param Kolicina the Kolicina to set
     */
    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    public KosaricaViewModel(int Stavka, int Kolicina, double Cijena) {
        this.Stavka = Stavka;
        this.Kolicina = Kolicina;
        this.Cijena = Cijena;
    }

    public KosaricaViewModel() {
    }

    public double getCijena() {
        return Cijena;
    }

    public void setCijena(double Cijena) {
        this.Cijena = Cijena;
    }

    public KosaricaViewModel(int Stavka, int Kolicina, double Cijena, String Naziv) {
        this.Stavka = Stavka;
        this.Kolicina = Kolicina;
        this.Cijena = Cijena;
        this.Naziv = Naziv;
    }
    
    
    
    
}

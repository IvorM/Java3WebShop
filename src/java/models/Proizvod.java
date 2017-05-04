package models;

public class Proizvod {
    private int IDProizvod;
    private String Naziv;
    private double Cijena;
    private int KategorijaID;
    private String Slika;

    public Proizvod(int IDProizvod, String Naziv, double Cijena, int KategorijaID) {
        this.IDProizvod = IDProizvod;
        this.Naziv = Naziv;
        this.Cijena = Cijena;
        this.KategorijaID = KategorijaID;
    }
    
    public Proizvod(int IDProizvod, String Naziv, double Cijena, int KategorijaID,String Slika) {
        this.IDProizvod = IDProizvod;
        this.Naziv = Naziv;
        this.Cijena = Cijena;
        this.KategorijaID = KategorijaID;
        this.Slika=Slika;
    }

    public Proizvod() {
    }
    

    public int getIDProizvod() {
        return IDProizvod;
    }

    public void setIDProizvod(int IDProizvod) {
        this.IDProizvod = IDProizvod;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public double getCijena() {
        return Cijena;
    }

    public void setCijena(double Cijena) {
        this.Cijena = Cijena;
    }

    public int getKategorijaID() {
        return KategorijaID;
    }

    public void setKategorijaID(int KategorijaID) {
        this.KategorijaID = KategorijaID;
    }
    
     public String getSlika() {
        return Slika;
    }

    public void setSlika(String Slika) {
        this.Slika = Slika;
    }
}

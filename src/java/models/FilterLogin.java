package models;

import java.util.Date;

public class FilterLogin {
    private int ID;
    private int KupacID;
    private String IPAdresa;
    private Date Datum;
    private String NazivKupca;

    public FilterLogin() {
    }

    public FilterLogin(int ID, int KupacID, String IPAdresa) {
        this.ID = ID;
        this.KupacID = KupacID;
        this.IPAdresa = IPAdresa;
    }
    
     public FilterLogin(int ID, int KupacID,Date Datum, String IPAdresa) {
        this.ID = ID;
        this.KupacID = KupacID;
        this.Datum=Datum;
        this.IPAdresa = IPAdresa;
    }

    public FilterLogin(int ID, int KupacID, Date Datum, String IPAdresa, String NazivKupca) {
        this.ID = ID;
        this.KupacID = KupacID;
        this.IPAdresa = IPAdresa;
        this.Datum = Datum;
        this.NazivKupca = NazivKupca;
    }
    
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    
    /**
     * @return the ID
     */
    public String getNazivKupca() {
        return NazivKupca;
    }

    /**
     * @param ID the ID to set
     */
    public void setNazivKupca(String NazivKupca) {
        this.NazivKupca = NazivKupca;
    }
    
    /**
     * @return the KupacID
     */
    public int getKupacID() {
        return KupacID;
    }

    /**
     * @param KupacID the KupacID to set
     */
    public void setKupacID(int KupacID) {
        this.KupacID = KupacID;
    }
    
     public Date getDatum() {
        return Datum;
    }

    /**
     * @param KupacID the KupacID to set
     */
    public void Datum(Date Datum) {
        this.Datum = Datum;
    }

    /**
     * @return the IPAdresa
     */
    public String getIPAdresa() {
        return IPAdresa;
    }

    /**
     * @param IPAdresa the IPAdresa to set
     */
    public void setIPAdresa(String IPAdresa) {
        this.IPAdresa = IPAdresa;
    }
}

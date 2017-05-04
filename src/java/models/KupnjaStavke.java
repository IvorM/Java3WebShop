package models;

import java.util.List;

/**
 *
 * @author Ivor
 */
public class KupnjaStavke {

    private double cijenaUkupno;
    private List<StavkaViewModel> stavke;

    public KupnjaStavke() {

    }

    public double getCijenaUkupno() {
        return cijenaUkupno;
    }

    public void setCijenaUkupno(double cijenaUkupno) {
        this.cijenaUkupno = cijenaUkupno;
    }

    public List<StavkaViewModel> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaViewModel> stavke) {
        this.stavke = stavke;
    }
}

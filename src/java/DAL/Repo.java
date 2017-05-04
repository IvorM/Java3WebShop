package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.CallableStatement;
import models.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Types;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

public class Repo {

    public static DataSource createDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("");
        ds.setDatabaseName("");
        ds.setUser("");
        ds.setPassword("");
        return ds;
    }

    /*
     1. SELECT
     */
    //GET PROIZVODI
    public static List<Proizvod> getProizvodi() throws SQLException {
        List<Proizvod> result = new LinkedList<>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT IDProizvod, Naziv, Cijena, KategorijaID,Slika FROM Proizvod");
            while (resultSet.next()) {
                result.add(new Proizvod(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4),resultSet.getString(5)));
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return result;
    }

    public static List<Proizvod> getProizvodiByKategorija(int KategorijaID) throws SQLException {
        List<Proizvod> result = new LinkedList<>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT IDProizvod, Naziv, Cijena, KategorijaID, Slika FROM Proizvod WHERE KategorijaID=" + KategorijaID);
            while (resultSet.next()) {
                result.add(new Proizvod(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4),resultSet.getString(5)));
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return result;
    }

    public static Proizvod getProizvodiByID(int ProizvodID) throws SQLException {
        Proizvod proizvod = new Proizvod();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT IDProizvod, Naziv, Cijena, KategorijaID,Slika FROM Proizvod WHERE IDProizvod=" + ProizvodID);
            while (resultSet.next()) {
                proizvod.setIDProizvod(resultSet.getInt(1));
                proizvod.setNaziv(resultSet.getString(2));
                proizvod.setCijena(resultSet.getDouble(3));
                proizvod.setKategorijaID(resultSet.getInt(4));
                proizvod.setSlika(resultSet.getString(5));
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return proizvod;
    }

    public static String getProizvodiNazivByID(int ProizvodID) throws SQLException {
        String Naziv = "";
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT  Naziv FROM Proizvod WHERE IDProizvod=" + ProizvodID);
            while (resultSet.next()) {
                Naziv = (resultSet.getString(1));
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return Naziv;
    }

    //GET RACUNI

    public static List<Racun> getRacuni() throws SQLException {
        List<Racun> result = new LinkedList<Racun>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT IDRacun, KupacID FROM Racun");
            while (resultSet.next()) {
                Racun r = new Racun(resultSet.getInt(1), resultSet.getInt(2));

                ResultSet resultSet2 = statement.executeQuery("SELECT IDStavka, ProizvodID, Kolicina, CijenaKomad, CijenaUkupno FROM Stavka WHERE RacunID = " + r.getIDRacun());
                List<Stavka> stavkeRacuna = new LinkedList<>();
                while (resultSet2.next()) {
                    stavkeRacuna.add(new Stavka(resultSet2.getInt(1), r.getIDRacun(), resultSet2.getInt(2), resultSet2.getInt(3), resultSet2.getDouble(4), resultSet2.getDouble(5)));
                }
                r.setStavkeRacuna(stavkeRacuna);
                result.add(r);
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return result;
    }

    public static List<RacunViewModel> getRacuniViewModel() throws SQLException {
        List<RacunViewModel> result = new LinkedList<RacunViewModel>();
        List<RacunViewModel> racuni = new ArrayList<RacunViewModel>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT IDRacun, KupacID, Datum FROM Racun");
            while (resultSet.next()) {
                RacunViewModel r = new RacunViewModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3));
                racuni.add(r);
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        for (RacunViewModel racun : racuni) {
            try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {
                ResultSet resultSet2 = statement.executeQuery("SELECT CijenaUkupno FROM Stavka WHERE RacunID = " + racun.getIDRacun());
                List<Double> stavkeRacuna = new LinkedList<>();
                while (resultSet2.next()) {
                    stavkeRacuna.add(resultSet2.getDouble(1));
                }
                Double ukupno = 0d;
                for (double cijena : stavkeRacuna) {
                    ukupno += cijena;
                }

                racun.setCijena(ukupno);
                result.add(racun);
            } catch (Exception e) {
            }
        }

        return result;
    }

    public static List<RacunViewModel> getRacuniViewModelByKupacID(int kupacID) throws SQLException {
        List<RacunViewModel> result = new LinkedList<RacunViewModel>();
        List<RacunViewModel> racuni = new ArrayList<RacunViewModel>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT IDRacun, KupacID, Datum FROM Racun where KupacID=" + kupacID);
            while (resultSet.next()) {
                RacunViewModel r = new RacunViewModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3));
                racuni.add(r);
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        for (RacunViewModel racun : racuni) {
            try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {
                ResultSet resultSet2 = statement.executeQuery("SELECT CijenaUkupno FROM Stavka WHERE RacunID = " + racun.getIDRacun());
                List<Double> stavkeRacuna = new LinkedList<>();
                while (resultSet2.next()) {
                    stavkeRacuna.add(resultSet2.getDouble(1));
                }
                Double ukupno = 0d;
                for (double cijena : stavkeRacuna) {
                    ukupno += cijena;
                }

                racun.setCijena(ukupno);
                result.add(racun);
            } catch (Exception e) {
            }
        }

        return result;
    }

    //GET STAVKE
    public static List<Stavka> getStavke(int IDRacun) throws SQLException {
        List<Stavka> result = new LinkedList<Stavka>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT IDStavka, RacunID, ProizvodID, p.Naziv as Naziv, Kolicina, CijenaKomad, CijenaUkupno FROM Stavka inner join Proizvod as p on Stavka.ProizvodID=p.IDProizvod WHERE RacunID = " + IDRacun);
            while (resultSet.next()) {
                result.add(new Stavka(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getDouble(7)));
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return result;
    }

    /*
     2. INSERT
     */
    //INSERT KUPAC
    public static boolean insertKupac(Kupac kupac) throws SQLException {
        // kreiraj konekciju i pripremi naredbu za izvrsavanje SQL procedure
        try (Connection conn = createDataSource().getConnection(); CallableStatement stmnt = conn.prepareCall("{CALL InsertKupac(?,?)}")) {

            // izvrsi pripremljenu naredbu - samo se mjenjaju parametri
            stmnt.setString(1, kupac.getKorisnickoIme());
            stmnt.setString(2, kupac.getLozinka());

            stmnt.executeUpdate();
            return true;
        } catch (Exception e) {
            insertLog(e.getMessage());
            return false;
        }
    }

    //INSERT RACUN
    public static void insertRacun(Racun racun) throws SQLException {
        List<Stavka> stavke = racun.getStavkeRacuna();
        int IDRacun = 0;
        try (Connection conn = createDataSource().getConnection(); CallableStatement stmnt = conn.prepareCall("{? = CALL InsertRacun(?,?)}")) {

            // izvrsi pripremljenu naredbu - samo se mjenjaju parametri
            Calendar cal = Calendar.getInstance();
            Date date = new Date(cal.getTimeInMillis());
            stmnt.setInt(1, IDRacun);
            stmnt.setInt(2, racun.getKupacID());
            stmnt.setDate(3, date);
            stmnt.registerOutParameter(1, Types.INTEGER);

            stmnt.execute();
            IDRacun = stmnt.getInt(1);

        } catch (Exception e) {
            insertLog(e.getMessage());
        }
        for (Stavka stavka : stavke) {
            try (Connection conn = createDataSource().getConnection(); CallableStatement stmnt = conn.prepareCall("{CALL InsertStavka(?,?,?,?,?)}")) {
                stmnt.setInt(1, IDRacun);
                stmnt.setInt(2, stavka.getProizvodID());
                stmnt.setInt(3, stavka.getKolicina());
                stmnt.setDouble(4, stavka.getCijenaKomad());
                stmnt.setDouble(5, stavka.getCijenaUkupno());
                stmnt.executeUpdate();

            } catch (Exception e) {
                insertLog(e.getMessage());
            }
        }
    }

    //LOG ERROR
    public static void insertLog(String msg) throws SQLException {
        try (Connection conn = createDataSource().getConnection(); CallableStatement stmnt = conn.prepareCall("{CALL InsertLog(?)}")) {
            Calendar cal = Calendar.getInstance();
            Date date = new Date(cal.getTimeInMillis());
            msg = msg + date.toString();
            stmnt.setString(1, msg);
            stmnt.executeUpdate();

        } catch (Exception e) {

        }
    }

    public static KupacViewModel getKupac(String KorisnickoIme, String Lozinka) throws SQLException {
        try (Connection connection = createDataSource().getConnection(); CallableStatement stmnt = connection.prepareCall("{CALL sp_ProvjeraKupca(?,?)}")) {
            KupacViewModel kupac;

            stmnt.setString(1, KorisnickoIme);
            stmnt.setString(2, Lozinka);

            ResultSet resultSet = stmnt.executeQuery();
            while (resultSet.next()) {
                kupac = new KupacViewModel();
                kupac.setIDKupac(resultSet.getInt(1));
                kupac.setKorisnickoIme(resultSet.getString(2));
                kupac.setLozinka(resultSet.getString(3));
                kupac.setAdministrator(resultSet.getBoolean(4));

                return kupac;
            }
            return null;

        } catch (Exception e) {
            insertLog(e.getMessage());
            return null;
        }
    }

    public static void insertLogin(FilterLogin login) throws SQLException {
        // kreiraj konekciju i pripremi naredbu za izvrsavanje SQL procedure
        try (Connection conn = createDataSource().getConnection(); CallableStatement stmnt = conn.prepareCall("{CALL InsertFilterLog(?,?)}")) {

            // izvrsi pripremljenu naredbu - samo se mjenjaju parametri
            stmnt.setInt(1, login.getKupacID());
            stmnt.setString(2, login.getIPAdresa());

            stmnt.executeUpdate();

        } catch (Exception e) {
            insertLog(e.getMessage());
        }
    }

    public static List<FilterLogin> getFilter() throws SQLException {
        List<FilterLogin> result = new LinkedList<>();
        try (Connection connection = createDataSource().getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT ID, KupacID, Datum, IPAdresa,k.KorisnickoIme as Naziv from Filter inner join Kupac as k on Filter.KupacID=k.IDKupac order by Datum desc");
            while (resultSet.next()) {
                result.add(new FilterLogin(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5)));
            }

        } catch (Exception e) {
            insertLog(e.getMessage());
        }

        return result;
    }
}

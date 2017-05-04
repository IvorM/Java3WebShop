package controllers;

import DAL.Repo;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.KosaricaViewModel;
import models.KupacViewModel;
import models.KupnjaStavke;
import models.Racun;
import models.Stavka;
import models.StavkaViewModel;
import org.json.*;

public class KosaricaZavrsiKupnjuServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.json.JSONException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException, SQLException {
        Gson gson = new Gson();
        // Citanje stavki
        KupnjaStavke stavkeModel = gson.fromJson(request.getParameter("Data"), KupnjaStavke.class);
        List<Stavka> stavke = new LinkedList<>();
        for (StavkaViewModel stavkaModel : stavkeModel.getStavke()) {
            Stavka s = new Stavka();
            s.setCijenaKomad(stavkaModel.getCijenaKomad());
            s.setCijenaUkupno(stavkeModel.getCijenaUkupno());
            s.setKolicina(stavkaModel.getKolicina());
            s.setProizvodID(stavkaModel.getProizvodID());

            stavke.add(s);
        }
        KupacViewModel kupac = (KupacViewModel) request.getSession().getAttribute("aktivniKorisnik");
        Racun racun = new Racun(stavke);
        if (kupac != null) {
            racun.setKupacID((kupac.getIDKupac()));
            if (request.getSession().getAttribute("kosarica") != null) {
                Repo.insertRacun(racun);
                request.getSession().setAttribute("kosarica", null);

//                response.sendRedirect("index.jsp");
                response.setStatus(200);
            }
        } else {
//            response.sendRedirect("kosarica.jsp");
            response.setStatus(500);
        }
        response.sendRedirect("kosarica.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(KosaricaZavrsiKupnjuServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KosaricaZavrsiKupnjuServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(KosaricaZavrsiKupnjuServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KosaricaZavrsiKupnjuServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

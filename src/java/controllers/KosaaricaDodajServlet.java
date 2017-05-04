package controllers;

import DAL.Repo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.KosaricaViewModel;
import models.KupacViewModel;

public class KosaaricaDodajServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int proizvodID = Integer.parseInt(request.getParameter("proizvodID"));
        int kolicina = Integer.parseInt(request.getParameter("kolicina"));
        double cijena = Double.parseDouble(request.getParameter("cijena"));

        if (request.getSession().getAttribute("kosarica") != null) {
            List<KosaricaViewModel> kosarica = (List<KosaricaViewModel>) request.getSession().getAttribute("kosarica");
            boolean postoji = false;
            for (KosaricaViewModel kosaricaTemp : kosarica) {
                if (kosaricaTemp.getStavka() == proizvodID) {
                    kosaricaTemp.setKolicina(kolicina);
                    kosaricaTemp.setCijena(cijena);
                    kosaricaTemp.setNaziv(Repo.getProizvodiNazivByID(proizvodID));
                    postoji = true;
                }
            }
            if (!postoji) {
                kosarica.add(new KosaricaViewModel(proizvodID, kolicina, cijena,Repo.getProizvodiNazivByID(proizvodID)));
                request.setAttribute("kosarica", kosarica);
            }

        } else {
            List<KosaricaViewModel> kosarica = new ArrayList<KosaricaViewModel>();
            kosarica.add(new KosaricaViewModel(proizvodID, kolicina, cijena,Repo.getProizvodiNazivByID(proizvodID)));
            request.getSession().setAttribute("kosarica", kosarica);
        }

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
        } catch (SQLException ex) {
            Logger.getLogger(KosaaricaDodajServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(KosaaricaDodajServlet.class.getName()).log(Level.SEVERE, null, ex);
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

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.KosaricaViewModel;

public class KosaricaUrediServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int proizvodID = Integer.parseInt(request.getParameter("proizvodID"));
        int kolicina = Integer.parseInt(request.getParameter("kolicina"));
        double cijena = Double.parseDouble(request.getParameter("cijena"));

        if (request.getSession().getAttribute("kosarica") != null) {
            List<KosaricaViewModel> kosarica = (List<KosaricaViewModel>) request.getSession().getAttribute("kosarica");
            for (KosaricaViewModel kosaricaProizvodi : kosarica) {
                if (kosaricaProizvodi.getStavka() == proizvodID) {
                    kosaricaProizvodi.setKolicina(kolicina);
                    kosaricaProizvodi.setCijena(cijena);
                }
            }

            request.setAttribute("kosarica", kosarica);
            response.setStatus(200);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

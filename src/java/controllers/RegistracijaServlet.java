package controllers;

import DAL.Repo;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Kupac;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistracijaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Kupac kupac=new Kupac();
        kupac.setKorisnickoIme(request.getParameter("username"));
        kupac.setLozinka(request.getParameter("passwd"));
        
        try {
            boolean rezultat=Repo.insertKupac(kupac);
            if (rezultat) {
                PrintWriter out = response.getWriter();
                JSONObject obj=new JSONObject();
                obj.put("status","OK");
                obj.put("poruka","Uspješno registrirani");
                
                out.write(obj.toString());
            }
            else
            {
                PrintWriter out = response.getWriter();
                JSONObject obj=new JSONObject();
                obj.put("status","Error");
                obj.put("poruka","Greška prilikom registracije");
                
                out.write(obj.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrijavaServlet.class.getName()).log(Level.INFO, ex.getMessage());
            response.sendRedirect("registracija.jsp");
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
        } catch (JSONException ex) {
            Logger.getLogger(RegistracijaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistracijaServlet.class.getName()).log(Level.SEVERE, null, ex);
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

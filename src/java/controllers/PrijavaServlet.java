package controllers;

import DAL.Repo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.KupacViewModel;

public class PrijavaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("username");
        String passwd = request.getParameter("passwd");

        request.getSession().getAttribute("aktivniKorisnik");

        KupacViewModel kupac;
        try {
            kupac = Repo.getKupac(user, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(PrijavaServlet.class.getName()).log(Level.INFO, ex.getMessage());
            kupac = null;
        }
        if (kupac != null) {
            if (request.getSession().getAttribute("aktivniKorisnik") == null) {
                request.getSession().setAttribute("aktivniKorisnik", kupac);
            }
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("prijava.jsp");
        }

    }
}

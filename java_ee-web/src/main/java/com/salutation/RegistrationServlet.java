package com.salutation;

import com.salutation.model.Attendee;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by khayapro on 2016/06/21
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {


    @EJB
    private RegistrationManager registrationManager;
    @EJB
    private ApplicationStatistics statistics;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConcurrencyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            final Attendee attendee = registrationManager.register( "Khaya ", "  Senior Java Developer ",
                    "Aeon Source Software PTY. LTD.  ");
            out.println("<h3> Number of attendees: " + statistics.getCount() + " has been registered successfully.</h3>");
            out.println("<h3> Total reg time: " + statistics.getTotalTime() + "</h3>");

            //bulk registration with using Transaction interceptor
           /* String [] names = {"Sam", "John", "Jones", "Khaya", "Sibu", "Ralf"};
            String [] titles = {"Analyst", "Architect", "BA", "Developer", "Tester", "Manager"};
            registrationManager.bulkRegister(names, titles, "Aeon Source Software PTY. LTD.  ");*/
            out.println("<h3>" + attendee.getName() + " has been registered successfully.</h3>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

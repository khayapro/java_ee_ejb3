package com.salutation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by khayapro on 2016/07/18
 */
@WebServlet(name = "ReportServlet", urlPatterns = "/ReportServlet")
public class ReportServlet extends HttpServlet {

    @EJB
    private ReportsManagerBean reportsManagerBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter pw = response.getWriter();
        try {
            pw.println("<!DOCTYPE>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Memory Report</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h3>"+ reportsManagerBean.getMemoryReport() +"</h3>");
            pw.println("</body>");
            pw.println("</html>");
        } finally {
            pw.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

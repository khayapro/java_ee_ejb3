package com.salutation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by khayapro on 2016/06/04
 */
@WebServlet(name = "SecurityServlet", urlPatterns = {"/SecurityServlet"})
public class SecurityServlet extends HttpServlet {

    @EJB
    private VoucherManager voucherManager;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            voucherManager.createVoucher("Sams Billing", "Cape Town", BigDecimal.valueOf(5045.23d));
            voucherManager.submit();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Security Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Security servlet: voucher submitted.</h3>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

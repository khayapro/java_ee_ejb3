/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 *
 * @author khayapro
 */
@WebServlet(name = "SalutationServlet", urlPatterns = {"/SalutationServlet"})
public class SalutationServlet extends HttpServlet {
    
      private static final Logger LOG = Logger.getLogger("SalutationServletLog");
    
    @EJB
    private Salutation salutation;

    //MDB declarations
    @Resource(mappedName = "jms/SalutationQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/SalutationQueue")
    private Queue queue;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //Getting ejb via JNDI lookup instead of DI
//        try{
//            Context context = new InitialContext();
//            salutation = (Salutation) context.lookup("java:global/salutation-web-1/Salutation");
//        } catch(final Exception e){
//            e.printStackTrace();
//        }
        
        final String name = (String)request.getParameter("name");
        
        
        try {
            final String message = "Saluation generated for : name - " + name;
            //1. Create a connection to server.
            final Connection connection = queueConnectionFactory.createConnection();
            //2. Create a session for this message.
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //3. Create a message producer with destination.
            final MessageProducer messageProducer = session.createProducer(queue);
            //4. Create a textMessage to bind a message to.
            final TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            
            messageProducer.send(textMessage);
            LOG.warning("Message sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }



        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SalutationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + salutation.getInformalSalutation(name) + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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

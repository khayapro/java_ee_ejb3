/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khayapro
 */
@WebServlet(name = "TextMessageServlet", urlPatterns = {"/TextMessageServlet"})
public class TextMessageServlet extends HttpServlet {
    
    @Resource(name = "jms/TextMessageConnnectionFactory")
    private QueueConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/TextMessageQueue")
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
        
        
        try {
            //1. create connection
             Connection connection = connectionFactory.createConnection();
             //2. create session
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             //3. create message producer for queue
             MessageProducer messageProducer =  (MessageProducer) session.createProducer(queue);
             //4. create text message
             TextMessage textMessage = session.createTextMessage();
             
             //Order No. 1234, weight : 123f, quantity: 10
             final String order = "1234 12.5 10";
             textMessage.setText(order);
             messageProducer.send(textMessage);
           
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TextMessageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> message: [" + order  +"] sent successfully</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch(JMSException e){
            
        } 
        finally {
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

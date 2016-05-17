package com.salutation;

import com.salutation.model.Order;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by khayapro on 2016/05/17
 */
@WebServlet(name ="OrderMessageServlet", urlPatterns = {"/OrderMessageServlet"})
public class OrderMessageServlet extends HttpServlet {

    @Resource(mappedName = "jms/OrderQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/OrderQueue")
    private Queue queue;


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            //1. creating queue connection
            final QueueConnection connection = queueConnectionFactory.createQueueConnection();
            //2. creating session
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //3. creating object message
            final ObjectMessage objectMessage = session.createObjectMessage();
            //4. creating message producer
            final MessageProducer producer = session.createProducer(queue);
            //5. sending a message
            objectMessage.setObject(new Order(1234, 50f, 50));
            producer.send(objectMessage);

            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Order Message</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Order object message sent...</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
        processRequest(httpServletRequest, httpServletResponse);
    }
}

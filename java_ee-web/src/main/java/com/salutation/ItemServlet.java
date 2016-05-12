package com.salutation;

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
 * Created by khayapro on 2016/05/12
 */
@WebServlet(name = "ItemServlet", urlPatterns = {"/ItemServlet"})
public class ItemServlet extends HttpServlet {

    @Resource(mappedName = "jms/ItemsQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/ItemsQueue")
    private Queue queue;

    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        final Connection connection;
        try {

            try {
                //creating connection
                connection = queueConnectionFactory.createQueueConnection();
                //creating session
                final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                //creating message producer
                final MessageProducer producer = session.createProducer(queue);
                //creating stream message
                final StreamMessage message = session.createStreamMessage();

                message.writeInt(1234); //order No.
                message.writeDouble(56.5d); //priced
                message.writeInt(5); //quantity

                producer.send(message);

            } catch (JMSException e) {
                e.printStackTrace();
            }

            final PrintWriter pw = response.getWriter();
            pw.println("<!DOCTYPE>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Item Servlet</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h3>StreamMessage sent successfully.</h3>");
            pw.println("</body>");
            pw.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

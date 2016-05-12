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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by khayapro on 2016/05/12
 */
@WebServlet(name = "MapMessageServlet", urlPatterns = {"/MapMessageServlet"})
public class MapMessageServlet extends HttpServlet {


    private static final Logger LOG = Logger.getLogger(MapMessageServlet.class.getName());
    @Resource(mappedName = "jms/MapMessageFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/MapMessageQueue")
    private Queue queue;

    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        final Connection connection;
        try {

            try {
                //1. creating connection
                connection = queueConnectionFactory.createQueueConnection();
                //2. creating session
                final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                //3. creating message producer
                final MessageProducer producer = session.createProducer(queue);
                //3. creating stream message
                final MapMessage message = session.createMapMessage();

                message.setInt("PartNumber", 1234);
                message.setDouble("Weight", 56.5d);
                message.setInt("Quantity", 5);

                producer.send(message);

            } catch (JMSException e) {
                LOG.log(Level.SEVERE, null, e);
            }

            final PrintWriter pw = response.getWriter();
            pw.println("<!DOCTYPE>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Item Servlet</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h3>MapMessage sent successfully.</h3>");
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

package com.salutation;

import com.salutation.model.Availability;

import javax.annotation.Resource;
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
 * Created by khayapro on 2016/05/18
 */
@WebServlet(name = "AvailabilityServlet", urlPatterns = {"/AvailabilityServlet"})
public class AvailabilityServlet extends HttpServlet {

    private static final Logger Log = Logger.getLogger(AvailabilityServlet.class.getName());

    @Resource(mappedName = "jms/AvailabilityTopicFactoryPool")
    private TopicConnectionFactory topicConnectionFactory;
    @Resource(mappedName = "jms/AvailabilityTopic")
    private Topic topic;

    private Availability availability;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try {


            try {
                //1. create connection
                final Connection connection = topicConnectionFactory.createConnection();
                //2. create session
                final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                //3. create producer
                final MessageProducer producer = session.createProducer(topic);
                //4. create message
                final ObjectMessage objectMessage = session.createObjectMessage(availability);
                objectMessage.setStringProperty("test", "tested...");

                // sending message
                producer.send(objectMessage);
                System.out.println("---> availability status sent");

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
            pw.println("<h3>MapMessage sent successfully.</h3>");
            pw.println("</body>");
            pw.println("</html>");
        } catch (IOException e) {
            Log.info("ERROR: " + e.getMessage());
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

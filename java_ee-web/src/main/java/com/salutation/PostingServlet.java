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
import java.util.Enumeration;

/**
 * Created by khayapro on 2016/05/30
 */
@WebServlet(name = "PostingServlet", urlPatterns = {"/PostingServlet"})
public class PostingServlet extends HttpServlet {

    @Resource(mappedName = "jms/PostingQueueConnectionFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/PostingQueue")
    private Queue postingQueue;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        try {
            final Connection connection = queueConnectionFactory.createConnection();
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final MessageProducer producer = session.createProducer(postingQueue);
            final TextMessage textMessage = session.createTextMessage();
            textMessage.setText("For your eyes only");
            textMessage.setStringProperty("PostingType", "private");
            producer.send(textMessage);
            System.out.println("... sent a private message successfully.");

            textMessage.setText("For public eyes");
            textMessage.setStringProperty("PostingType", "public");
            producer.send(textMessage);
            System.out.println("... sent a public message successfully.");

            //Browsing message in a postingQueue using the QueueBrowser
            textMessage.setText("This is a protected message - in house");
            textMessage.setStringProperty("PostingType", "protected");
            producer.send(textMessage);
            System.out.println("... sent a public message successfully.");

            final QueueBrowser queueBrowser = session.createBrowser(postingQueue);
            final Enumeration enumeration = queueBrowser.getEnumeration();

            while(enumeration.hasMoreElements()){
                final TextMessage message = (TextMessage) enumeration.nextElement();
                System.out.println("queueBrowser: " + message.getText());
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        processRequest(request, response);
    }
}

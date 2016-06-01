package com.salutation;

import com.salutation.model.City;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by khayapro on 2016/05/31
 */
@WebServlet(name = "PopulationServlet", urlPatterns = {"/PopulationServlet"})
public class PopulationServlet extends HttpServlet {


    @EJB
    private PopulationManager populationManager;
    @EJB
    private CityFacadeBean cityFacadeBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            clearTables();

            //demonstrating transaction by adding city and then update
            populationManager.addCity("Johannesburg", "South Africa", 1000000L);
            populationManager.updatePopulation("Johannesburg", 2000000L);

            final List<City> cities = cityFacadeBean.findAll();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PopulationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            for(City city : cities)
                out.println("<p>" + city + "</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void clearTables(){
        final List<City> cities = cityFacadeBean.findAll();
        for (City city : cities){
            cityFacadeBean.remove(city);
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

package control;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import model.Bottiglia;
import model.BottigliaDAO;
import model.DriverManagerConnectionPool;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

@WebServlet(urlPatterns = "/myInit", name = "myInit", loadOnStartup = 0)

public class InitServlet extends HttpServlet {

    public void init() throws ServletException {
    	DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
				.getAttribute("DriverManager");
		BottigliaDAO model = new BottigliaDAO(dm);
		Collection<Bottiglia> bott = new LinkedList();
		Collection<Bottiglia> bott1 = new LinkedList();
        try {
		bott = model.doRetrieveByRegione("Campania");
		bott1 = model.doRetrieveByMaxVote();
        /*for(Bottiglia b:bott) {
        	b.setPathImages(model.doRetrieveImagesById(b.getId()));
        }*/
        } 
        catch(SQLException e) {
        	e.printStackTrace();
        }
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("listaViniCampani", bott);
        servletContext.setAttribute("listaViniPreferiti", bott1);
        super.init();
    }
}

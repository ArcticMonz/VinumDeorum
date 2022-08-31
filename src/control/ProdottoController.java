package control;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bottiglia;
import model.BottigliaDAO;
import model.DriverManagerConnectionPool;


@WebServlet(urlPatterns = "/ProdottoDetail", name = "ProdottoController")

public class ProdottoController extends HttpServlet {
	   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        int id= Integer.parseInt(request.getParameter("id"));
	        DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
					.getAttribute("DriverManager");
			BottigliaDAO model = new BottigliaDAO(dm);
			Bottiglia bean = new Bottiglia();
			Collection<Bottiglia> bott = new LinkedList();
			
			try {
				bean = model.doRetrieveByKey(id);
				bott = model.doRetrieveByCantina(bean.getCantina());			}
			catch(SQLException e) {
	        	e.printStackTrace();
	        }
			request.setAttribute("bottiglia", bean);
			request.setAttribute("bottiglieCorrelate", bott);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/productPage.jsp");
	        dispatcher.forward(request, response);
	    }

	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
}

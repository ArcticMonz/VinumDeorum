package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Bottiglia;
import model.BottigliaDAO;
import model.DriverManagerConnectionPool;

public class HomeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
				.getAttribute("DriverManager");
		BottigliaDAO model = new BottigliaDAO(dm);	
		
		Collection<Bottiglia> listaViniRilevanti = new LinkedList<Bottiglia>();
		Collection<Bottiglia> listaViniCampani = new LinkedList<Bottiglia>();
		
		//BottigliaDAO bottiglia = new BottigliaDAO(driver);
        try {
		listaViniRilevanti = model.doRetrieveByMaxVote();
        listaViniCampani = model.doRetrieveByRegione("Campania");
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }

        request.setAttribute("listaViniRilevanti", listaViniRilevanti);
        request.setAttribute("listaViniCampani", listaViniCampani);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/home.jsp");
        dispatcher.forward(request, response);
	}
}

package control;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.Utente;
import model.UtenteDAO;
import model.DriverManagerConnectionPool;

public class LoginServlet extends HttpServlet {

	 Utente user;

	    public LoginServlet(){
	        super();
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String action = req.getParameter("action");
	        if(action.equals("logout")){
	            req.getSession().removeAttribute("user");
	            resp.sendRedirect(req.getContextPath() + "/index.jsp");
	        }
	        else {
	            //LOGIN
	            String destination = req.getParameter("destination");
	            String username = req.getParameter("username");
	            String password = req.getParameter("password");
	            if(destination==null){
	                destination = "/index.jsp";
	            }
	            if(username==null||password==null){
	                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
	                return;
	            }
	            /*if (User.isAdmin(username, password)) {
	            	//ACCESSO TRAMITE ADMIN
	            	req.getSession().setAttribute("user", new AdminUserBean());
	            	resp.sendRedirect(req.getContextPath() + destination);
	            	return;
	            }*/
	            else if (checkLogin(username, password)) {
	                //SUCCESSO
	                req.getSession().setAttribute("user", user);
	                resp.sendRedirect(resp.encodeURL(req.getContextPath() + destination));
	                return;
	            } else {
	                //ERRORE
	                req.setAttribute("error", true);
	                req.setAttribute("destination", destination);
	                req.getRequestDispatcher("/login.jsp").forward(req, resp);
	                return;
	            }
	        }
	    }

	    /**
	     *
	     * @param username
	     * @param password
	     * @return true if an user with user username and pass password is found in the database, false otherwise
	     */
	    private boolean checkLogin(String username, String password){
	    	
	    	DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
					.getAttribute("DriverManager");
	        UtenteDAO model = new UtenteDAO(dm);
	        
	        try {
	            user = model.doRetrieveByKey(username);
	            if(user.getPassword().equals(password)){
	                return true;
	            }
	        } catch (SQLException throwables) {
	            return false;
	        }
	        

	        //ERRORE PASSWORD
	        return false;

	    }
}

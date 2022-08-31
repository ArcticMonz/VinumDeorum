package control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import model.Utente;
import model.UtenteDAO;
import model.DriverManagerConnectionPool;

public class SignupServlet extends HttpServlet {

	public SignupServlet(){
        super();
    }

    private static final String insertError = "Errore sui parametri";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String dataNascita = req.getParameter("data");
        
        Boolean checkName = nome.matches("[A-Za-z]+$");
        Boolean checkSurname = cognome.matches("[A-Za-z]+$");
        Boolean	checkUsername = username.matches("(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
        Boolean checkPassword = password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");

        //Controllo null e RegEx
        /*if( typeUser != null || nome != null || cognome != null || username != null || password != null ){
        	if ( ! ( checkName && checkSurname  && checkUsername  && checkPassword  ) ) {
        		req.setAttribute("errorSignup", insertError);
        		req.getRequestDispatcher(req.getContextPath() + "/signup.jsp").forward(req, resp);
        		return;
        	}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errori nei parametri della richiesta!");
	    	return;
		}
        /********************************************************************************************/
        
        /*UserModel um = new UserModel();
        
        //Controllo se ci sono problemi di username
        Collection<User> col = null;
		try {
			col = um.doRetriveAll("username");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<User> it = col.iterator();
		while ( it.hasNext() ) {
			User app = it.next();
			if (app.getUsername().equalsIgnoreCase(username) ){ 
				req.setAttribute("errorSignup", "Username gia inserito!");
				req.getRequestDispatcher(req.getContextPath() + "/signup.jsp").forward(req, resp);
				return;
			}	
		}
		/********************************************************************************************/
       
     
       
        	/*User user = new User();
        	user.setNome(nome);
        	user.setCognome(cognome);
        	user.setUsername(username);
        	user.setPassword(password);

        	try {
        		um.doSave(user);
        	} catch (SQLException throwables) {
        		throwables.printStackTrace();
        	}

        	resp.sendRedirect(req.getContextPath() + "/login.jsp");*/
        
    }
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class RecensioneDAO {
	
	private static final String TABLE_NAME = "recensione";
	private DriverManagerConnectionPool dmcp = null;	

	public RecensioneDAO(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Product Model creation....");
	}
	
	
	
	public synchronized void doSave(Recensione recensione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecensioneDAO.TABLE_NAME
				+ " (codice, voto, descrizione, idBottiglia, emailUtente) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, recensione.getCodice());
			preparedStatement.setInt(2, recensione.getVoto());
			preparedStatement.setString(3, recensione.getDescrizione());
			preparedStatement.setInt(4, recensione.getIdBottiglia());
			preparedStatement.setString(5, recensione.getEmailUtente());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
	}

	
	public synchronized Recensione doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Recensione recensione = new Recensione();

		String selectSQL = "SELECT * FROM " + RecensioneDAO.TABLE_NAME + " WHERE codice = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				recensione.setCodice(rs.getInt("codice"));
				recensione.setVoto(rs.getInt("voto"));
				recensione.setDescrizione(rs.getString("descrizione"));
				recensione.setIdBottiglia(rs.getInt("idBottiglia"));
				recensione.setEmailUtente(rs.getString("emailUtente"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return recensione;
	}

	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneDAO.TABLE_NAME + " WHERE codice = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	
	public synchronized Collection<Recensione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection <Recensione> recensioni = new LinkedList<Recensione>();

		String selectSQL = "SELECT * FROM " + RecensioneDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Recensione recensione = new Recensione();

				recensione.setCodice(rs.getInt("codice"));
				recensione.setVoto(rs.getInt("voto"));
				recensione.setDescrizione(rs.getString("descrizione"));
				recensione.setIdBottiglia(rs.getInt("idBottiglia"));
				recensione.setEmailUtente(rs.getString("emailUtente"));
				recensioni.add(recensione);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return recensioni;
	}
}

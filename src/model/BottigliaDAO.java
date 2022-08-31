package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BottigliaDAO {
	private static final String TABLE_NAME = "Bottiglia";
	private DriverManagerConnectionPool dmcp = null;	

	public BottigliaDAO(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Product Model creation....");
	}
	
	
	
	public synchronized void doSave(Bottiglia bottiglia) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + BottigliaDAO.TABLE_NAME
				+ " (nome, regione, disponibilità, prezzo, iva, anno, tipologia, descrizione, cantina, pathImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bottiglia.getNome());
			preparedStatement.setString(2, bottiglia.getRegione());;
			preparedStatement.setInt(3, bottiglia.getDisp());
			preparedStatement.setDouble(4, bottiglia.getPrezzo());
			preparedStatement.setInt(5, bottiglia.getIva());
			preparedStatement.setInt(6, bottiglia.getAnno());
			preparedStatement.setString(7, bottiglia.getTipologia());
			preparedStatement.setString(8, bottiglia.getDescrizione());
			preparedStatement.setString(9, bottiglia.getCantina());
			preparedStatement.setString(10, bottiglia.getPathImage());

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

	
	public synchronized Bottiglia doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Bottiglia bottiglia = new Bottiglia();

		String selectSQL = "SELECT * FROM " + BottigliaDAO.TABLE_NAME + " WHERE id = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bottiglia.setId(rs.getInt("id"));
				bottiglia.setNome(rs.getString("nome"));
				bottiglia.setRegione(rs.getString("regione"));
				bottiglia.setDisp(rs.getInt("disponibilità"));
				bottiglia.setPrezzo(rs.getDouble("prezzo"));
				bottiglia.setIva(rs.getInt("iva"));
				bottiglia.setAnno(rs.getInt("anno"));
				bottiglia.setTipologia(rs.getString("tipologia"));
				bottiglia.setDescrizione(rs.getString("descrizione"));
				bottiglia.setCantina(rs.getString("cantina"));
				bottiglia.setPathImage(rs.getString("pathImage"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bottiglia;
	}

	
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + BottigliaDAO.TABLE_NAME + " WHERE id = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

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

	
	public synchronized Collection<Bottiglia> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Bottiglia> bottiglie = new LinkedList<Bottiglia>();

		String selectSQL = "SELECT * FROM " + BottigliaDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Bottiglia bottiglia = new Bottiglia();

				bottiglia.setId(rs.getInt("id"));
				bottiglia.setNome(rs.getString("nome"));
				bottiglia.setRegione(rs.getString("regione"));
				bottiglia.setDisp(rs.getInt("disponibilità"));
				bottiglia.setPrezzo(rs.getDouble("prezzo"));
				bottiglia.setIva(rs.getInt("iva"));
				bottiglia.setAnno(rs.getInt("anno"));
				bottiglia.setTipologia(rs.getString("tipologia"));
				bottiglia.setDescrizione(rs.getString("descrizione"));
				bottiglia.setCantina(rs.getString("cantina"));
				bottiglia.setPathImage(rs.getString("pathImage"));
				
				bottiglie.add(bottiglia);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bottiglie;
	}
	
	public synchronized Collection<Bottiglia> doRetrieveByMaxVote() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Bottiglia bottiglia = new Bottiglia();
		Collection<Bottiglia> bottiglie = new LinkedList<Bottiglia>();

		String selectSQL = "SELECT * FROM " + BottigliaDAO.TABLE_NAME + ", recensione WHERE recensione.idBottiglia=Bottiglia.id AND voto >= 4";
		
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bottiglia.setId(rs.getInt("id"));
				bottiglia.setNome(rs.getString("nome"));
				bottiglia.setRegione(rs.getString("regione"));
				bottiglia.setDisp(rs.getInt("disponibilità"));
				bottiglia.setPrezzo(rs.getDouble("prezzo"));
				bottiglia.setIva(rs.getInt("iva"));
				bottiglia.setAnno(rs.getInt("anno"));
				bottiglia.setTipologia(rs.getString("tipologia"));
				bottiglia.setDescrizione(rs.getString("descrizione"));
				bottiglia.setCantina(rs.getString("cantina"));
				bottiglia.setPathImage(rs.getString("pathImage"));
				bottiglie.add(bottiglia);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bottiglie;
	}
	
	public synchronized Collection<Bottiglia> doRetrieveByRegione(String regione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		Collection<Bottiglia> bottiglie = new LinkedList<Bottiglia>();

		String selectSQL = "SELECT * FROM " + BottigliaDAO.TABLE_NAME + " WHERE regione = ?";
		
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, regione);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Bottiglia bottiglia = new Bottiglia();
				bottiglia.setId(rs.getInt("id"));
				bottiglia.setNome(rs.getString("nome"));
				bottiglia.setRegione(rs.getString("regione"));
				bottiglia.setDisp(rs.getInt("disponibilità"));
				bottiglia.setPrezzo(rs.getDouble("prezzo"));
				bottiglia.setIva(rs.getInt("iva"));
				bottiglia.setAnno(rs.getInt("anno"));
				bottiglia.setTipologia(rs.getString("tipologia"));
				bottiglia.setDescrizione(rs.getString("descrizione"));
				bottiglia.setCantina(rs.getString("cantina"));
				bottiglia.setPathImage(rs.getString("pathImage"));
				bottiglie.add(bottiglia);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bottiglie;
	}
	
	public synchronized Collection<Bottiglia> doRetrieveByCantina(String cantina) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		Collection<Bottiglia> bottiglie = new LinkedList<Bottiglia>();

		String selectSQL = "SELECT * FROM " + BottigliaDAO.TABLE_NAME + " WHERE cantina = ?";
		
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cantina);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Bottiglia bottiglia = new Bottiglia();
				bottiglia.setId(rs.getInt("id"));
				bottiglia.setNome(rs.getString("nome"));
				bottiglia.setRegione(rs.getString("regione"));
				bottiglia.setDisp(rs.getInt("disponibilità"));
				bottiglia.setPrezzo(rs.getDouble("prezzo"));
				bottiglia.setIva(rs.getInt("iva"));
				bottiglia.setAnno(rs.getInt("anno"));
				bottiglia.setTipologia(rs.getString("tipologia"));
				bottiglia.setDescrizione(rs.getString("descrizione"));
				bottiglia.setCantina(rs.getString("cantina"));
				bottiglia.setPathImage(rs.getString("pathImage"));
				bottiglie.add(bottiglia);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bottiglie;
	}
	

}

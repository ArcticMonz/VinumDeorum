package model;


import java.sql.Date;

public class Utente {
		private String email;
	    private String password;
	    private String username;
	    private String nome;
	    private String cognome;
	    private Date dataDiNascita;
	    private String nazione;
	    private Boolean admin;

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	    	this.password=password;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getCognome() {
	        return cognome;
	    }

	    public void setCognome(String cognome) {
	        this.cognome = cognome;
	    }

	    public Date getDataDiNascita() {
	        return dataDiNascita;
	    }

	    public void setDataDiNascita(Date dataDiNascita) {
	        this.dataDiNascita = dataDiNascita;
	    }

	    public String getNazione() {
	        return nazione;
	    }

	    public void setNazione(String nazione) {
	        this.nazione = nazione;
	    }

	    public Boolean getAdmin() {
	        return admin;
	    }

	    public void setAdmin(Boolean admin) {
	        this.admin = admin;
	    }
}

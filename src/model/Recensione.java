package model;

public class Recensione {
	
	private int codice;
	private int voto;
	private String descrizione;
	private int idBottiglia;
	private String emailUtente;
	
	public int getCodice(){
		return codice;
	}
	
	public void setCodice(int codice){
		this.codice = codice;
	}
	
	public int getVoto() {
		return voto;
	}
	
	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public int getIdBottiglia() {
		return idBottiglia;
	}
	
	public void setIdBottiglia(int idBottiglia) {
		this.idBottiglia = idBottiglia;
	}
	
	public String getEmailUtente() {
		return emailUtente;
	}
	
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}
}

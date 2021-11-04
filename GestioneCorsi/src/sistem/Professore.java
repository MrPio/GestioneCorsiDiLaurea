package sistem;

import java.util.ArrayList;

public class Professore extends Persona {
	String recapito;
	String sito_web;
	String progetti_di_ricerca;

	// COSTRUTTORE (parziale) - solo nome e cognome
	Professore(String nome, String cognome) {
		super(nome, cognome);
	}

	// COSTRUTTOTR (totale) - con tutte le informazioni
	Professore(String nome, String cognome, String recapito, String sito_web,
			String progetti_di_ricerca) {
		super(nome, cognome);
		this.recapito = recapito;
		this.sito_web = sito_web;
		this.progetti_di_ricerca = progetti_di_ricerca;
	}

	public void assegnaVoto(Corso corso, Studente studente, int voto) {
		if (!getListaCorsi(studente.getMy_corso()).contains(corso)) {
			System.err.println("CORSO NON DI COMPENZA DEL PROFESSORE!");
			return;
		}
		for (Esame esame : studente.getListaEsami())
			if (esame.getCorso().equals(corso)) {
				esame.setVoto(voto);
				System.err.println("VOTO INSERITO CON SUCCESSO!");
			}
	}
	public ArrayList<Corso> getListaCorsi(CorsoLaurea corso_laurea) {
		ArrayList<Corso> lista = new ArrayList<Corso>();
		for (Corso corso : corso_laurea.getLista_corsi())
			if (corso.getProfessore().equals(this))
				lista.add(corso);
		return lista;
	}
	public String toString() {
		return "\nNome=" + getNome() + "\nCognome=" + getCognome()
				+ "\nRecapito=" + recapito + "\nSito_web=" + sito_web
				+ "\nProgetti_di_ricerca=" + progetti_di_ricerca;

	}
}
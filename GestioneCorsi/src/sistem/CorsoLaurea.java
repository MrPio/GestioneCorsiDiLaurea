package sistem;

import java.util.ArrayList;

public class CorsoLaurea {
	private final int MIN_CREDITI = 9;
	private String nome;
	private Corso[] lista_corsi;
	private ArrayList<Studente> lista_studenti = new ArrayList<Studente>();
	private ArrayList<Studente> lista_studenti_laureati = new ArrayList<Studente>();

	// COSTRUTTORE
	CorsoLaurea(String nome, Corso[] lista_corsi) {
		this.nome = nome;
		this.lista_corsi = lista_corsi;
	}

	// GETTER
	public String getNome() {
		return nome;
	}
	public Corso[] getLista_corsi() {
		return lista_corsi;
	}
	ArrayList<Studente> getLista_studenti() {
		return lista_studenti;
	}
	ArrayList<Studente> getLista_studenti_laureati() {
		return lista_studenti_laureati;
	}

	// METODI (pubblici)
	public CorsoLaurea iscriviStudente(Studente studente) {
		// preparo il piano di studio del nuovo studente
		for (Corso corso : lista_corsi)
			studente.getListaEsami().add(new Esame(corso));
		// iscrivo lo studente
		studente.setMy_corso(this);
		lista_studenti.add(studente);
		return this;
	}
	public Studente cercaStudente(String matricola) {
		for (Studente stud : lista_studenti)
			if (stud.getMatricola().equals(matricola))
				return stud;
		return null;
	}
	public boolean cercaStudente(Studente studente) {
		for (Studente stud : lista_studenti)
			if (stud.equals(studente))
				return true;
		return false;
	}
	public boolean ritiraStudente(Studente studente) {
		for (Studente stud : lista_studenti)
			if (stud.equals(studente))
				return lista_studenti.remove(stud);
		return false;
	}
	public void laureaStudente(Studente studente) {
		if (studente.calcolaCfuTotali() >= MIN_CREDITI) {
			lista_studenti_laureati.add(studente);
			if (ritiraStudente(studente))
				System.err.println("STUDENTE LAUREATO CON SUCCESSO");
			else
				System.err.println("STUDENTE NON TROVATO");
		}
		System.err
				.println("LO STUDENTE NON SODDISFA I REQUISITI PER LAUREARSI!");
	}
	public boolean cercaCorso(Corso corso) {
		for (Corso c : lista_corsi)
			if (c.getCodice().equals(corso.getCodice()))
				return true;
		return false;
	}
	public String toString() {
		String output = getNome().toUpperCase() + System.lineSeparator()
				+ "\t•STUDENTI FREQUENTANTI:" + System.lineSeparator();
		for (Studente studente : lista_studenti)
			output += "\t\t" + studente + System.lineSeparator();
		output += "\t•STUDENTI LAUREATI:" + System.lineSeparator();
		for (Studente studente : lista_studenti_laureati)
			output += "\t\t" + studente + System.lineSeparator();
		return output;
	}
}
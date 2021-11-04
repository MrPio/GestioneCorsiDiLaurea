package sistem;

import java.util.ArrayList;

import sistem.Corso.TipoCorso;

public class Studente extends Persona {
	private static int numero_studente = 0;
	private String matricola;
	private CorsoLaurea my_corso;
	private ArrayList<Esame> lista_esami = new ArrayList<Esame>();

	// COSTRUTTORE
	Studente(String nome, String cognome) {
		super(nome, cognome);
		assegnaMatricola();
	}

	// GETTER e SETTER
	public String getMatricola() {
		return matricola;
	}
	public ArrayList<Esame> getListaEsami() {
		return lista_esami;
	}
	CorsoLaurea getMy_corso() {
		return my_corso;
	}

	void setMy_corso(CorsoLaurea my_corso) {
		this.my_corso = my_corso;
	}

	// METODI(privati)
	private void assegnaMatricola() {
		this.matricola = "S" + String.format("%07d", ++numero_studente);
	}
	private String sommarioPianoDiStudio() {
		String piano_di_studio = "PIANO DI STUDI:" + System.lineSeparator();
		int count = 0;
		for (Esame esame : lista_esami) {
			piano_di_studio += ++count + "• [" + esame.getCorso().getCodice()
					+ "] " + esame.getCorso().getNome() + "    Voto = "
					+ esame.getVotoFinale() + System.lineSeparator();
		}
		return piano_di_studio;
	}

	// METODI (pubblici)
	public void aggiungiEsame(Esame esame) {
		if (!my_corso.cercaCorso(esame.getCorso())) {
			System.err.println(
					"Corso non appartenente al corso di laurea dello studente!"
							.toUpperCase());
			return;
		}
		for (Esame es : lista_esami) {
			if (es.getCorso().getCodice()
					.equals(esame.getCorso().getCodice())) {
				System.err.println(
						"Questo esame è già presente nel piano di studio!"
								.toUpperCase());
				return;
			}
		}
		System.err.println("Esame aggiunto con successo".toUpperCase());
		lista_esami.add(esame);
	}
	public void rimuoviEsame(Esame esame) {
		if (esame.getCorso().getTipo_corso() == TipoCorso.obbligatorio) {
			System.err.println(
					"Non puoi rimuovere un esame obbligatorio!".toUpperCase());
			return;
		}
		for (Esame es : lista_esami) {
			if (es.getCorso().getCodice()
					.equals(esame.getCorso().getCodice())) {
				System.err.println("Esame rimosso con successo".toUpperCase());
				lista_esami.remove(es);
				return;
			}
		}
		System.err.println(
				"Esame non presente nel piano di studio".toUpperCase());
	}
	public boolean cercaEsame(Esame esame) {
		for (Esame es : lista_esami)
			if (es.getCorso() == esame.getCorso())
				return true;
		return false;
	}
	public int calcolaCfuTotali() {
		int cfu_tot = 0;
		for (Esame esame : lista_esami)
			if (esame.isAccettato())
				cfu_tot += esame.getCorso().getCfu();
		return cfu_tot;
	}
	public float calcolaMediaVoti() {
		int somma = 0;
		int conteggio = 0;
		for (Esame esame : lista_esami) {
			if (esame.getVotoFinale() != 0 && esame.isAccettato()) {
				somma += esame.getVotoFinale();
				++conteggio;
			}
		}
		return (float) somma / conteggio;
	}
	public void accettaEsitoEsame(Esame esame, boolean accetto) {
		for (Esame es : lista_esami) {
			if (es.getCorso().equals(esame.getCorso()) && es.isSuperato()) {
				es.setAccettato(accetto);
				System.err
						.println("Voto accettato con successo!".toUpperCase());
				return;
			}
		}
		System.err.println(
				"Esame non superato oppure non presente nel piano di studio!"
						.toUpperCase());
	}
	public String toString() {
		return "[" + getMatricola() + "] (" + calcolaCfuTotali()
				+ " CFU) - Cognome: " + getCognome() + "    Nome: " + getNome()
				+ System.lineSeparator() + "Media Voti = " + calcolaMediaVoti()
				+ System.lineSeparator() + sommarioPianoDiStudio();
	}
}
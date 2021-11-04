package sistem;

import java.util.Date;

public class Esame {
	private Corso corso;
	private int voto;
	private Date data;
	private boolean accettato;

	// COSTRUTTORE (parziale) - per esame non ancora sostenuto
	Esame(Corso corso) {
		this.corso = corso;
	}
	// COSTRUTTORE (totale) - per esame sostenuto a prescindere dall'esito
	Esame(Corso corso, int voto, Date data, boolean accettato) {
		this.corso = corso;
		this.voto = voto;
		this.data = data;
		this.accettato = accettato;
	}

	// GETTER
	public Corso getCorso() {
		return corso;
	}
	public int getVotoFinale() {
		return voto;
	}
	public Date getDataEsame() {
		return data;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}
	public void setAccettato(boolean accettato) {
		this.accettato = accettato;
	}
	public boolean isAccettato() {
		return accettato;
	}
	public boolean isSuperato() {
		return voto >= 18;
	}
}

package sistem;

public class Corso {
	enum TipoCorso {
		obbligatorio, a_scelta, facoltativo
	};
	enum Lingua {
		IT, EN, SP, FR, TD
	};
	enum Lezioni {
		frontali, in_laboratorio, miste
	};

	private String nome;
	private String codice;
	private Professore professore;
	private int cfu;
	private TipoCorso tipo_corso;
	private Lingua lingua_corso;
	private Lezioni modalità_lezioni;
	private String prerequisiti;
	private String risultati_attesi;
	private String programma;
	private String[] testi_consigliati;

	// COSTRUTTORE (parziale) - solo informazioni essenziali
	Corso(String codice, String nome, int cfu, Professore professore) {
		this.codice = codice;
		this.nome = nome;
		this.cfu = cfu;
		this.professore = professore;
	}

	// COSTRUTTORE (totale) - tutte le informazioni
	Corso(String nome, int cfu, String codice, Professore professore,
			TipoCorso tipo_corso, Lingua lingua_corso, Lezioni modalità_lezioni,
			String prerequisiti, String risultati_attesi, String programma,
			String[] testi_consigliati) {
		this.nome = nome;
		this.cfu = cfu;
		this.codice = codice;
		this.professore = professore;
		this.tipo_corso = tipo_corso;
		this.lingua_corso = lingua_corso;
		this.modalità_lezioni = modalità_lezioni;
		this.prerequisiti = prerequisiti;
		this.risultati_attesi = risultati_attesi;
		this.programma = programma;
		this.testi_consigliati = testi_consigliati;
	}

	// GETTER e SETTER
	public String getNome() {
		return nome;
	}
	public String getCodice() {
		return codice;
	}
	public Professore getProfessore() {
		return professore;
	}
	public int getCfu() {
		return cfu;
	}
	public TipoCorso getTipo_corso() {
		return tipo_corso;
	}
	public void setTipo_corso(TipoCorso tipo_corso) {
		this.tipo_corso = tipo_corso;
	}
	public Lingua getLingua_corso() {
		return lingua_corso;
	}
	public void setLingua_corso(Lingua lingua_corso) {
		this.lingua_corso = lingua_corso;
	}
	public Lezioni getModalità_lezioni() {
		return modalità_lezioni;
	}
	public void setModalità_lezioni(Lezioni modalità_lezioni) {
		this.modalità_lezioni = modalità_lezioni;
	}
	public String getPrerequisiti() {
		return prerequisiti;
	}
	public void setPrerequisiti(String prerequisiti) {
		this.prerequisiti = prerequisiti;
	}
	public String getRisultati_attesi() {
		return risultati_attesi;
	}
	public void setRisultati_attesi(String risultati_attesi) {
		this.risultati_attesi = risultati_attesi;
	}
	public String getProgramma() {
		return programma;
	}
	public void setProgramma(String programma) {
		this.programma = programma;
	}
	public String[] getTesti_consigliati() {
		return testi_consigliati;
	}
	public void setTesti_consigliati(String[] testi_consigliati) {
		this.testi_consigliati = testi_consigliati;
	}
}

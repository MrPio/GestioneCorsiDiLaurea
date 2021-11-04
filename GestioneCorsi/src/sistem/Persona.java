package sistem;

abstract class Persona {
	private String nome;
	private String cognome;

	// COSTRUTTORE
	Persona(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	// METODI (pubblici)
	String getNome() {
		return nome;
	};
	String getCognome() {
		return cognome;
	};
}
package sistem;

import java.util.Scanner;

import sistem.Corso.TipoCorso;

public class Sistema {
	final static Professore[] lista_professori= {
			new Professore("Piero","Montecchiari"),
			new Professore("Renato","Colucci"),
			new Professore("Aldo Franco","Dragoni"),
			new Professore("Emanuele","Frontoni"),
			new Professore("Claudio","Turchetti"),
			new Professore("Luca ","Pierantoni"),
	};
	
	static Corso[] lista_corsi_ingegneria_informatica = {
			new Corso("AM1","Analisi Matematica 1", 9,lista_professori[0]),
			new Corso("AM2","Analisi Matematica 2", 6,lista_professori[1]),
			new Corso("FI","Fondamenti di informatica", 9,lista_professori[2]),
			new Corso("PO","Programmazione ad ogggetti", 9,lista_professori[3])
	};
	static Corso[] lista_corsi_ingegneria_elettronica = {
			new Corso("AM1","Analisi Matematica 1", 9,lista_professori[0]),
			new Corso("AM2","Analisi Matematica 2", 6,lista_professori[1]),
			new Corso("MNE","Micro e nano elettronica", 9,lista_professori[4]),
			new Corso("SMER","Sistemi multifisici per l'elettronica a radiofrequenza", 9,lista_professori[5])
	};
	
	static CorsoLaurea[] lista_corsi_laurea= {
			new CorsoLaurea("Ingegneria Informatica",lista_corsi_ingegneria_informatica),
			new CorsoLaurea("Ingegneria Elettronica",lista_corsi_ingegneria_elettronica)
	};
	
	static Scanner input=new Scanner(System.in);
	
	public static void main(String[] args) {
		for(Corso corso:lista_corsi_ingegneria_informatica)
			corso.setTipo_corso(TipoCorso.obbligatorio);
		lista_corsi_ingegneria_elettronica[3].setTipo_corso(TipoCorso.a_scelta);
		
		System.out.println("Benvenuto!");
		while(true) {
			System.out.println("Come desideri accedere?"+System.lineSeparator()+
					System.lineSeparator()+"(1)accedo come segreteria"+
					System.lineSeparator()+"(2)accedo come professore"+
					System.lineSeparator()+"(3)accedo come studente");	
			switch(input.nextInt()) {
				case 1:
					terminaleSegreteria();
					break;
				case 2:
					terminaleProfessore();
					break;
				case 3:
					terminaleStudente();
					break;
			}
			System.out.println();System.out.println();System.out.println();
		}
	}
	private static void terminaleSegreteria() {
		System.out.println("Lista corsi disponibili:"+System.lineSeparator());
		stampaSommarioCorsiLaurea();
		System.out.print(System.lineSeparator()+"Seleziona il corso di laurea da gestire:");
		CorsoLaurea corso_laurea_selezionato=lista_corsi_laurea[input.nextInt()-1];
		System.out.print("Hai selezionato il corso: ");
		while(true) {
			boolean esci=false;
			System.out.println(corso_laurea_selezionato);
			System.out.println("Cosa desideri fare?"+System.lineSeparator()+
					System.lineSeparator()+"(1)iscrevere uno studente"+
					System.lineSeparator()+"(2)cercare uno studente"+
					System.lineSeparator()+"(3)ritirare uno studente"+
					System.lineSeparator()+"(4)laureare uno studente");		
			
			switch(input.nextInt()) {
				case 1:
					input.nextLine();
					System.out.print("nome=");
					String nome=input.nextLine();
					System.out.print("cognome=");
					String cognome=input.nextLine();
					corso_laurea_selezionato.iscriviStudente(new Studente(nome, cognome));
					break;
				case 2:
					input.nextLine();
					System.out.print("matricola=");
					String matricola=input.nextLine();
					if(corso_laurea_selezionato.cercaStudente(matricola)!=null)
						System.err.println("HO TROVATO IL SEGUENTE STUDENTE:"+
								System.lineSeparator()+corso_laurea_selezionato.cercaStudente(matricola));
					else 
						System.err.println("STUDENTE NON ISCRITTO AL CORSO DI LAUREA");
					break;
				case 3:
					input.nextLine();
					System.out.print("matricola=");
					matricola=input.nextLine();
					if(corso_laurea_selezionato.cercaStudente(matricola)!=null) {
						corso_laurea_selezionato.ritiraStudente(corso_laurea_selezionato.cercaStudente(matricola));
						System.err.println("STUDENTE RIMOSSO CON SUCCESSO");
					}
					else 
						System.err.println("STUDENTE NON ISCRITTO AL CORSO DI LAUREA");
					break;
				case 4:
					input.nextLine();
					System.out.print("matricola=");
					matricola=input.nextLine();
					if(corso_laurea_selezionato.cercaStudente(matricola)!=null) 
						corso_laurea_selezionato.laureaStudente(corso_laurea_selezionato.cercaStudente(matricola));				
					else 
						System.err.println("STUDENTE NON ISCRITTO AL CORSO DI LAUREA");
					break;
				default:
					esci=true;
					break;
	
			}
			System.out.println();System.out.println();System.out.println();
			if(esci)
				break;
		}
	}
	private static void terminaleProfessore() {
		boolean esci=false;
		Professore professore_selezionato=loginProfessore();
		if(professore_selezionato==null) {
			System.err.println("Professore non trovato!".toUpperCase());
			return;
		}
		while(true) {
			System.out.println(professore_selezionato);
			System.out.println("Cosa desideri fare?"+System.lineSeparator()+
					System.lineSeparator()+"(1)assegna un voto ad un esame di uno studente");		
			
			switch(input.nextInt()) {
				case 1:
					input.nextLine();
					System.out.println("Voto da assegnare = ");
					int voto=input.nextInt();
					input.nextLine();
					professore_selezionato.assegnaVoto(
							scegliCorso(professore_selezionato), loginStudente(), voto);
					break;
				default:
					esci=true;
					break;
	
			}
			System.out.println();System.out.println();System.out.println();
			if(esci)
				break;
		}
	}
	private static void terminaleStudente() {
		boolean esci=false;
		input.nextLine();
		Studente studente_selezionato=loginStudente();
		if(studente_selezionato==null) {
			System.err.println("Studente non trovato!".toUpperCase());
			return;
		}
		while(true) {
			System.out.println(studente_selezionato);
			System.out.println("Cosa desideri fare?"+System.lineSeparator()+
					System.lineSeparator()+"(1)aggiungi un esame al piano di studio"+
					System.lineSeparator()+"(2)rimuovi un esame al piano di studio"+
					System.lineSeparator()+"(3)cerca un esame nel piano di studio"+
					System.lineSeparator()+"(4)accetta/rifiuta l'esito di un esame");		
			
			switch(input.nextInt()) {
				case 1:
					input.nextLine();
					studente_selezionato.aggiungiEsame(scegliEsame(studente_selezionato));
					break;
				case 2:
					input.nextLine();
					studente_selezionato.rimuoviEsame(scegliEsame(studente_selezionato));
					break;
				case 3:
					input.nextLine();
					if(studente_selezionato.cercaEsame(scegliEsame(studente_selezionato)))
						System.err.println("Corso presente nel piano studio".toUpperCase());
					else 
						System.err.println("Corso non presente nel piano studio!".toUpperCase());
					break;
				case 4:
					input.nextLine();
					Esame esame_selezionato=scegliEsame(studente_selezionato);
					System.out.print("[A]ccettare o [R]ifiutare = ");
					switch(input.next()) {
						case "A":
							studente_selezionato.accettaEsitoEsame(esame_selezionato,true);
							break;
						case "B":
							studente_selezionato.accettaEsitoEsame(esame_selezionato,false);
							break;
					}
					break;
				default:
					esci=true;
					break;
	
			}
			System.out.println();System.out.println();System.out.println();
			if(esci)
				break;
		}
	}
	
	private static Esame scegliEsame(Studente studente) {
		int conteggio=0;
		for(Corso corso:studente.getMy_corso().getLista_corsi())
			System.out.println("\t"+(++conteggio)+"-["+corso.getCodice()+"] "+corso.getNome()+System.lineSeparator());
		System.out.println();System.out.println("La tua scelta = ");
		int scelta=input.nextInt();
		input.nextLine();
		return new Esame(studente.getMy_corso().getLista_corsi()[scelta-1]);
	}
	private static Corso scegliCorso(Professore professore) {
		int conteggio=0;
		for(CorsoLaurea corso_laurea:lista_corsi_laurea) 
			System.out.println(++conteggio+"•"+corso_laurea.getNome().toUpperCase());
		System.out.println();System.out.println("La tua scelta = ");
		int scelta_corso_laurea=input.nextInt();
		input.nextLine();
		conteggio=0;
		for(Corso corso:professore.getListaCorsi(lista_corsi_laurea[scelta_corso_laurea-1]))
			System.out.println("\t"+(++conteggio)+"-["+corso.getCodice()+"] "+corso.getNome()+System.lineSeparator());

		System.out.println();System.out.println("La tua scelta = ");
		int scelta=input.nextInt();
		input.nextLine();
		return professore.getListaCorsi(lista_corsi_laurea[scelta_corso_laurea-1]).get(scelta-1);
	}
	private static Studente loginStudente() {
		System.out.print("matricola=");
		String matricola=input.nextLine();
		for(CorsoLaurea corso_laurea:lista_corsi_laurea) {
			for(Studente studente:corso_laurea.getLista_studenti())
				if(studente.getMatricola().equals(matricola))
					return studente;
			for(Studente studente:corso_laurea.getLista_studenti_laureati())
				if(studente.getMatricola().equals(matricola))
					return studente;
			
		}
		return null;
	}
	private static Professore loginProfessore() {
		System.out.print("cognome=");
		input.nextLine();
		String cognome=input.nextLine();
		for(Professore professore:lista_professori) 
			if(professore.getCognome().equals(cognome))
				return professore;
		return null;
	}
	private static void stampaSommarioCorsiLaurea() {
		String output = "";
		int count=0;
		for(CorsoLaurea corso_laurea:lista_corsi_laurea) {
			output+=++count+"•"+corso_laurea.getNome()+System.lineSeparator();
			for(Corso corso:corso_laurea.getLista_corsi())
				output+="-["+corso.getCodice()+"] "+corso.getNome()+System.lineSeparator();
			output+=System.lineSeparator();
		}
		System.out.println(output);
	}
}
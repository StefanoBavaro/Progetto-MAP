package com.company;

class LinkList {
	private Puntatore inizioLista = null;
	boolean isEmpty() {
			return inizioLista == null;
	}
	Puntatore firstList() {
			return null;
		}
		
	boolean endList(Puntatore p) {
			if (isEmpty()) {
				return true;
			}
			if (p == firstList()) {
				return inizioLista == null; // verifica che la lista sia vuota
			} else { //verifica che elemento successivo a quello in posizione p sia nullo
				return ((Puntatore) p).link.successivo == null;
				}
			}
			
	Object readList(Puntatore p) {
			if (isEmpty()) {
				throw new IndexOutOfBoundsException("Lista vuota");
			}
			if (p == firstList()) {
				return inizioLista.link.elemento;
			} else {
				return ((Puntatore) p).link.successivo.link.elemento;
			}
		}
		 void add(Object e) { //aggiunge in testa
			Puntatore temp;

			if (!isEmpty()) {
					temp = inizioLista;
					inizioLista = new Puntatore(new Cella(e));
					inizioLista.link.successivo = temp;
				} else {
				// se la lista � vuota
				inizioLista = new Puntatore(new Cella(e));
			}

		}
		
		 Puntatore succ(Puntatore p) {
			if (endList(p)) {
				throw new IndexOutOfBoundsException("Posizione fine lista non valida");
			}
			if (isEmpty()) {
				throw new IndexOutOfBoundsException("Lista vuota");
			}
			if (p == firstList()) {
				return inizioLista;
			} else if (p == inizioLista) {
				return inizioLista.link.successivo;
			} else {
				return ((Puntatore) p).link.successivo;
			}
		}
}

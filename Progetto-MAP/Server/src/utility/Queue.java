package utility;

/**
 * Classe generica che modella una struttura dati coda,
 * usata come contenitore a modalità FIFO per i pattern frequenti scoperti
 * a livello k da usare per generare i pattern candidati a livello k+1
 * @param <T> tipo parametrizzato della coda
 */
public class Queue<T> {
    /**
     * Cella iniziale della coda
     */
    private Record begin = null;
    /**
     * Cella finale della coda
     */
    private Record end = null;

    /**
     * Inner class che modella una cella della coda
     */
    private class Record {
        /**
         * Elemento di tipo T contenuto nella cella
         */
        T elem;

        /**
         * Riferimento alla cella successiva
         */
        Record next;

        /**
         * Costruttore di una cella
         * @param e elemento da inserire nella cella
         */
        Record(T e) {
            this.elem = e;
            this.next = null;
        }
    }

    /**
     * Controllato il contenuto della prima cella della coda: se null, allora nella coda non c'è nessuna cella
     * @return booleano che indica se la cella è vuota
     */
    public boolean isEmpty() {
        return this.begin == null;
    }

    /**
     * Aggiunge un elemento alla coda
     * @param e elemento da aggiungere alla coda
     */
    public void enqueue(T e) {
        if (this.isEmpty())
            this.begin = this.end = new Record(e);
        else {
            this.end.next = new Record(e);
            this.end = this.end.next;
        }
    }

    /**
     * Restituisce il primo elemento della coda; se questa è vuota, restituisce un'eccezione
     * @return primo elemento della coda
     * @throws EmptyQueueException
     */
    public T first() throws EmptyQueueException {
        if (this.isEmpty()) {
            throw new EmptyQueueException("La coda e' vuota.");
        }
        return this.begin.elem;
    }

    /**
     * Rimuove un elemento dalla coda; se questa è vuota, restituisce un'eccezione
     * @throws EmptyQueueException
     */
    public void dequeue() throws EmptyQueueException {
        if (this.begin == this.end) {
            if (this.isEmpty()) {
                throw new EmptyQueueException("La coda e' vuota.");
            } else
                this.begin = this.end = null;
        } else {
            begin = begin.next;
        }
    }

}
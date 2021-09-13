# DOCUMENTAZIONE
Progetto realizzato da:
- Jacopo D'Abramo (mat. 716484) <br>
 mail: j.dabramo@studenti.uniba.it
- Lorenzo Cassano (mat. 718331) <br>
mail: l.cassano25@studenti.uniba.it
- Stefano Bavaro (mat. 716034) <br>
mail: s.bavaro4@studenti.uniba.it
<br><br>
Il software presentato permette, a partire da un dataset memorizzato tramite il servizio
MySQL, la ricerca di _pattern frequenti_ e l'analisi di _pattern emergenti_, sulla base
di un valore minimo di _supporto_ e un valore di _grow rate_ forniti dall'utente. La tecnica
di _data mining_ che viene implementata sfrutta un [algoritmo apriori](https://it.wikipedia.org/wiki/Algoritmo_apriori)
fornito dal docente. <br>
Definiamo un **pattern frequente** come un sottoinsieme di _Item_, ove un _item_ è una coppia [attributo,valore], che occorrono con
una frequenza minima rappresentata dal _minimo supporto_, in un dato insieme. <br>
Un **pattern emergente** è definito come un insieme di item la cui frequenza cambia in maniera notevole da un dataset ad un altro (_grow rate_, tasso di crescita).

## Progetto base
La versione base del progetto consiste in un’architettura client/server che permette all’utente di
esplorare un dataset creato dal server. <br>
Il server dovrà essere eseguito su una macchina con un database MySQL in esecuzione.
Il servizio sarà raggiungibile sulla porta 8080, e potrà comunicare con diversi client
contemporaneamente. <br>
I servizi offerti dal server sono i seguenti:
- Caricamento del Database contenente il _dataset_
- Ricerca di _frequent pattern_ dato un _minimo supporto_
- Scoperta di _emerging pattern_ sulla base di un valore di _grow rate_ e _minimo supporto_
- Salvataggio e caricmento su file di _frequent_ ed _emerging pattern_

Il client, da riga di comando, permette di collegarsi ad una macchina che sta eseguendo un’istanza
del server (ServerSocket), il cui indirizzo IP e porta devono essere specificati all’avvio del client.
<br>
 L’utente dovrà, successivamente, specificare se effettuare una nuova ricerca, oppure consultare l'archivio per reperire frequent patter ed emerging pattern gia trovati in precedenza. In
entrmabi i casi l'utente sara tenuto ad inserire i nomi della tabella target e quella di background insieme ai valori di minimo supporto e di grow rate. <br>
Una volta effettuata la scelta, si avvierà l’esplorazione del dataset o dell'archivio, tramite **l'algoritmo APRIORI**. Alla fine del processo, all’utente sarà presentato l'output della ricerca.
 Sarà quindi possibile ricominciare una nuova ricerca o interrogare l'archivio.
Il salvataggio dell'output verrà effettuato in automatico in seguito a una nuova ricerca. L’utente sarà informato di eventuali errori sollevati dal server
durante l’esecuzione delle operazioni richieste.

## Progetto Esteso
L’estensione per il progetto consiste in un’interfaccia grafica per desktop, sviluppata esclusivamente lato client (in server non richiede interazione con l'utente).
Tale interfaccia è stata sviluppata tramite l'applicativo **Scene Builder** per quanto concerne la progettazione grafica delle finestre e quindi dei relativi _bottoni_, _campi di testo_ e _label_. L'altro tool utilizzato è stato il framework **javaFX**, ovvero una libreria Java per la gestione del codice relativo alle diverse finestre menzionate precedentemente.

Le classi utilizzate per la gestione dell'interfaccia grafica sono 4 e si trovano all'interno dei package _controller_ e _connection_ unitamente al package che contiene i file _.fxml_
(ovvero i file generati da scene builder).

Tali classi sono:

- **ManagerConnection**:
questa classe si occupa di inizializzare e conseguentemente chiudere la connessione con il server. Infatti il metodo _initConnection_ va ad inizializzare l'oggetto _socket_ con l'indirizzo e la port del server.

- **ControllerPort**:
Questa classe gestisce la prima finestra grafica mostrata all'avvio del software. In particolare, essa richiede l'inserimento dell'indirizzo e della port del server a cui connettersi, dopo aver premuto il pulsante _connetti_ viene richiamato il metodo _initConnection_ sopra menzionato.

- **ControllerParameters**:
Se la connessione con il server è andata a buon fine la getione viene passata alla suddetta classe che si occupa di gestire la finestra per l'inserimento dei paramentri di ricerca. Essa presenta un radio button per la selezione del criterio di ricerca e una serie di text field per l'inserimento dei valori necessari. Se quest ultimi risultano corretti i dati vengono inviati al server per la loro elaborazione.

- **ControllerResults**
Questa classe si occupa solo della gestione della finestra che contiene l'output del server. In particolare, in questa finestra vengono riportati i parametri inseriti in precedenza e la risposta del Server (eventuali _frequent pattern_ ed _emerging pattern_).

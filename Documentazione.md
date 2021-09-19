<center>

# DOCUMENTAZIONE

</center>

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
L’estensione per il progetto consiste in un’interfaccia grafica per desktop, sviluppata esclusivamente lato client (il server non richiede interazione con l'utente).
Tale interfaccia è stata sviluppata tramite l'applicativo **Scene Builder** per quanto concerne la progettazione grafica delle finestre e quindi dei relativi _bottoni_, _campi di testo_ e _label_. Mentre per quando riguarda la gestione del codice il tool utilizzato è stato il framework **javaFX**, ovvero una libreria Java che contiene una serie di classi e metodi per la gestione delle finestre menzionate precedentemente. <br>

L'estensione prevede un'interfaccia grafica suddivisa in 3 finestre principali:
- La prima finestra viene mostrata all'avvio dell'applicativo e consente all'utente di avere a disposizione un _Help command_ e di potersi connettere al server tramite un indirizzo e una port.

- La seconda finestra viene mostrata se la connessione con il server non ha prodotto errori. Essa permette all'utente di inserire tutti i parametri necessari per poter eseguire una ricerca nel dataset.

- La terza e ultima finestra si occupa di mostrare a video l'output dell'elaborazione del server.  <br><br>

# Guida di installazione
I file <i>.jar .bat </i> sono riposti nella cartella _bin_ di ogni progetto, vi è anch una cartella _bin_ per l'intero progetto contenente due file _.bat_ i quali un file avvia server e client base e l'altro server e client esteso.

## Installazione Server
Per eseguire il Server sulla propria macchina è necessario avere:
- Installato la _Java RunTime Enviroment_ 8 o versioni successive
- Aver installato _MySQL_
- Eseguire il Servizio _MySQL_

### Installazione Java
Per poter installare la _Java RunTime Enviroment_ basta andare sul seguente link: https://www.java.com/it/download/manual.jsp .<br>
Nel caso fosse già installata la Java RunTime può essere verificata la sua versione immettendo il comando <code> java -version </code> sulla _Comand Line_.

### Installazione MySQL
MySQL è il _Database Management System_ usato nel progetto e può essere scaricato dal seguente link https://dev.mysql.com/downloads/installer/.<br>
Una volta installato MySQL esso dovrà essere eseguito per poter avviare l'applicazione.<br>
Per poter avviare il servizio _MySQL_ basta andare in _Servizi_ che si trova sulla barra delle applicazione trovare il servizio _MySQL_ ed eseguirlo.

## Installazione Client
Per poter eseguire correttamente il Client (sia del progetto base che del progetto esteso) bisogna avere:
- Installato la _Java RunTime Enviroment_ 8 o versioni successive
- Avere un server in ascolto

**NB:** Il Client del progetto esteso potrà essere avviato anche senza nessun Server in ascolto ma al momento in cui ci si dovrà collegare al Server (prima finestra di apertura del Client del progetto esteso) verranno sollevati degli errori di connessione.<br><br>

# Guida Utente
## Avvio del Server 
Per avviare il server è necessario eseguire il file _avvio server.bat_ nella stessa cartella di _server.jar_.
Il server può anche essere eseguito da riga di comando,tramite l'istruzione <br>
<p align = "center"> <i> java -jar Server.jar </i> </p>

## Avvio del Client (progetto base)
Per avviare il client è necessario eseguire il file <i> avvio client base.bat    </i> nella stessa cartella di <i> client_base.jar </i>, in questo modo il client si connetterà ad un server in esecuzione sulla propria macchina su una porta 8080 .
Il client può anche essere eseguito da riga di comando,tramite l'istruzione <br>
<p align = "center"> <i> java -jar client_base.jar [INDIRIZZO] [PORTA]  </i></p>
 
## Avvio del Client (progetto esteso)
Per avviare il client del progetto esteso è necessario eseguire il file <i> avvio client_esteso.bat </i> nella stessa cartella di <i> Client esteso.jar </i>, in questo modo verrà aperta una finestra dove dovranno essere inserite l'indirizzo e la porta del server sul quale ci si vuole connettere.
Il client può anche essere eseguito da riga di comando,tramite l'istruzione <br>
<p align = "center"> <i> java -jar Client esteso.jar </i></p>
 
 ## Avvio del Server e del Client 
All'interno della cartella <i> bin </i> vi si trovano due file <i> .bat </i>:
- <i> avvio_server_e_client_base.bat </i> il quale avvia automaticamente il server e il client del progetto base facendolo collegare automaticamente al server.
- <i> avvio_server_e_client_esteso </i> il quale avvia automaticamente il server e il client del progetto esteso con l'apertura della prima finestra per il collegamento al server.<br><br>

 # Guida di utilizzo
 ## Utilizzo Client (progetto base)

 Una volta avviato il client del progetto base e la connessione è andata a buon fine si specifica quale operazione si vuole fare:
 - nuova scoperta (premendo 1)
 - risultati in archivio (premendo 2) 
 <br>

 <center><img src =  "img/img1.png" width=400></center>

<br>

Una volta effettuata la scelta bisogna inserire i seguenti dati:
- valore minimo di supporto
- growrate (o tasso di crescita)
- tabella target
- tabella background

Questi dati saranno inviati al server.<br>
Successivamente ci sarà la ricerca dei pattern nel dataset in base ai criteri inseriti che verranno inviati al client e stampati a video.
 <br>

 <center><img src =  "img/img2.png" width=250></center>

<br>

 <center><img src =  "img/img7.png" width=250></center>

<br>

 <center><img src =  "img/img8.png" width=250></center>

<br>

## Utilizzo del Client (progetto esteso)

### Schermata di connessione con il Server 

All'avvio del client del progetto esteso verrà aperta una finestra dove dovranno essere inseriti indirizzo e porta del server per avviare la connessione.<br>
Nella finestra avremo due bottoni:
- **Connetti** il quale connetterà il client con il server se i dati sono corretti
- **Help** il quale una volta premuto stampa un avviso contenente le principali informazioni dell'applicazione.

 <br>

 <center><img src =  "img/img3.png" width=400></center>

<br>

 <center><img src =  "img/img4.png" width=200></center>


<br>

### Schermata di Inserimento dei dati
Appena il client si collega con il server si aprirà una finestra relativa all'inserimento dei dati(i dati da inserire sono gli stessi del client base) per ricercare i pattern nel dataset.

 <br>

 <center><img src =  "img/img5.png" width=400></center>

<br>


Nel caso i dati non siano corretti verranno stampati a video degli avvisi relativi agli errori di inserimento dei dati.<br>
_Es_
 <br>

 <center><img src =  "img/img6.png" width=250></center>

<br>

Nella schermata è anche presente un bottone pulisci il quale "pulisce" i campi dove inserire i dati per la ricerca dei pattern

### Schermata di stampa dei risultati
Se non ci dovessero essere errori sull'inserimento dei dati il server effettuerà le ricerche dei pattern, se il server trova i pattern il client aprirà una nuova finestra dove sono mostrati i risultati, altrimenti sarà mostrata a video un messaggio che ci dice che non sono stati trovati i pattern.

<br>

 <center><img src =  "img/img9.png" width=400></center>

<br>

 <center><img src =  "img/img10.png" width=400></center>

<br>
<br>

# Esempi di Test
<i> Per i test è stata usata la versione del client esteso in quanto più precisa sui messaggi di errori.<br>
Gli output numerici attesi sono stati ricavati dalla documentazione fornita insieme alle esercitazioni effettuate durante il corso.  
</i> <br>

## Esempi di test senza errori
**Input:** <br>
- tabella target = _"playtennistarget"_ <br>
- tabella backgorund = _"playtennisBackground"_ <br>
- minimo supporto = _0.3_ <br>
- growrate = _1_ <br>

**Output:**
<br>

 <center><img src =  "img/1.png" width=400></center>

<br>

 <center><img src =  "img/2.png" width=400></center>

 <br>

  <center><img src =  "img/3.png" width=400></center>
<br>

 <center><img src =  "img/4.png" width=400></center>

<br>

 <center><img src =  "img/5.png" width=400></center>

## Esempi di test contenenti errori

**Input:** inserire un indirizzo sbagliato <br>
**Output:** messaggio di errore relativo all'indirizzo errato

<br>

 <center><img src =  "img/testAccesso1.png" width=400></center>

<br>


**Input:** inserire un valore numerico errato nel campo porta per il collegamento al server <br>
**Output:** messaggio di errore relativo nell'inserire un valore numerico nel campo porta per collegarsi al server

<br>

 <center><img src =  "img/portaNonNumerica.png" width=400></center>

<br>

**Input:** dati relativi ad un file non esistente <br>
**Output:** messaggio di errore relativo alla non presenza del file
 
<br>

 <center><img src =  "img/archivioVuoto.png" width=400></center>

<br>

**Input:** inserimento di dati in modo da non avere emerging pattern <br>
**Output:** stampa dei frequent pattern con un avviso che gli emerging pattern non sono stati individuati 

<br>

 <center><img src =  "img/epNonIndividuati.png" width=400></center>

<br>

**Input:** inserire un valore di growrate minore o uguale a 0 <br>
**Output:** messaggio di errore relativo al growrate errato
<br>

 <center><img src =  "img/growrateErrato.png" width=400></center>

<br>

**Input:** lasciare i campi vuoti dei dati relativi alla ricerca dei pattern<br>
**Output:** messaggio di errore relativi ai dati non inseriti
<br>

 <center><img src =  "img/noBGnoSupporto.png" width=400></center>

<br>

**Input:** inserire valori in modo tale da avere i frequent pattern e gli emerging pattern vuoti <br>
**Output:** messaggio di errore relativo all'assenza di risultati nella ricerca
<br>

 <center><img src =  "img/risultatiVuoti.png" width=400></center>

<br>

**Input:** inserire un valore di supporto maggiore di 1 o minore di 0 <br>
**Output:** messaggio di errore relativo al supporto errato
<br>

 <center><img src =  "img/supportoErrato.png" width=400></center>

<br>

<br>

**Input:** inserire un valore non numerico nel campo growrate o nel campo del minimo supporto <br>
**Output:** messaggio di errore relativo all'inserimento del valore non numerico
 
<br>

 <center><img src =  "img/valoriNonNumerici.png" width=400></center>

<br>
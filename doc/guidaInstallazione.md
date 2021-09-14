# Guida di installazione
I file _.jar .bat_ e (ove necessario) _.sql_ sono riposti nella cartella _bin_ di ogni progetto.

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
Per poter eseguire correttamente il Client bisogna avere:
- Installato la _Java RunTime Enviroment_ 8 o versioni successive
- Avere un server in ascolto

**NB:** Il Client potrà essere avviato anche senza nessun Server in ascolto ma al momento in cui ci si dovrà collegare al Server(prima finestra di apertura del Client) verranno sollevati degli errori di connessione.


## Oppgave 1: Lagre data i PostgreSQL


Du m� installere PostgreSQL f�r du starter med oppgaven. Det kan du gj�re herfra: https://www.postgresql.org/. PostgreSQL er en open source database med bedre ytelse og stabilitet enn MySQL og en av de mest popul�re databasene i seri�se prosjekter.

Du trenger ogs� en databaseklient. Jeg anbefaler DBeaver som du kan installere herfra: https://dbeaver.io/ DBeaver er open source og st�tter en stor rekke databaser. Den har ogs� mulighet til � generere visuelle databaseskjemaer, utforske databaser og mye mer.

I prosjektet finnes det en fil `no.kristiania.pgr200.database.ConferenceDatabaseProgram`. **F�rste oppgave er � f� denne til � kj�re og lagre data i PostgreSQL databasen**.

For at dette skal fungere m� du:

1. Installere PostgreSQL og DBeaver
2. Koble deg til PostgreSQL med DBeaver
3. Opprette en databasebruker
4. Opprette en logisk database i databaseserveren
5. Kj�re `ConferenceDatabaseProgram`
6. Sjekke at det har kommet data i PostgreSQL ved hjelp av DBeaver

Her er flere detaljer:

* For � koble DBeaver til PostgreSQL, velg File > New > DBeaver > Database Connection
  Du skal benytte brukernavnet `postgres` og passorder du satte n�r du installerte PostgreSQL
* For � opprette en databasebruker skal du bruke kommandoen [CREATE USER](https://www.postgresql.org/docs/current/static/sql-createuser.html).
  Benytt brukernavn og passord som du finner i `ConferenceDatabaseProgram.createDataSource`
* For � opprette en logisk database skal du bruke kommandoen [CREATE DATABASE](https://www.postgresql.org/docs/current/static/sql-createdatabase.html).
  Bruk navnet `conferencedb_test` p� databasen
* N�r du starter `ConferenceDatabaseCommand` m� du angi er argument "insert"
* Du kan s� utforske tabellene i DBeaver (men du m� f�rst h�yreklikke p� Database Navigator objektene og velge Refresh)
* Du kan ogs� utf�re kommandoen `SELECT * FROM CONFERENCE_TALK` i et SQL Editor vindu i DBeaver


## Oppgave 2: Laste ut data fra databasen

Kommandoen `select * from CONFERENCE_TALK` lister opp alle radene i `CONFERENCE_TALK` tabellen. Implementer en kommando i `ConferenceDatabaseProgram` som utf�rer denne kommandoen og skrive ut radene.

Tips: Du b�r bruke `Connection#prepareStatement` og `PreparedStatement#executeQuery` for � f� tak i et `ResultSet` objekt. Benytt `ResultSet#next` og `ResultSet#getString` for � hente ut data fra `ResultSet`.


## Oppgave 3: Oppdater `ConferenceTalksDaoTest`

`ConferenceTalksDaoTest` kan ogs� sjekke at data som har blitt lagt inn kan hentes ut igjen. Endre testen `shouldInsertConferenceTalks` til � ta med dette.





# Simulazione Pizzeria (Java)

Questo progetto simula una semplice pizzeria con clienti, camerieri e una gestione base delle interazioni tramite thread.

## 🔧 Struttura dei file

### `Main.java`
Contiene il **punto di ingresso** del programma. In questa classe:
- Viene creata un'istanza della pizzeria.
- Vengono lanciati i thread di `Cameriere` e `Cliente`.
- Si simula il comportamento concorrente di più clienti e camerieri nella stessa pizzeria.

### `Cliente.java`
Modella un cliente:
- Estende `Thread`.
- Ogni cliente attende un tempo casuale, simula la scelta della pizza e fa una richiesta alla pizzeria.
- Dopo aver ordinato, attende che un cameriere lo serva.

### `Cameriere.java`
Modella un cameriere:
- Estende `Thread`.
- Si mette in attesa di clienti da servire (usa una struttura condivisa con `Pizzeria`).
- Servizio dei clienti avviene in modo concorrente, uno alla volta.

### `Pizzeria.java`
È il **componente centrale**:
- Contiene una `BlockingQueue` o simile per gestire la comunicazione tra clienti e camerieri.
- Tiene traccia degli ordini effettuati.
- Espone metodi sincronizzati per gestire ordine e servizio.

## 🚀 Avvio

Compilare ed eseguire con:
```bash
javac *.java
java Main
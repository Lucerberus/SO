# 🏖️ Simulatore di Lido Balneare - Java Multithreading

Un sistema multithreadato per simulare la gestione di un lido balneare con un numero limitato di postazioni. I clienti possono essere **abbonati** o **occasionali**, e accedono tramite una **biglietteria sincronizzata**.

---

## ⚙️ Funzionalità principali

### ✅ Tipi di clienti
- **Abbonati**:
  - Hanno **priorità assoluta** nella coda.
  - Possono arrivare in gruppo.
  - Se il `numero_gruppo > 2` occupano **2 postazioni**.
  - Sempre accettati (entro il limite iniziale fissato all’avvio).

- **Occasionali**:
  - Vengono serviti **solo se la coda degli abbonati è vuota**.
  - Possono essere rifiutati se non ci sono abbastanza postazioni disponibili:
    - 1 postazione se gruppo ≤ 2
    - 2 postazioni se gruppo > 2

### 🧮 Postazioni
- Ogni postazione comprende 2 lettini e 1 ombrellone.
- Inizializzazione:
  - `N` postazioni totali
  - `n_abbonamenti` (limite massimo: `N / 2`)
  - Le postazioni **prenotabili da clienti occasionali** sono `postazioni_disponibili_occasionali = N_postazioni - n_abbonamenti*2`.

---

## 🧵 Thread & Concorrenza

- Ogni cliente che accede al lido viene gestito in un **thread separato**.
- La permanenza varia da **30 a 60 secondi** (tempo casuale).
- Il metodo `servi_clienti()` è sincronizzato e gestisce una coda condivisa per abbonati e occasionali.
- Le code sono:
  - `CodaAbbonati`
  - `CodaOccasionali`

---

## 📐 Esempio di flusso

```plaintext
1. Cliente arriva → in_coda(cliente)
2. Biglietteria chiama → servi_clienti()
3. Se abbonato → entra subito
4. Se occasionale → controlla postazioni disponibili:
     - gruppo ≤ 2 → serve se ≥1 postazione
     - gruppo > 2 → serve se ≥2 postazioni
5. Cliente usa la postazione per 30–60 secondi (gestionePermanenza)
6. Alla fine libera la postazione

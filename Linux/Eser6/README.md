# README – Spiegazione dettagliata script `ese6.sh`

Questo script Bash serve a **cercare e stampare email valide** da file di testo contenuti in una directory.  
L’email deve avere il formato:
```
nome.cognome@dominio.com
```
dove **nome, cognome e dominio** contengono **solo lettere**, senza numeri o simboli.

---

## 📜 Obiettivo

Lo script riceve:
- Una **directory** come unico argomento

E per ogni file **.txt ordinario** al suo interno:
- cerca righe contenenti email in formato valido
- stampa quelle righe

---

## 🧱 Struttura del codice

### 1. Shebang

```bash
#!/bin/bash
```

---

### 2. Controllo numero argomenti

```bash
if [ "$#" -ne 1 ]; then
    echo "Usa: $0 directory"
    exit 1
fi
```

- Richiede **esattamente un argomento**
- In caso contrario, stampa messaggio d’uso e termina

---

### 3. Assegna la directory

```bash
directory="$1"
```

---

### 4. Ciclo su tutti i file nella directory

```bash
for file in "$directory"/*
```

- Scorre ogni file contenuto nella directory
- ⚠️ È fondamentale avere `/*` dopo la variabile, altrimenti **non legge il contenuto**

---

### 5. Controllo file ordinario

```bash
if [ -f "$file" ]; then
```

- Verifica che sia un file normale (non una cartella, né un link)

---

### 6. Ricerca email valide con `awk`

```bash
awk '$0 ~ /^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$/ { print $0 }' "$file"
```

- Cerca righe che matchano **esattamente** l’espressione regolare

---

## 🔍 Espressione REGEX usata:

```regex
^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$
```

Significato:
- `^` e `$` ➜ inizio e fine della riga
- `[a-zA-Z]+` ➜ solo lettere (almeno una)
- `\.` ➜ punto letterale
- `@` ➜ chiocciola
- `.com` ➜ estensione finale obbligatoria

✔️ Esempio valido: `mario.rossi@gmail.com`  
❌ Esempio NON valido: `123rossi@gmail.com` (nome con cifre)

---

### 🔧 Nota tecnica

Nel codice è commentato anche questo:
```bash
awk -v arg='^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$' '$0 ~ arg {print $0}'
```

❗ Non funziona correttamente in alcune shell (es. Cygwin), perché il backslash viene gestito male nelle variabili `awk`.

---

## ✅ Esempio d’uso

Supponendo di avere una cartella `es03` con file `.txt` dentro:
```bash
./ese6.sh es03
```

🖨️ Output: tutte le righe contenenti email valide

---



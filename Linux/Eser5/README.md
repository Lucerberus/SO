# README – Spiegazione dettagliata script `ese5.sh`

Questo script Bash cerca un **pattern specifico** all’interno di tutti i file con una data estensione (es. `.txt`) nella directory corrente, e stampa i **nomi dei file** che contengono almeno una riga corrispondente.

---

## 📜 Obiettivo

Lo script riceve:
1. un **pattern** da cercare (prima riga)
2. una **estensione di file** (es. `txt`, `log`, ecc.)

Restituisce l’elenco dei file che contengono quel pattern.

---

## 🧱 Struttura del codice

### 1. Shebang

```bash
#!/bin/bash
```
Indica che lo script deve essere eseguito con l’interprete `bash`.

---

### 2. Controllo numero argomenti

```bash
if [ "$#" -ne 2 ]; then
    echo "usa: $0 Pattern tipo_file"
    exit 1
fi
```

- Controlla che ci siano **esattamente due argomenti**
- In caso contrario, stampa un messaggio d’uso e termina


---

### 3. Assegnazione variabili

```bash
pattern="$1"
tipo_file="$2"
```

- `$1` → pattern da cercare nel contenuto dei file
- `$2` → estensione dei file da esaminare (es. `txt`)

---

### 4. Messaggio iniziale

```bash
echo "Elenco dei file che contengono il pattern '$pattern' con estensione '$tipo_file':"
```

Stampa un’intestazione prima di elencare i file trovati

---

### 5. Ciclo su tutti i file con l’estensione specificata

```bash
for file in *."$tipo_file"
```

- Cerca tutti i file che finiscono con `.estensione` nella directory corrente

---

### 6. Verifica che sia un file ordinario

```bash
if [ -f "$file" ]; then
```

- `-f` verifica che l’elemento trovato sia un file vero (esclude directory, link, ecc.)

---

### 7. Ricerca del pattern con `awk`

```bash
awk -v arg="$pattern" '$0 ~ arg {print FILENAME}' $file
```

- `-v arg="$pattern"` → passa il pattern ad awk
- `$0 ~ arg` → controlla se la riga corrente (`$0`) contiene il pattern
- `{print FILENAME}` → stampa il nome del file dove il pattern è stato trovato

📌 Se il pattern appare in **più righe** dello stesso file, il nome del file verrà stampato più volte.

---

## ✅ Esempio d’uso

```bash
./ese5.sh "cane e gatto" txt
```

Cerca la stringa `"cane e gatto"` in tutti i file `*.txt` della directory.

---

## 🧠 Miglioramento possibile

Per stampare il **nome del file una sola volta**, si può aggiungere `exit` dentro il blocco `awk`:
```bash
awk -v arg="$pattern" '$0 ~ arg {print FILENAME; exit}' "$file"
```

---


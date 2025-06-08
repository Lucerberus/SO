# README – Spiegazione dettagliata script `Ese1.sh`

Questo script Bash consente di **rinominare un file esistente**, aggiungendo un **prefisso** specificato dall’utente davanti al nome originale del file.

---

## 📜 Obiettivo

Lo script riceve **due argomenti**:
1. `prefisso` – stringa da anteporre al nome del file
2. `nomefile` – il file esistente da rinominare

Esegue:
- controlli sugli argomenti
- verifica che il file esista
- crea un nuovo nome con il prefisso
- esegue la rinomina

---

## 🧱 Struttura del codice

### 1. Shebang

```bash
#!/bin/bash
```
Indica che lo script deve essere eseguito con l’interprete `bash`.

---

### 2. Controllo numero di argomenti

```bash
if [ "$#" -ne 2 ]; then
    echo "Usa: $0 prefisso nomefile"
    exit 1
fi
```

- `"$#"` restituisce il numero di argomenti passati allo script.
- Se non sono **esattamente due**, stampa un messaggio e termina con codice di errore `1`.

---

### 3. Assegnazione delle variabili

```bash
prefisso=$1
nomefile=$2
```

- `$1` → primo argomento: prefisso
- `$2` → secondo argomento: nome del file da rinominare

---

### 4. Verifica che il file esista

```bash
if [ ! -f "$nomefile" ]; then
    echo "Errore: il file '$nomefile' non esiste nella directory corrente."
    exit 1
fi
```

- `-f` → controlla che esista **un file ordinario** con quel nome
- Se il file **non esiste**, mostra un errore ed esce.

---

### 5. Costruzione del nuovo nome

```bash
nuovo_file="${prefisso}_${nomefile}"
```

- Crea la nuova stringa combinando prefisso e nomefile, separati da `_`

Esempio:
- `prefisso=compito`
- `nomefile=esempio.txt`
- → `nuovo_file=compito_esempio.txt`

---

### 6. Rinomina del file

```bash
mv "$nomefile" "$nuovo_file"
```

- `mv` esegue la rinomina effettiva.

---

### 7. Messaggio finale

```bash
echo "Il file è stato rinominato in '$nuovo_file'."
```

- Conferma il successo dell’operazione.

---

## ✅ Esempio d'uso

```bash
./Ese1.sh backup documento.txt
```

Se `documento.txt` esiste, verrà rinominato in `backup_documento.txt`.

---

## 🧀 Proverbio a tema:
> “Chi cambia nome o fa pe’ sfuggì ‘o guaio... o pe’ fa backup!” 😄

Questo script è utile per operazioni batch o preparazioni automatiche di consegne.

# Script Bash - Esercizi con Regex e Automazione

Questo file README contiene la spiegazione dettagliata dei seguenti script `.sh` basati sugli esercizi del corso di Sistemi Operativi. Le consegne si trovano nel file PDF `05e._es_scripting.pdf`.

---

## ✅ [Esercizio 1 – Rinomina con prefisso](Eser1/README_Ese1.md) 

**Script:** `Ese1.sh`  
Riceve:
- un **prefisso**
- un **nome file**

🔧 Se il file esiste, lo rinomina anteponendo il prefisso:
```bash
mv "$nomefile" "${prefisso}_${nomefile}"
```

---

## ✅ [Esercizio 2 – Elenco file da directory](Eser2/README_ese2.md)

**Script:** `ese2.sh`  
Riceve:
- una **directory**

🔎 Controlla che esista e stampa solo i **nomi dei file** (senza path):
```bash
echo "$file" | awk -F / '{print $NF}'
```

---

## ✅ [Esercizio 3 – Rinomina file in directory](Eser3/README_ese3.md)

**Script:** `ese3.sh`  
Riceve:
- un **prefisso**
- una **directory**

🔁 Per ogni file nella directory, lo rinomina anteponendo il prefisso:
```bash
mv "$file" "$directory/${prefisso}_$(basename "$file")"
```

---

## ❌ [Esercizio 4 – ESERCIZIO DI MERDA](Eser4/README.md)

È talmente brutto che manco sono riuscito a finire

---

## ✅ [Esercizio 5 – Cerca stringa in file `.txt`](Eser5/README_ese5.md)

**Script:** `ese5.sh`  
Riceve:
- un **pattern da cercare**
- un'estensione (es. `txt`)

🔍 Cerca nei file con quell’estensione e stampa quelli che contengono il pattern:
```bash
awk -v arg="$pattern" '$0 ~ arg { print FILENAME }'
```

---

## ✅ [Esercizio 6 – Estrazione email](Eser6/README_ese6.md)

**Script:** `ese6.sh`  
Riceve:
- una **directory**

🔍 Cerca in ogni `.txt` nella cartella le email nel formato `nome.cognome@dominio.com` dove tutto è solo **lettere**:
```bash
awk '$0 ~ /^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$/ { print $0 }'
```

---

## 🧠 Espressioni REGEX utilizzate

| Regex | Significato |
|-------|-------------|
| `^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$` | Email in formato nome.cognome@dominio.com (solo lettere) |
| `[^/]*$` | Estrae solo il nome del file da un path |
| `^file-[ab][0-9]*$` | Matcha file che iniziano con "file-a"/"file-b" seguiti da numeri |
| `$0 ~ arg` | Matcha una riga qualsiasi contenente il pattern `arg` |

---



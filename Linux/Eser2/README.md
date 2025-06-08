# README – Spiegazione dettagliata script `ese2.sh`

Questo script Bash consente di **stampare i nomi dei file** presenti in una directory specificata come argomento, mostrando **solo il nome del file** (senza il percorso completo).

---

## 📜 Obiettivo

Lo script riceve **un solo argomento**:
- Una **directory** da esplorare

Esegue:
- controllo sul numero di argomenti
- verifica che la directory esista
- stampa il nome di ogni file al suo interno (non il path completo)

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
if [ "$#" -ne 1 ]; then
    echo "Usa: $0 directory"
    exit 1
fi
```

- Verifica che venga passato **esattamente un argomento**
- In caso contrario, stampa un messaggio di uso corretto e termina


---

### 3. Assegnazione della variabile

```bash
directory="$1"
```

Salva il primo argomento in una variabile chiamata `directory`

---

### 4. Verifica esistenza directory

```bash
if [ ! -d "$directory" ]; then
    echo "Errore: la directory '$directory' non esiste."
    exit 1
fi
```

- `-d` verifica che l’argomento sia una directory esistente
- Se non lo è, esce con messaggio d’errore

---

### 5. Ciclo sui file

```bash
for file in "$directory"/*
do
    echo "$(echo "$file" | awk -F / '{print $NF}')"
done
```

- Scorre ogni file nella directory
- `awk -F / '{print $NF}'` stampa **solo l’ultima parte del path**, ovvero il **nome del file**

📌 Alternativa (commentata nello script):
```bash
basename "$file"
```

---

## ✅ Esempio d'uso

```bash
./ese2.sh es01
```

Supponendo che `es01` contenga:
```
es01/prova.txt
es01/test.jpg
```

🖨️ Output:
```
prova.txt
test.jpg
```

---

## 🧠 Nota tecnica

- Il comando `awk -F / '{print $NF}'` usa `/` come separatore e stampa **l'ultimo campo**, cioè il nome del file
- Utile quando vuoi evitare di usare `basename`, o scrivere codice più generico

---



# README – Spiegazione dettagliata script `ese3.sh`

Questo script Bash consente di **rinominare tutti i file ordinari** all’interno di una directory, anteponendo un **prefisso** specificato dall’utente.

---

## 📜 Obiettivo

Lo script riceve **due argomenti**:
1. `prefisso` – stringa da anteporre al nome dei file
2. `directory` – directory contenente i file da rinominare

Per ogni file ordinario (esclude directory), aggiunge il prefisso e rinomina il file.

---

## 🧱 Struttura del codice

### 1. Shebang

```bash
#!/bin/bash
```
Indica che lo script deve essere eseguito usando l’interprete `bash`.

---

### 2. Controllo numero argomenti

```bash
if [ "$#" -ne 2 ]; then
    echo "Usa: $0 prefisso directory"
    exit 1
fi
```

- `$#` → numero di argomenti passati allo script
- Se non sono **esattamente due**, stampa un messaggio e termina

---

### 3. Assegnazione variabili

```bash
prefisso="$1"
directory="$2"
```

- `$1` → il prefisso da anteporre
- `$2` → la directory da esplorare

---

### 4. Controllo esistenza directory

```bash
if [ ! -d "$directory" ]; then
    echo "erorre la directory non esiste"
    exit 2
fi
```

- `-d` → verifica che il secondo argomento sia una directory esistente
- Se no, stampa errore e termina

📝 **Nota**: `erorre` è un refuso → dovrebbe essere `errore`

---

### 5. Ciclo sui file

```bash
for file in "$directory"/*
```

- Scorre tutti gli elementi dentro la directory specificata

---

### 6. Verifica se è un file ordinario

```bash
if [ -f "$file" ]; then
```

- `-f` → verifica che l’elemento sia un **file**, non una directory o altro

---

### 7. Generazione nuovo nome

```bash
nome_file="$prefisso$(echo "$file" | awk -F / '{print $NF}')"
```

- Estrae il **nome base del file** (usando `awk -F / '{print $NF}'`)
- Lo concatena con il prefisso:
  - es: `pippo + documento.txt` = `pippo_documento.txt`

📝 Alternativa più elegante (già presente come commento):
```bash
nome_file="${prefisso}_$(basename "$file")"
```

---

### 8. Rinomina del file

```bash
mv "$file" "$directory/$nome_file"
```

- Sposta/riscrive il file con il nuovo nome nella stessa directory

---

### 9. Output informativo

```bash
echo "rinnominato $file in $nome_file"
```

- Stampa il cambiamento effettuato

---

## ✅ Esempio d'uso

Supponiamo che `esempio/` contenga `a.txt`, `b.txt`, `c.jpg`

```bash
./ese3.sh test esempio
```

Produrrà:
```
esempio/testa.txt
esempio/testb.txt
esempio/testc.jpg
```

---

## 🧠 Nota su sicurezza

Questo script **non verifica** se il nuovo nome esiste già → può **sovrascrivere** file esistenti.  
Può essere migliorato con:
```bash
if [ -e "$directory/$nome_file" ]; then echo "File esiste già"; fi
```

---

## 🧀 Proverbio a tema:
> “Chi mette ‘o prefisso, cambia ‘a faccia ma nun ‘o contenuto!” 😄  
E mo rinomini tutto, ma senza perdere ‘nu byte.


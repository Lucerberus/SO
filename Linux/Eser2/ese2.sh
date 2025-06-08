#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usa: $0 directory"
    exit 1
fi
# assegno variabile
directory="$1"
#importate mettere i spazio tra le parentesi e le virgolette
if [ ! -d "$directory" ]; then # -d verifica se è una directory e non un file
    # se la directory non esiste
    echo "Errore: la directory '$directory' non esiste."
    exit 1
fi

echo "Elenco dei file nella directory '$directory':"
for file in "$directory"/*
do
    
    #echo "$(basename "$file")" 
    # si puo usare basename per ottenere solo il nome del file senza il percorso
    # oppure si puo usare awk per ottenere il nome del file
    # echo "$file" | awk -F / '{print $NF}'
    echo  ecco il il nome del file tramite awk: "$(echo "$file" | awk -F / '{print $NF}')"
done 
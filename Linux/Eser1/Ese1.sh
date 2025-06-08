#!/bin/bash

# controllo numero argomenti
if [ "$#" -ne 2 ]; then #verifico che siano due argomenti
    echo "Usa: $0 prefisso nomefile" # messaggio di errore
    exit 1
fi
#assegno variabili
prefisso=$1
nomefile=$2
# controllo se il file esiste
if [ ! -f "$nomefile" ]; then # verifico se il file esiste
    # se il file non esiste
    echo "Errore: il file '$nomefile' non esiste nella directory corrente." # messaggio di errore
    exit 1
fi

#costruisco il file
nuovo_file="${prefisso}_${nomefile}"

# rinomino il file
mv "$nomefile" "$nuovo_file"

echo "Il file è stato rinominato in '$nuovo_file'." # messaggio di successo
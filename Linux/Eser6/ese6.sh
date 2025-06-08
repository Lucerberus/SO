#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usa: $0 directory"
    exit 1
fi

# assegno variabile
directory="$1"

for file in "$directory"/* #serve / per indicare che deve prendere i file denro la directory
#infatti se si liva non succede niente, non trova i file
do
    if [ -f "$file" ]; then

        #awk -v arg='^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$' '$0 ~ arg {print $0}' $file
        awk '$0 ~ /^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$/ { print $0 }' "$file"
        #strano che non funzioni con -v arg='^[a-zA-Z]+\.[a-zA-Z]+@[a-zA-Z]+\.com$'
        #invece se si passa direttamente l'espressione regolare funziona, senza fare il giro da -v
    fi
done
# Questo script cerca e stampa le righe che contengono un indirizzo email
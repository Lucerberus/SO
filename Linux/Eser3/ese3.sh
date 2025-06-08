#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Usa: $0 prefisso directory"
    exit 1
fi
# assegno variabile
prefisso="$1"
directory="$2"

if [ ! -d "$directory" ]; then
    echo "erorre la directory non esiste"
    exit 2
fi

for file in "$directory"/*
do 
    if [ -f "$file" ]; then
        #nome_file=$prefisso"_"$(basename "$file")
        nome_file="$prefisso$(echo "$file" | awk -F / '{print $NF}')"
        mv "$file" "$directory/$nome_file"
        echo "rinnominato $file in $nome_file"
    fi
done
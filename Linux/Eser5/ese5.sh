#!/bin/bash
#controllo numero argomenti
if [ "$#" -ne 2 ]; then
    echo "usa: $0 pattern tipo_file"
    exit 1
fi

#assegno variabili
pattern="$1"
tipo_file="$2"
echo "Elenco dei file che contengono il pattern '$pattern' con estensione '$tipo_file':"
for file in *."$tipo_file"
do
    if [ -f "$file" ]; then
        awk -v arg="$pattern" '$0 ~ arg {print FILENAME}' $file
    fi
done

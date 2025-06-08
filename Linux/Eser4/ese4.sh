#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "usa : $0 espressione  directory_destinazione"
    exit 1
fi

esepressione="$1"
directory_destinazione="$2"

#verifica che la directory di destinazione esista
if [ ! -d "$directory_destinazione" ]; then
    echo "Errore: La directory di destinazione '$directory_destinazione' non esiste."
    exit 3
fi

#echo $(ls -l | awk -v arg="$esepressione" ' $1 ~ /(^-)|(^l)/ && $9 ~ arg {print $9}')

#mv $(ls -l | awk -v expr="$esepressione" '$1 ~ /(^-)|(^l)/ && $11 ~ expr {print $11}') "$directory_destinazione"
mv $(ls -l | awk -v expr="$esepressione" '($1 ~ /^-/ || $1 ~ /^l/) && $11 ~ expr {print $11}') "$directory_destinazione"
echo "Operazione completata.

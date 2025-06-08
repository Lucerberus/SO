#!/bin/bash
 if [ $# -ne 2 ] 
then
 echo "Uso: $0 <REGEX> <CARTELLA_DESTINAZIONE>"
 echo "Specificare solo l'espressione regolare da cercare, e la cartella di destinazione."
 exit 1
 else 
REGEX="$1"
 DESTINATION="$2"
 echo "Cerco file che corrispondono all'espressione regolare: $REGEX"
 echo "Sposto i file nella cartella: $DESTINATION"
 if [ ! -d "$DESTINATION" ] 
then
 echo "La cartella di destinazione non esiste."
 exit 1
 fi
 mv $(ls -l | awk -v arg="$REGEX" ' $1 ~ /(^-)|(^l)/ && $11 ~ arg {print $11}') "$DESTINATION" 
 #mv $(ls -l | awk -v r="$REGEX" '($1~/^-/||$1~/^l/) && $11~r {print $11}') "$DESTINATION"

fi
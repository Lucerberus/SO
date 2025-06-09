#!/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Usa: $0 <path> <ext1> <ext2>"
    exit 1
fi

path=$1
ext1=$2
ext2=$3

if [ -d "$path" ]; then
    for full_path in "$path"/*; do
        if [ -f "$full_path" ]; then
            filename=$(basename "$full_path")
            if [[ "$filename" == *.$ext1 ]]; then
                file_base="${filename%.*}"
                mv "$full_path" "$path/$file_base.$ext2"
                echo "File '$filename' rinominato in '$file_base.$ext2'"
            fi
        fi
    done
    echo "Rinominazione completata."
else
    echo "Errore: la directory '$path' non esiste."
    exit 1
fi

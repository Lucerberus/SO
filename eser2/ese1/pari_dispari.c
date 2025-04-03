#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

/*
Consegna: si scriva un programma in c cha dato un array a di n interi sfrutti due thread distinti
per calcolare rispettivamnete la somma di tutti i numeri in posizione pari e dispari;
*/

















#define MAX 20

// Struttura dati con puntatore all'array condiviso
struct A {
    int* a;  // Puntatore all'array condiviso
    int par; // 1 per somma pari, 0 per somma dispari
    int risultato;
};

// Funzione per calcolare la somma
void* calcola_somma(void* p) {
    struct A* pp = (struct A*) p;
    static int somma = 0;

    for (size_t i = 0; i < MAX; i++) {
        if ((pp->par == 1 && i % 2 == 0) || (pp->par == 0 && i % 2 != 0)) {
            pp->risultato += pp->a[i];
        }
    }

    return NULL; // Restituiamo un puntatore alla somma
}

int main() {
    pthread_t t1_id, t2_id;
    int* t1_rValue;
    int* t2_rValue;
    int array[MAX];  // Array condiviso tra i thread

    struct A vett1, vett2;

    // Inizializziamo il generatore di numeri casuali
    srand(time(NULL));

    // Riempiamo l'array con numeri casuali
    for (size_t i = 0; i < MAX; i++) {
        array[i] = rand() % 101;  // Numeri da 0 a 100
    }

    // Stessa struttura, ma puntano entrambi allo stesso array
    vett1.a = array;
    vett1.par = 1; // Thread 1 calcola somma posizioni pari
    vett2.a = array;
    vett2.par = 0; // Thread 2 calcola somma posizioni dispari

    // Creiamo i thread
    pthread_create(&t1_id, NULL, calcola_somma, &vett1);
    pthread_create(&t2_id, NULL, calcola_somma, &vett2);

    // Attendiamo la fine dei thread e otteniamo i risultati
    pthread_join(t1_id, NULL);
    printf("\nSomma delle posizioni pari: %d", vett1.risultato);
    
    pthread_join(t2_id, NULL);
    printf("\nSomma delle posizioni dispari: %d\n", vett2.risultato);

    // Liberiamo la memoria allocata nei thread

    return 0;
}


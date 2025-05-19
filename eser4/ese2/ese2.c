#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>


#define N 20
#define L 25
void* generanumeri(void* arg)
{
    char* numeri = malloc(N*sizeof(char));
    
    for (int i = 0; i < N; i++)
    {
        numeri[i] = '0' + (rand() % 10); // Genera un numero casuale tra 0 e 9 
        // '0' in ascii è 48, '1' è 49, ..., '9' è 57
        // quindi sommando un numero tra 0 e 9 a '0' ottengo il carattere corrispondente

    }
    return numeri;
}

void* generalettere(void* arg)
{
    char* lettere = malloc(L*sizeof(char));
    for(int i = 0; i< L; i++)
    {
        lettere[i] = 'a' + (rand() % 26); // Genera una lettera casuale tra 'a' e 'z'
        // 'a' in ascii è 97, 'b' è 98, ..., 'z' è 122
        // quindi sommando un numero tra 0 e 25 a 'a' ottengo il carattere corrispondente
    }
    return lettere;
}


int main()
{
    printf("--- inizio del programma\n");
    // Creazione di due thread
    char *numeri, *lettere;
    srand(time(NULL));
    pthread_t thread1, thread2;
    pthread_create(&thread1, NULL, generanumeri, NULL);
    pthread_create(&thread2, NULL, generalettere, NULL);
    // Attesa della terminazione dei thread
    pthread_join(thread1, (void **)&numeri);
    pthread_join(thread2, (void **)&lettere);
    // Stampa dei risultati
    printf("stringa finale : ");
    int i = 0;
    int j = 0;
    int k = 0;
    char stringa_finale[L+N+1];
    while(i < L || j < N){
        for(int tmp = 0; tmp < 2 && i < L; tmp++){
            stringa_finale[k++] = lettere[i++];
        }
        for(int tmp = 0; tmp < 2 && j < N; tmp++){
            stringa_finale[k++] = numeri[j++];
        }
    }
    stringa_finale[k] = '\0'; // Aggiungi il terminatore di stringa
    printf("%s\n", stringa_finale);
    free(numeri);
    free(lettere);
    printf("-- fine programma\n");
    return 0;
}
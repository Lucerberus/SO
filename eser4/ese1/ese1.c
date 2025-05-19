#include <pthread.h>
#include <stdio.h>
#include <unistd.h>


void* sommapari(void* arg)
{
    int* A = (int*)arg;
    int somma = 0;
    for (int i = 0; i < 50; i++)
    {
        if (i % 2 == 0)
        {
            somma += A[i];
        }
    }
    printf("Somma delle posizioni pari: %d\n", somma);
    fflush(stdout);
    return NULL;
}
void* sommadispari(void* arg)
{
    int* A = (int*)arg;
    int somma = 0;
    for (int i = 0; i < 50; i++)
    {
        if (i % 2 != 0)
        {
            somma += A[i];
        }
    }
    printf("Somma delle posizioni dispari: %d\n", somma);
    fflush(stdout);
    return NULL;
}

int main()
{
    printf("--- inizio del programma\n");
    // Creazione di due thread
    int A[50];
    for (int i = 0; i < 50; i++)
    {
        A[i] = i;
    }
    
    pthread_t thread1, thread2;
    pthread_create(&thread1, NULL, sommapari, (void *)A);
    pthread_create(&thread2, NULL, sommadispari, (void *)A);
    // Attesa della terminazione dei thread
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    
    return 0;
}
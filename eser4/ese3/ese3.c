#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>


#define N 3
struct thread_args
{
    int A[N][N];
    int type; // 0 numeri 1 lettere
    int somma;
};

void* thread_func(void* parameters)
{
    struct thread_args* pp = (struct thread_args*) parameters;
    int tipo = pp->type;
    // Calcolo del determinante di Sarrus
    // per la matrice 3x3
    if(tipo == 0)
    {
        pp->somma = pp->A[0][0] * pp->A[1][1] * pp->A[2][2] + 
                      pp->A[0][1] * pp->A[1][2] * pp->A[2][0] +
                      pp->A[0][2] * pp->A[1][0] * pp->A[2][1];

    }
    else
    {
        pp->somma = pp->A[0][2] * pp->A[1][1] * pp->A[2][0] + 
                      pp->A[0][0] * pp->A[1][2] * pp->A[2][1] +
                      pp->A[0][1] * pp->A[1][0] * pp->A[2][2];
    }
    return NULL;
}
void random_matrix(int A[N][N])
{
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            A[i][j] = rand() % 10;
        }
    }
}
void print_matrix(int A[N][N])
{
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            printf("%d ", A[i][j]);
        }
        printf("\n");
    }
}
void copy_matrix(int dest[N][N], int src[N][N]) {
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            dest[i][j] = src[i][j];
}
int main()
{
    printf("--- inizio del programma\n");
    srand(time(NULL));
    struct thread_args args, args2;
    random_matrix(args.A);
    copy_matrix(args2.A, args.A);

    args.type = 0; // differiziazione tra i due thread
    args2.type = 1;

    print_matrix(args.A);
    printf("\n");
    // Creazione dei thread
    pthread_t thread1, thread2;
    pthread_create(&thread1, NULL, thread_func, (void *)&args);
    pthread_create(&thread2, NULL, thread_func, (void *)&args2);

    // Attesa della terminazione dei thread
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    // Stampa dei risultati
    int det1 = args.somma - args2.somma;
    printf("determinante di sarrus : %d\n", det1);
    printf("-- fine programma\n");
    return 0;
}
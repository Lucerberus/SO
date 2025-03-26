#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include <time.h>
// child deve mandare un numero casuale al parent
// lascio lo spazio cosi non vedrete subito la soluzione






















int main()
{
    printf("--- inizio del programma\n");    
    int fd[2];
    int buffer;
    int statofiglio;

    if (pipe(fd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    

    pid_t pid =fork();
    if(pid == 0) // child
    {
        printf("child process: %d\n", (int) getpid());
        //chiudo la lettura nel child poiche deve inviare il dato
        close(fd[0]);
        //genere il numero casuale
        srand(time(NULL));
        int min = 0, max = 100;
        int numero = min + rand() % (max - min + 1); // Intervallo [min, max]
        write(fd[1], &numero, sizeof(numero));
        //inviato
        printf("child process: numero randomico inviato\n");
        
    }
    else if (pid > 0) // parent
    {   
        printf("parent process: %d\n", (int) getpid());
        //chiudo la scrittura al parent poiche deve solo leggere
        close(fd[1]);
        //leggo il dato inviato dal child
        read(fd[0], &buffer, sizeof(buffer));
        printf("parent procces: ecco il numero randomico del figlio = %d\n", buffer);

    }
    else
    {
        //fork error
        printf("eroe nella fork\n");
        return(1);
    }
    
    wait(&statofiglio);

    return 0;
}
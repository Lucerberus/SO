#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
// child manda il proprio pid al parent
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
        //chiudo la lettura nel figlio poiche deve inviare il dato
        close(fd[0]);
        //invio il pid al padre
        int prova = (int) getpid();
        write(fd[1], &prova, sizeof(prova));
        //inviato
        printf("child process: pid inviato\n");
        
    }
    else if (pid > 0) // parent
    {   
        printf("parent process: %d\n", (int) getpid());
        //chiudo la scrittura al padre poiche deve solo leggere
        close(fd[1]);
        //leggo il dato inviato dal figlio
        read(fd[0], &buffer, sizeof(buffer));
        printf("parent procces: ecco il pid del figlio = %d", buffer);

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
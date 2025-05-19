#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int determinaVincitore(char figlio1, char figlio2) {
    if (figlio1 == figlio2) {
        return 0; // Pareggio
    }
    if ((figlio1 == 's' && figlio2 == 'f') ||
        (figlio1 == 'f' && figlio2 == 'c') ||
        (figlio1 == 'c' && figlio2 == 's')) {
        return 1; // Vince il primo giocatore
    }
    return 2; // Vince il secondo giocatore
}

int main()
{
    printf("--- inizio del programma\n");    

    int statofiglio;
    int fp1[2]; // pipe di comunicazione da c1 a parent
    int fp2[2]; // pipe di comunicazione da c2 a parent
    if (pipe(fp1) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    if (pipe(fp2) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    pid_t pid =fork();
    if(pid == 0)
    {
        //child
        
        printf("child 1 process: %d\n", (int) getpid());
        close(fp1[0]); // chiudo il lato di lettura della pipe
        char arma1 = 'f';
        write(fp1[1], &arma1, sizeof(arma1)); // scrivo nella pipe
        printf("child 1: ha inviato la sua arma\n");
    }
    else if (pid > 0)
    {
        //printf("parent process: %d\n", (int) getpid());
        pid_t ppid =fork();
        if(ppid == 0)
            {
                //child
                
                printf("child 2 process: %d\n", (int) getpid());
                close(fp2[0]); // chiudo il lato di lettura della pipe
                char arma2 = 'c';
                write(fp2[1], &arma2, sizeof(arma2)); // scrivo nella pipe
                printf("child 2: ha inviato la sua arma\n");

            }
            else if (pid > 0)
            {   
                //parent
                printf("parent process: %d\n", (int) getpid());
                close(fp1[1]); // chiudo il lato di scrittura della pipe
                close(fp2[1]); // chiudo il lato di scrittura della pipe
                char figlio1;
                char figlio2;
                read(fp1[0], &figlio1, sizeof(char)); // leggo dalla pipe
                read(fp2[0], &figlio2, sizeof(char)); // leggo dalla pipe
                printf("parent: ha ricevuto le armi dei figli\n");
                int risultato = determinaVincitore(figlio1, figlio2);
                if (risultato == 0) 
                    {
                        printf("Pareggio! Si ripete il round.\n");
                    }   
                else if (risultato == 1) 
                    {
                        printf("Il giocatore 1 vince il round!\n");
                    } 
                else 
                    {
                        printf("Il giocatore 2 vince il round!\n");
                    }
                printf("-- fine programma");

            }
            else
            {
                //fork error
                printf("eroe nella fork\n");
                return(1);
            }

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
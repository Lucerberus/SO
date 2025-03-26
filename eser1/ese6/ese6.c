#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include <time.h>
// c1 manda un numero casuale al parent e il parent lo rimanda al c2 e quello lo stampa
// lascio lo spazio cosi non vedrete subito la soluzione































int main()
{
    printf("--- inizio del programma\n");
    int fd[2]; // pipe di comunicazione da c1 a parent
    int buffer;
    if (pipe(fd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }   
    pid_t pid =fork();
    int statofiglio;
    if(pid == 0)
    {
        //child
        printf("child process: %d\n", (int) getpid());
        srand(time(NULL));
        int min = 0, max = 100;
        int numero = min + rand() % (max - min + 1);
        close(fd[0]);
        write(fd[1], &numero, sizeof(numero));
        printf("child: ha inviato il numero random\n");
        
    }
    else if (pid > 0)
    {
        printf("parent process: %d\n", (int) getpid());
        close(fd[1]);
        read(fd[0], &buffer, sizeof(buffer));
        printf("parent: ha ricevuto il numero casuale: %d\n",buffer);
        int pd[2]; // pipe di comunicazione da parent a c2
        int buf; // in questo caso possiamo creare la pipe pd anche solo nel parent, poi che il parent genera entrambi i
        // child e quindi da qui ha accesso ad entrambe le pipe
        if (pipe(pd) == -1)
        {
            perror("pipe");
            exit(EXIT_FAILURE);
        } 
        pid_t ppid =fork();
        if(ppid == 0)
            {
                //child
                printf("child 2 process: %d\n", (int) getpid());
                close(pd[1]);
                read(pd[0], &buf, sizeof(buf));
                printf("child 2: ha ricevuto il numero casuale: %d\n",buf);
                
            }
            else if (pid > 0)
            {
                printf("parent process: %d\n", (int) getpid());
                close(pd[0]);
                write(pd[1], &buffer, sizeof(buffer));  

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
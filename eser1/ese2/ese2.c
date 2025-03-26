#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
// parent ha 2 child dove entrambi sono generati dal parent
// lascio lo spazio cosi non vedrete subito la soluzione




















int main()
{
    printf("--- inizio del programma\n");    
    pid_t pid =fork();
    int statofiglio;
    if(pid == 0)
    {
        //child
        
        printf("child process: %d\n", (int) getpid());
        
    }
    else if (pid > 0)
    {
        printf("parent process: %d\n", (int) getpid());
        pid_t ppid =fork();
        if(ppid == 0)
            {
                //child
                
                printf("child 2 process: %d\n", (int) getpid());
                
            }
            else if (pid > 0)
            {
                printf("parent process: %d\n", (int) getpid());
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
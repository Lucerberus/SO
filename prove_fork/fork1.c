#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
    printf("--- inizio del programma\n");
    
    int contatore=0;
    int N=20;
    
    pid_t pid =fork();

    if(pid == 0)
    {
        //child
        for(int i=0; i<N; ++i)
        {
            printf("c process: counter = %d\t(%d)\n",++contatore, (int) getpid());
            fflush(stdout);//puliamo il buffer server a scrivere sul file in giusto ordine
            sleep((double)rand()/(double)RAND_MAX);//random in [0,1]
        }
    }
    else if (pid > 0)
    {
        //padre
        for (int j = 0; j < N; ++j)
        {
            printf("p process: counter =%d\t(%d)\n",++contatore, (int) getpid());
            fflush(stdout);//puliamo il buffer server a scrivere sul file in giusto ordine
            sleep((double)rand()/(double)RAND_MAX);//random in [0,1]
        }
    }
    else
    {
        //fork error
        printf("eroe nella fork\n");
        return(1);
    }
    printf("-- fine programma");
    sleep(1);

    return 0;
}
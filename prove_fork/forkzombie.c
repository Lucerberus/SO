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
        //figlio
        printf("figlio (%d) dorme per 20s", getpid());
        sleep(40);//random in [0,1]
    }
    else if (pid > 0)
    {
        //padre
        printf("padre (%d) non dorme", getpid());
        sleep(20);
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
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
// creare il programma con parent e child
// lascio lo spazio cosi non vedrete subito la soluzione

























int main()
{
    printf("--- inizio del programma\n");    
    pid_t pid =fork();// uso la fork 
    int statofiglio;
    if(pid == 0)
    {
        //child
        
        printf("child process: %d\n", (int) getpid());
        
    }
    else if (pid > 0) // parent
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
    
    wait(&statofiglio);

    return 0;
}
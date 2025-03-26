#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include <time.h>
// parent manda il proprio pid al c1 e c1 lo inoltra al c2.
// lascio lo spazio cosi non vedrete subito la soluzione





















int main()
{
    printf("--- inizio del programma\n");
    // dichiaro qui le pipe poi che c1 richiede acesso a tutte e 2 pipe
    int fd[2]; // pipe di comunicazione da parent a c1
    int buffer;
    int pd[2]; // pipe di comunicazione da c1 a c2
    int buf;
    if (pipe(pd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    } 
    if (pipe(fd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }   
    pid_t pid =fork();
    int statofiglio;
    if(pid == 0)//c1
    {
        //child
        printf("child 1: %d\n", (int) getpid());
        close(fd[1]);
        read(fd[0], &buffer, sizeof(buffer));
        printf("child 1: ha ricevuto il pid del parent\n");
        close(pd[0]);
        write(pd[1], &buffer, sizeof(buffer));
        printf("child 1: ha mandato il pid del parent a child 2\n");
        
    }
    else if (pid > 0)
    {
        pid_t ppid =fork();
        if(ppid == 0) //c2
            {
                //child2
                printf("child 2: %d\n", (int) getpid());
                close(pd[1]);
                read(pd[0], &buf, sizeof(buf));
                printf("child 2: ha ricevuto il pid del parent: %d\n",buf);
                
            }
            else if (pid > 0) // parent
            {
                printf("parent: %d\n", (int) getpid());
                close(fd[0]);
                int p_pid = (int) getpid();
                write(fd[1], &p_pid, sizeof(p_pid));  

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
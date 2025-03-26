#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
// creare una struttura ad albero usando le fork e quindi disegnare l'albero
// lascio lo spazio cosi non vedrete subito la soluzione



























int main()
{
    printf("--- inizio del programma\n");    
    pid_t pid;
    printf("pid del padre originale : %d\n",(int) getpid());
    for (size_t i = 0; i < 3; i++)
    {
        fork();
        printf("i=%d) pid = %d == ppid %d\n", i, (int) getpid(), (int) getppid());
        sleep(2);
    }
    
    

    printf("\n---fine programma");
    return 0;
}
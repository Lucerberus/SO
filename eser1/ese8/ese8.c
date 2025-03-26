#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include <time.h>
// parent deve mandare una la stringa con il nome di un file "esempio.c" al child e ques'ultimo lo deve 
// rinominare il file a piacere
// lascio lo spazio cosi non vedrete subito la soluzione





















#define MAX 19
int main()
{
    printf("--- inizio del programma\n");    
    int fd[2];
    char buffer[MAX];
    char *nome_file="esempio.c";
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
        //chiudo la scrittura nel child poiche deve ricever il dato
        close(fd[1]);
        read(fd[0], &buffer, sizeof(buffer));
        //ricevuto
        printf("child : nome file ricevuto : %s\n",buffer);
        // uso execlp per camviare il nome
        // int execlp(const char *file, const char *arg, ..., (char *) NULL);
        // il comando mv serve a rinominare i file e a spostarli, 
        // mv file_vecchio.txt file_nuovo.txt <-- cosi si chiambia il nome del file
        execlp("mv", "mv", buffer, "pippo_con_pluto.c", (char *)NULL);
        // in questo caso ho usato execlp con il comnado linux mv che serve a rinominare i file
        // 1) "mv" è il percorso del programma da eseguire, in questo caso è un programma di sistema quindi il suo percorso
        //     nel path
        // 2) "mv" è di nuovo il nome del programma
        // 3) buffer è la varibile contenente il il nome del file al quale bisogna cambiare il nome
        // 4) "pippo_con_pluto.c" è il nuovo nome del file
        // 5)  (char *)NULL segna la fine dei parametri
    }
    else if (pid > 0) // parent
    {   
        printf("parent : %d\n", (int) getpid());
        //chiudo la lettura al parent poiche deve solo inviare
        close(fd[0]);
        //invio il nome_file al child
        write(fd[1], nome_file, strlen(nome_file)+1); // con le stringhe non ce bisono di passare l'indirizzo di memoria
        printf("parent : nome del file inviato\n");

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
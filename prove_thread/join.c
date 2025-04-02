#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

/*nel comando gcc bisogna aggiungere  -lpthread
es: gcc join.c -o join -lpthread

*/

struct print_params
{
    char chr;
    int t;
};

void* print_char(void* p)
{
    struct print_params* pp =(struct print_params*) p;
    for (size_t i = 0; i<=pp->t; i++)
    {
        printf("%c", pp->chr);
        fflush(stdout);
        sleep(1);
    }
    return NULL;  
}

int main()
{
    pthread_t t1_id;
    pthread_t t2_id;

    struct print_params t1_args;
    struct print_params t2_args;

    t1_args.chr='X';
    t1_args.t=20;

    t2_args.chr='0';
    t2_args.t=20;

    pthread_create (&t1_id, NULL, &print_char, &t1_args);
    pthread_create (&t2_id, NULL, &print_char, &t2_args);

    pthread_join(t1_id, NULL);
    pthread_join(t2_id, NULL);

    return 0;
}
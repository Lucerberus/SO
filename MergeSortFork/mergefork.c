#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include <time.h>

void merge(int arr[], int left, int mid, int right) {
    int i, j, k;
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int L[n1], R[n2];

    for (i = 0; i < n1; i++)
        L[i] = arr[left + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[mid + 1 + j];

    i = 0;
    j = 0;
    k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

void mergeSort(int arr[], int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        
        int pipe1[2], pipe2[2];
        pipe(pipe1);
        pipe(pipe2);
        
        pid_t pid1 = fork();
        if (pid1 == 0) {
            printf("Processo figlio 1 (PID %d) ordinando la parte sinistra(parent:%d)\n", getpid(), getppid());
            sleep(1);
            close(pipe1[0]);
            mergeSort(arr, left, mid);
            write(pipe1[1], &arr[left], (mid - left + 1) * sizeof(int));
            close(pipe1[1]);
            printf("Processo figlio 1 (PID %d) terminato(parent:%d)\n", getpid(), getppid());
            exit(0);
        }
        
        pid_t pid2 = fork();
        if (pid2 == 0) {
            printf("Processo figlio 2 (PID %d) ordinando la parte destra(parent:%d)\n", getpid(), getppid());
            sleep(1);
            close(pipe2[0]);
            mergeSort(arr, mid + 1, right);
            write(pipe2[1], &arr[mid + 1], (right - mid) * sizeof(int));
            close(pipe2[1]);
            printf("Processo figlio 2 (PID %d) terminato(parent:%d)\n", getpid(), getppid());
            exit(0);
        }
        
        wait(NULL);
        wait(NULL);
        
        close(pipe1[1]);
        close(pipe2[1]);
        read(pipe1[0], &arr[left], (mid - left + 1) * sizeof(int));
        read(pipe2[0], &arr[mid + 1], (right - mid) * sizeof(int));
        close(pipe1[0]);
        close(pipe2[0]);
        
        printf("Processo padre (PID %d) eseguendo il merge\n", getpid());
        sleep(1);
        merge(arr, left, mid, right);
        printf("Processo padre (PID %d) ha completato il merge\n", getpid());
    }
}



void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++)
        printf("%d] %d \n", i, arr[i]);
    printf("\n");
}

int main() {
    srand(time(NULL));
    int arr_size=10;
    int min = 0, max = 20;
    int arr[arr_size];

    for (int i = 0; i < arr_size; i++)
    {
        arr[i]= min + rand() % (max - min + 1);
    }
    

    printf("Array iniziale: \n");
    printArray(arr, arr_size);

    mergeSort(arr, 0, arr_size - 1);

    printf("Array ordinato: \n");
    printArray(arr, arr_size);
    return 0;
}
/*
esempio: 
Array iniziale: 
0] 0
1] 3
2] 13
3] 16
4] 9
5] 9
6] 3
7] 5
8] 16
9] 5 
PID 1737 (root)
├─ PID 1738 (figlio 1, sinistra)
│   ├─ PID 1740 (figlio 1, sinistra)
│   │   ├─ PID 1744 (figlio 1, sinistra)
│   │   │   ├─ PID 1752 (figlio 1, sinistra) → termina
│   │   │   └─ PID 1754 (figlio 2, destra) → termina
│   │   │   → merge(1744)
│   │   └─ PID 1747 (figlio 2, destra) → termina
│   │   → merge(1740)
│   ├─ PID 1741 (figlio 2, destra)
│   │   ├─ PID 1746 (figlio 1, sinistra) → termina
│   │   └─ PID 1749 (figlio 2, destra) → termina
│   │   → merge(1741)
│   → merge(1738)
└─ PID 1739 (figlio 2, destra)
    ├─ PID 1742 (figlio 1, sinistra)
    │   ├─ PID 1745 (figlio 1, sinistra)
    │   │   ├─ PID 1753 (figlio 1, sinistra) → termina
    │   │   └─ PID 1755 (figlio 2, destra) → termina
    │   │   → merge(1745)
    │   └─ PID 1748 (figlio 2, destra) → termina
    │   → merge(1742)
    └─ PID 1743 (figlio 2, destra)
        ├─ PID 1750 (figlio 1, sinistra) → termina
        └─ PID 1751 (figlio 2, destra) → termina
        → merge(1743)
    → merge(1739)
→ merge(1737)

array finale:
0] 0
1] 3
2] 3
3] 5
4] 5
5] 9
6] 9
7] 13
8] 16
9] 16
*/
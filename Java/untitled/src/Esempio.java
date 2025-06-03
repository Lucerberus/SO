public class Esempio extends Thread
{
    private int[] A;
    private int a,b;
    private int max;

    public Esempio(int[] A, int a, int b)
    {
        this.A=A;
        this.a=a;
        this.b=b;
    }
    public void run()
    {
        max=A[a];
        for(int i=a+1; i<b; i++)
        {
            if(A[i]>max)
                max=A[i];
        }
    }
    public static int getMax(int[] A) throws InterruptedException {
        int len = A.length;
        int mass;

        Esempio t1 = new Esempio(A, 0, len/2);
        System.out.println("il thread 1 calcola il massimo dei valori " +
                            "in posizione da: 1 a" + len/2);

        Esempio t2 = new Esempio(A,len/2, len);
        System.out.println("il thread 1 calcola il massimo dei valori " +
                "in posizione da:"+len/2+" a " + len);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Massimo thread1 = "+t1.max);
        System.out.println("Massimo thread2 = "+t2.max);

        if(t1.max > t2.max)
            mass=t1.max;
        else
            mass=t2.max;

        return mass;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];

        for(int i=0; i< arr.length; i++)
        {
            arr[i] = i;
        }

        int max = getMax(arr);
        System.out.println("Massimo = " +max);
    }
}

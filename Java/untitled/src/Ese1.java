public class Ese1 {
    private int[] A;
    private int a,b;
    private int max;

    public Ese1(int[] A, int a, int b)
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
    public static int getSomma(int[] A) throws InterruptedException {
        int len = A.length;
        int somma =0;

        return somma;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];

        for(int i=0; i< arr.length; i++)
        {
            arr[i] = i;
        }

        int Somma = getSomma(arr);
        System.out.println("Somma  = " +Somma);
    }
}

import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        Random r = new Random();
        Pizzeria pizzeria = new Pizzeria(r.nextInt(3)+1,r.nextInt(2)+1,r.nextInt(1)+1);
        System.out.println(pizzeria.toString());
        for(int i=0;i<10;i++)
        {
            new Cliente(r.nextInt(10)+1,i,pizzeria).start();
        }
        for(int i=0;i<3;i++)
        {
            new Cameriere(pizzeria,i).start();
        }

    }
}
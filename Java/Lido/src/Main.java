import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random r = new Random();
        int N_postazioni = 10;
        int n_abbonamenti = 4;
        Lido lido = new Lido(N_postazioni, n_abbonamenti);

        Biglietteria b1 = new Biglietteria(1, lido);
        b1.start();
        int c_abbonati = 0;
        for(int i =0; i<N_postazioni; i++)
        {
            boolean abbonato = r.nextBoolean();
            if(c_abbonati < n_abbonamenti && abbonato)
            {
                c_abbonati++;
                Clienti c = new Clienti(i+1, r.nextInt(3)+1, abbonato, lido);
                c.start();
            }else
            {
                Clienti c = new Clienti(i+1, r.nextInt(3)+1, false, lido);
                c.start();
            }

        }
        System.out.println("Numero abbonati: " + c_abbonati);
    }
}
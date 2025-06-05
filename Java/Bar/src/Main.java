import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        Bar baretto = new Bar();
        Gelabarman bro = new Gelabarman(156, 1.20, 2.80,2.10, baretto);
        bro.start();
        Random random=new Random();
        int numCliente = 20 + random.nextInt(50 - 20 + 1);
        List<Cliente> lista_clienti = new ArrayList<>();
        for (int id = 1; id <= numCliente; id++)
        {
            int tipoGelato = 1 + random.nextInt(2 - 1 + 1);
            Cliente c = new Cliente(id, random.nextBoolean(), random.nextBoolean(),tipoGelato, baretto);
            c.start();
            lista_clienti.add(c);
        }
    }
}
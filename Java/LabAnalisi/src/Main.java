import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Laboratorio lab = new Laboratorio();
        List<Analista> listaAnalisti = new ArrayList<>();
        List<Cliente> listaClienti = new ArrayList<>();
        Random r = new Random();
        int n_Analisti = r.nextInt(10)+4;
        int n_clienti = r.nextInt(101)+50;

        for (int i = 0; i < n_Analisti; i++) {
            listaAnalisti.add(new Analista(i+1, lab));
        }
        for (int i = 0; i < n_clienti; i++) {
            listaClienti.add(new Cliente(i+1, r.nextInt(2)+1, lab));
        }
        for (Analista a : listaAnalisti) {
            a.start();
        }
        for (Cliente c : listaClienti) {
            c.start();
        }
    }
}
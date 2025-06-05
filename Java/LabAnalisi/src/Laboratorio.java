import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Laboratorio
{
    private LinkedList<Cliente> prenotazioni = new LinkedList<Cliente>();
    private LinkedList<Cliente> salaS = new LinkedList<Cliente>();
    private Random r = new Random();

    public synchronized void in_fila(Cliente c)
    {
        System.out.println("E arrivato un nuovo "+c.toString()+", si è messo in fila");
        prenotazioni.add(c);
        notifyAll();
    }

    public synchronized void ServiCliente(Analista a) throws InterruptedException {
        Cliente c=null;
        if(!salaS.isEmpty() && a.getN_clienti_S() >0)
        {
            Iterator<Cliente> it = salaS.iterator();
            while (it.hasNext())
            {
                c = it.next();
                if (c.getId_Analista() == a.getid()) {
                    it.remove();
                    break;
                }
            }
            a.Decr_n_clienti_S();
            System.out.println("Il "+c.toString()+" è stato servito");
            // Il random deve essere tra 10-30 secondi
            Thread.sleep((r.nextInt(15)+5) * 1000);//il thread si ferma tra 5-14 secondi
            System.out.println("Il "+c.toString()+" ha pagato");
            notifyAll();
        } else if ( !prenotazioni.isEmpty())
        {
            c=prenotazioni.removeFirst();
            System.out.println(a.toString()+" sta iniziando a servire il "+c.toString());
            Thread.sleep((r.nextInt(31)+10) * 1000);//il thread si ferma tra 10-30 secondi
            if(c.getTipo_analisi()==2)
            {
                a.Incr_n_clienti_S();
                c.setId_Analista(a.getid());
                salaS.add(c);
                System.out.println(a.toString()+" ha terminato di servire il "+c.toString()+", il cliente è stato posto in sala");
                notifyAll();
            }else
            {
                System.out.println(a.toString()+" ha terminato di servire il "+c.toString()+" il cliente sta per pagare");
                Thread.sleep((r.nextInt(10)) * 1000);
                notifyAll();
            }

        }else
        {
            System.out.println("Nessun cliente in fila");
            System.out.println(a.toString()+" Attende un nuovo cliente");
            wait();
        }
    }
}

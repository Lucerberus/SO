import java.lang.ref.Cleaner;
import java.util.Iterator;
import java.util.LinkedList;

public class Laboratorio
{
    private LinkedList<Cliente> prenotazioni = new LinkedList<Cliente>();
    private LinkedList<Cliente> salaS = new LinkedList<Cliente>();

    public synchronized void in_fila(Cliente c)
    {
        System.out.print("E arrivato un nuovo "+c.toString()+", si è messo in fila");
        prenotazioni.add(c);
    }

    public synchronized void ServiCliente(Analista a)
    {
        Cliente c;
        if(!salaS.isEmpty() && a.getN_clienti_S() >0)
        {
            Iterator<Cliente> it = salaS.iterator();
            while (it.hasNext())
            {
                c = it.next();
                if (c.getId_Analista() == a.getId()) {
                    it.remove();
                    break;
                }
            }

        }
    }
}

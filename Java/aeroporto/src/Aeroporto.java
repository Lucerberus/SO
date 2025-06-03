import java.util.LinkedList;

public class Aeroporto
{
    private LinkedList<Integer> priorita = new LinkedList<Integer>();
    private LinkedList<Integer> self = new LinkedList<Integer>();//1.76€/L
    private LinkedList<Integer> servito = new LinkedList<Integer>();//2.01€/L
    private int imbarcato;
    private static int c=10;

    private synchronized void in_coda(int id, boolean prio, int n_pers, int bagalio)//solo passeggeri
    {
        System.out.println("Arriva il passegero con\nid: "+id+"\nNumero persone: "+n_pers+"\nBagalio: "+bagalio);
        if(prio)
        {

            priorita.add(id);
            System.out.println("Il passegero con id: "+id+" si è messo in coda con priorità\n" +
                    "passegeri in coda con priorità: "+priorita.size());
            notifyAll();
        } else if (n_pers > 1)
        {
            servito.add(id);
            System.out.println("Il passegero con id: "+id+" si è messo in coda con priorità\n" +
                    "passegeri in coda con priorità: "+servito.size());
            notifyAll();
        } else if (bagalio == 2)
        {
            servito.add(id);
            System.out.println("Il passegero con id: "+id+" si è messo in coda con priorità\n" +
                    "passegeri in coda con priorità: "+servito.size());
            notifyAll();
        }else
        {

            self.add(id);
            System.out.println("Il passegero con id: "+id+" si è messo in coda con priorità\n" +
                    "passegeri in coda con priorità: "+self.size());
            notifyAll();
        }
    }

    public synchronized void serviPasseggero(int id_Pers, boolean self)
    {
        if(self)
        {
            //serve la fila self
        }else
        {
            //serve le file priority e servito


        }
    }


}

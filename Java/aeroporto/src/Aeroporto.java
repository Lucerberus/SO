import java.util.LinkedList;

public class Aeroporto
{
    private LinkedList<Passeggeri> priorita = new LinkedList<Passeggeri>();
    private LinkedList<Passeggeri> self = new LinkedList<Passeggeri>();//1.76€/L
    private LinkedList<Passeggeri> servito = new LinkedList<Passeggeri>();//2.01€/L
    private int imbarcato;
    private static int c=10;

    public synchronized void in_coda(Passeggeri p)//solo passeggeri
    {
        System.out.println("Arriva il passegero con\nid: "+p.getId()+"\nNumero persone: "+p.getNperosne()+"\nBagalio: "+p.getDim_bagaglio());
        if (p.getNperosne() > 1)
        {
            priorita.add(p);
            System.out.println("Il passegero con id: "+p.getId()+" si è messo in coda con priorità\n" +
                    "passegeri in coda con priorità: "+priorita.size());

        } else if (p.getDim_bagaglio() == 2)
        {
            servito.add(p);
            System.out.println("Il passegero con id: "+p.getId()+" si è messo in coda con servito\n" +
                    "passegeri in coda con priorità: "+servito.size());

        }else
        {

            self.add(p);
            System.out.println("Il passegero con id: "+p.getId()+" si è messo in coda con self\n" +
                    "passegeri in coda con priorità: "+self.size());
        }
        notifyAll();
    }

    public synchronized void serviPasseggero(Postazioni pos)
    {
        if(pos.isSelfDrop())
        {
            //serve la fila self
        }else
        {
            //serve le file priority e servito


        }
    }


}

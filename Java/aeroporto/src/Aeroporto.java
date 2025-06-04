import java.util.LinkedList;

public class Aeroporto
{
    private LinkedList<Passeggeri> priorita = new LinkedList<Passeggeri>();
    private LinkedList<Passeggeri> self = new LinkedList<Passeggeri>();//1.76€/L
    private LinkedList<Passeggeri> servito = new LinkedList<Passeggeri>();//2.01€/L
    private Passeggeri imbarcato;
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

    public synchronized void serviPasseggero(Postazioni pos) throws InterruptedException {
        if(pos.isSelfDrop())
        {
            if(self.isEmpty())
            {
                System.out.println("la coda self è vuota, postazione in attesa");
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else
            {
                System.out.println("c'è qualcuno in coda prelevo il primo mal capitato");
                imbarcato = self.removeFirst();
                Thread.sleep((int) (Math.random() * 100));
                System.out.println("La postazione "+pos.getId()+" ha servito il passeggero: " + imbarcato.getId() +"dalla coda self");
            }
        }else
        {
            if(priorita.isEmpty() && servito.isEmpty())
            {
                System.out.println("entrambe code sono vuote");
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else
            {
                if(priorita.isEmpty())
                {
                    System.out.println("la coda priorità è vuota, controllo coda servito");
                }else// c'è qualcuno in priority
                {
                    System.out.println("c'è qualcuno in coda prelevo il primo mal capitato");
                    imbarcato = priorita.removeFirst();
                    Thread.sleep((int) (Math.random() * 100));
                    System.out.println("La postazione "+pos.getId()+" ha servito il passeggero: " + imbarcato.getId()+"dalla coda priorita");
                    notify();
                }

                if(!servito.isEmpty())
                {
                    System.out.println("c'è qualcuno in coda servito prelevo il primo mal capitato");
                    imbarcato = servito.removeFirst();
                    Thread.sleep((int) (Math.random() * 100));
                    System.out.println("La postazione "+pos.getId()+" ha servito il passeggero: " + imbarcato.getId()+"dalla coda servito");
                    notify();
                }
            }
        }
    }


}

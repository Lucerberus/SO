import java.util.LinkedList;

public class Aeroporto
{
    // ecco le 3 code per ogni tipo di passegere
    private LinkedList<Passeggeri> priorita = new LinkedList<Passeggeri>();
    private LinkedList<Passeggeri> self = new LinkedList<Passeggeri>();//1.76€/L
    private LinkedList<Passeggeri> servito = new LinkedList<Passeggeri>();//2.01€/L
    private Passeggeri imbarcato;
    private int c=0;// mi serve a gestire la concorreza

    public synchronized void in_coda(Passeggeri p)//solo passeggeri
    {
        System.out.println("Arriva il "+p.toString());
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
                if(!priorita.isEmpty() && c <=5) {

                    System.out.println("c'è qualcuno in coda prelevo il primo mal capitato");
                    imbarcato = priorita.removeFirst();
                    Thread.sleep((int) (Math.random() * 100));
                    System.out.println("La postazione " + pos.getId() + " ha servito il passeggero: " + imbarcato.getId() + "dalla coda priorita");
                    notifyAll();
                    c++;
                } else if (c > 6)
                {
                    c=0;
                }

                if(!servito.isEmpty())
                {
                    System.out.println("c'è qualcuno in coda servito prelevo il primo mal capitato");
                    imbarcato = servito.removeFirst();
                    Thread.sleep((int) (Math.random() * 100));
                    System.out.println("La postazione "+pos.getId()+" ha servito il passeggero: " + imbarcato.getId()+"dalla coda servito");
                    notifyAll();
                }
            }
        }
    }


}

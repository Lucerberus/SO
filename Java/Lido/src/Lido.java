import java.util.LinkedList;
import java.util.Random;

public class Lido
{
    private final int N_postazioni;
    private int n_abbonamenti;
    private int postazioni_disponibili_occasionali;

    private LinkedList<Clienti> Postazioni = new LinkedList<>();
    private LinkedList<Clienti> CodaAbbonati = new LinkedList<>();
    private LinkedList<Clienti> CodaOccasionali = new LinkedList<>();



    Random r = new Random();

    public Lido(int N_postazioni, int n_abbonamenti)
    {
        this.N_postazioni = N_postazioni;
        if(N_postazioni/2 > n_abbonamenti)
            this.n_abbonamenti = n_abbonamenti;
        else
        {

            this.n_abbonamenti = (int) (N_postazioni / 2) - 1;
            System.out.println("Numero abbonamenti non valido, ecco il numero di abbonamenti che verranno creati: " + this.n_abbonamenti);
        }
        this.postazioni_disponibili_occasionali = N_postazioni - n_abbonamenti*2;
    }

    public synchronized void in_coda(Clienti c)
    {
        if(c.isAbbonamento())
        {
            System.out.println("È arrivato un nuovo "+c.toString()+" abbonato, si è messo in CodaAbbonati");
            CodaAbbonati.add(c);
        }else
        {
            System.out.println("È arrivato un nuovo "+c.toString()+" Occasionale, si è messo in CodaOccasionali");
            CodaOccasionali.add(c);
        }
        notifyAll();
    }
    
    public synchronized void servi_clienti(Biglietteria b)
    {
        final Clienti cliente;
        if(CodaAbbonati.isEmpty() && CodaOccasionali.isEmpty())//controllo se le code sono vuote se si mi metto in attesa
        {
            System.out.println("Non ci sono clienti da servire, biglietteria in attesa");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else if(!CodaAbbonati.isEmpty())
        {
            cliente = CodaAbbonati.removeFirst();
            if(cliente.getNumeroGruppo() >2)
            {
                System.out.println(b.toString()+" sta servendo un cliente abbonato, id: "+cliente.toString());
                Postazioni.add(cliente);
                Clienti cliente2 = new Clienti(cliente.getId_secondoGrupppo(), cliente.getNumeroGruppo(), cliente.isAbbonamento(), this);
                Postazioni.add(cliente2);
                new Thread(()-> gestionePermanenza(cliente)).start();
                new Thread(()-> gestionePermanenza(cliente2)).start();
                System.out.println(cliente.toString()+" ha occupato due postazioni");
                notifyAll();
            }else
            {
                System.out.println(b.toString()+" sta servendo un cliente abbonato, id: "+cliente.toString());
                Postazioni.add(cliente);
                new Thread(()-> gestionePermanenza(cliente)).start();
                System.out.println(cliente.toString()+" ha occupato una postazione");
                notifyAll();
            }
        }else
        {
            cliente = CodaOccasionali.removeFirst();
            System.out.println(b.toString()+" sta servendo un cliente occasionalmente, id: "+cliente.toString());
            if(cliente.getNumeroGruppo() >2 && postazioni_disponibili_occasionali >= 2)
            {
                Postazioni.add(cliente);
                Clienti cliente2 = new Clienti(cliente.getId_secondoGrupppo(), cliente.getNumeroGruppo(), cliente.isAbbonamento(), this);
                Postazioni.add(cliente2);
                new Thread(()-> gestionePermanenza(cliente)).start();
                new Thread(()-> gestionePermanenza(cliente2)).start();
                System.out.println(cliente.toString()+" Occasionale ha occupato due postazioni");
                postazioni_disponibili_occasionali-=2;
                notifyAll();
            } else if (cliente.getNumeroGruppo() <3 && postazioni_disponibili_occasionali >= 1)
            {
                Postazioni.add(cliente);
                new Thread(()-> gestionePermanenza(cliente)).start();
                System.out.println(cliente.toString()+" Occasionale ha occupato una postazione");
                postazioni_disponibili_occasionali-=1;
                notifyAll();
            }else
            {
                System.out.println("Non ci sono postazioni disponibili, "+cliente.toString()+" se va");
                notifyAll();
            }
        }
    }

    public synchronized void gestionePermanenza(Clienti c)
    {
        int tempoPermanenza = 30000 + r.nextInt(30001); // 30-60 secondi
        try{
            System.out.println("il "+c.toString()+" sta usando postazione");
            Thread.sleep(tempoPermanenza);
            Postazioni.remove(c);
            if(!c.isAbbonamento())
                postazioni_disponibili_occasionali++;
            System.out.println(c+"ha lasciato la postazione");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
    }

}

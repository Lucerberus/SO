import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pizzeria
{
    private List<Cliente> tavolo2 = new ArrayList<Cliente>();
    private List<Cliente> tavolo4 = new ArrayList<Cliente>();
    private List<Cliente> tavolo6 = new ArrayList<Cliente>();
    private List<Cliente> tavolo20 = new ArrayList<Cliente>();

    private int nTavolo2;
    private int nTavolo4;
    private int nTavolo6;
    private int NumeroClientiTavolo20;

    private Random r = new Random();

    public Pizzeria(int nTavolo2, int nTavolo4, int nTavolo6)
    {
        this.nTavolo2 = nTavolo2;
        this.nTavolo4 = nTavolo4;
        this.nTavolo6 = nTavolo6;
        NumeroClientiTavolo20 = 20;
    }

    public synchronized boolean in_line(Cliente cliente)
    {
        boolean seduto=false;
        System.out.println("È arrivato il"+cliente.toString());
        if(cliente.getGruppo() <3)
        {
            if(tavolo2.size() <= nTavolo2)
            {
                tavolo2.add(cliente);
                notifyAll();
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 2");
                seduto=true;
            }else if(tavolo4.size() <= nTavolo4)
            {
                tavolo4.add(cliente);
                notifyAll();
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 4");
                seduto=true;
            }else if(tavolo6.size() <= nTavolo6)
            {
                tavolo6.add(cliente);
                notifyAll();
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 6");
                seduto=true;
            }else
            {
                if(NumeroClientiTavolo20 >= 2)
                {
                    tavolo20.add(cliente);
                    notifyAll();
                    seduto=true;
                    System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 20 occuppando "+cliente.getGruppo()+" posti ");
                    NumeroClientiTavolo20-= cliente.getGruppo();
                    System.out.println("Il tavolo da 20 ha "+NumeroClientiTavolo20+" posti rimasti");
                }
            }
        } else if ( cliente.getGruppo() < 5)
        {
            if(tavolo4.size() <= nTavolo4)
            {
                tavolo4.add(cliente);
                notifyAll();
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 4");
                seduto=true;
            }else if(tavolo6.size() <= nTavolo6)
            {
                tavolo6.add(cliente);
                notifyAll();
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 6");
                seduto=true;
            }else
            {
                if(NumeroClientiTavolo20 >= 4)
                {
                    tavolo20.add(cliente);
                    notifyAll();
                    seduto=true;
                    System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 20 occuppando "+cliente.getGruppo()+" posti ");
                    NumeroClientiTavolo20-= cliente.getGruppo();
                    System.out.println("Il tavolo da 20 ha "+NumeroClientiTavolo20+" posti rimasti");
                }
            }
        }else if ( cliente.getGruppo() < 7)
        {
            if(tavolo6.size() <= nTavolo6)
            {
                tavolo6.add(cliente);
                notifyAll();
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 6");
                seduto=true;
            }else
            {
                if(NumeroClientiTavolo20 >= 6)
                {
                    tavolo20.add(cliente);
                    notifyAll();
                    seduto=true;
                    System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 20 occuppando "+cliente.getGruppo()+" posti ");
                    NumeroClientiTavolo20-= cliente.getGruppo();
                    System.out.println("Il tavolo da 20 ha "+NumeroClientiTavolo20+" posti rimasti");
                }
            }
        }else if ( cliente.getGruppo() < 11)
        {
            if(NumeroClientiTavolo20 >= 10)
            {
                tavolo20.add(cliente);
                notifyAll();
                seduto=true;
                System.out.println("Il"+cliente.toString()+" si è seduto sul tavolo da 20 occuppando "+cliente.getGruppo()+" posti ");
                NumeroClientiTavolo20-= cliente.getGruppo();
                System.out.println("Il tavolo da 20 ha "+NumeroClientiTavolo20+" posti rimasti");
            }
        }
        if (!seduto)
            System.out.println("Il "+cliente.toString()+" si è messo fuori ad aspettare che un tavolo si liberi");

        return seduto;
    }

    public synchronized void servi(Cameriere cameriere)
    {   Cliente cliente;
        while(tavolo2.isEmpty() && tavolo4.isEmpty() && tavolo6.isEmpty() && tavolo20.isEmpty())
        {
            System.out.println("Non ci sono i clienti, il "+cameriere.toString()+" si mette in attesa");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(!tavolo2.isEmpty())
        {
            cliente = tavolo2.removeFirst();
            System.out.println("Il "+cameriere.toString()+" sta servendo il "+cliente.toString());
            try {
                Thread.sleep(r.nextInt(10000)+1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Il "+cameriere.toString()+" ha finito di  servire il "+cliente.toString());
            notifyAll();

        }else if(!tavolo4.isEmpty())
        {
            cliente = tavolo4.removeFirst();
            System.out.println("Il "+cameriere.toString()+" sta servendo il "+cliente.toString());
            try {
                Thread.sleep(r.nextInt(10000)+1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Il "+cameriere.toString()+" ha servito il "+cliente.toString());
            notifyAll();
        } else if (!tavolo6.isEmpty())
        {
            cliente = tavolo6.removeFirst();
            System.out.println("Il "+cameriere.toString()+" sta servendo il "+cliente.toString());
            try {
                Thread.sleep(r.nextInt(10000)+1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Il "+cameriere.toString()+" ha servito il "+cliente.toString());
            notifyAll();
        }else
        {
            cliente = tavolo20.removeFirst();
            System.out.println("Il "+cameriere.toString()+" sta servendo il "+cliente.toString());
            try {
                Thread.sleep(r.nextInt(10000)+1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Il "+cameriere.toString()+" ha servito il "+cliente.toString());
            notifyAll();
            NumeroClientiTavolo20+=cliente.getGruppo();
        }


    }

    @Override
    public String toString() {
        return "Pizzeria{" +
                "nTavoli2=" + nTavolo2 +
                ", nTavoli4=" + nTavolo4 +
                ", nTavoli6=" + nTavolo6 +
                '}';
    }
}

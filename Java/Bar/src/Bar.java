
import java.util.LinkedList;

public class Bar
{
    private LinkedList<Cliente> Caffe = new LinkedList<Cliente>();
    private LinkedList<Cliente> GelatoBrioche = new LinkedList<Cliente>();
    private LinkedList<Cliente> GelatoCono = new LinkedList<Cliente>();
    private LinkedList<Cliente> GelatoCaffe = new LinkedList<Cliente>();
    private LinkedList<Cliente> Cassa = new LinkedList<Cliente>();


    public synchronized void in_line(Cliente c)
    {   //il cliente usa il metodo in_line
        if(c.isCaffe() && c.isGelato()) // controllo se è il caso particolare,
                                        // che il cliente vuole prendere uno e altro
        {
            if(c.getTipo_gelato() < 2)//se è vero quindi
            {
                System.out.println("Un nuovo " + c.toString() + " si messo in coda GelatoBrioche");
                GelatoBrioche.add(c);
                notifyAll();
            }else
            {
                System.out.println("Un nuovo " + c.toString() + " si messo in coda GelatoCono");
                GelatoCono.add(c);
                notifyAll();
            }
        }else
        {
            if(c.isCaffe())
            {
                System.out.println("Un nuovo " + c.toString() + " si messo in coda Caffe");
                Caffe.add(c);
                notifyAll();
            }else if (c.isGelato())
            {
                if(c.getTipo_gelato() < 2)
                {
                    System.out.println("Un nuovo " + c.toString() + " si messo in coda GelatoBrioche");
                    GelatoBrioche.add(c);
                    notifyAll();
                }else
                {
                    System.out.println("Un nuovo " + c.toString() + " si messo in coda GelatoCono");
                    GelatoCono.add(c);
                    notifyAll();
                }
            }
        }
    }

    public synchronized void serviCliente(Gelabarman p) throws InterruptedException {
        if(Caffe.isEmpty() && GelatoCaffe.isEmpty() && GelatoCono.isEmpty() && GelatoBrioche.isEmpty() && Cassa.isEmpty())
        {
            System.out.println("Non ce nessuno Il GelaBarman aspetta");
            wait();
        }
        //probabilmente farò qui un if qui che controlla se in cassa ci sono più di 8 persone
        // se si vado a svuotare la cassa prima
        if(Cassa.size() >= 8 || (Caffe.isEmpty() && GelatoCaffe.isEmpty() && GelatoCono.isEmpty() && GelatoBrioche.isEmpty() && !Cassa.isEmpty()))
        {
            int num_cliente = Cassa.size();
            for(int i = 0; i< num_cliente; i++)
            {
                Cliente c = Cassa.removeFirst();
                System.out.println("Il "+c.toString()+" paga un totale di: "+c.getTot());
                try {
                    Thread.sleep((int) Math.random() * 30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }else
        {
            if(!GelatoBrioche.isEmpty())
            {
                Cliente c = GelatoBrioche.removeFirst();
                System.out.println("Il Gelabarman sta servendo il "+c.toString()+"");
                c.IncrTot(p.getPrezzo_gelato_brioche());
                try {
                    Thread.sleep((int) Math.random() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(c.isCaffe())
                {
                    GelatoCaffe.add(c);
                    System.out.println("Il "+c.toString()+" vuole prendere anche il caffe, è adanto a mettersi in coda");
                }else
                {
                    Cassa.add(c);
                    System.out.println("Il "+c.toString()+" è andato a mettersi in cassa");
                }
            }
            if(!GelatoCono.isEmpty())
            {
                Cliente c = GelatoCono.removeFirst();
                System.out.println("Il Gelabarman sta servendo il "+c.toString()+"");
                c.IncrTot(p.getPrezzo_gelato_cono());

                try {
                    Thread.sleep((int) Math.random() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(c.isCaffe())
                {
                    GelatoCaffe.add(c);
                    System.out.println("Il cliente vuole prendere anche il caffe, è adanto a mettersi in coda");
                }else
                {
                    Cassa.add(c);
                    System.out.println("Il "+c.toString()+" è andato a mettersi in cassa");
                }
            }
            if(!GelatoCaffe.isEmpty())
            {
                Cliente c = GelatoCaffe.removeFirst();
                System.out.println("Il Gelabarman sta servendo il "+c.toString()+"");
                c.IncrTot(p.getPrezzo_caffe());
                try {
                    Thread.sleep((int) Math.random() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Cassa.add(c);
                System.out.println("Il "+c.toString()+" è andato a mettersi in cassa");
            }
            if(!Caffe.isEmpty())
            {
                Cliente c = Caffe.removeFirst();
                System.out.println("Il Gelabarman sta servendo il "+c.toString()+"");
                c.IncrTot(p.getPrezzo_caffe());
                try {
                    Thread.sleep((int) Math.random() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Cassa.add(c);
                System.out.println("Il "+c.toString()+" è andato a mettersi in cassa");
            }
        }
        notifyAll();
    }
}

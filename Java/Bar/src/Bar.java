
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

}

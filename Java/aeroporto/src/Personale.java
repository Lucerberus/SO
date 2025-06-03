public class Personale extends Thread
{
    private int id;
    private boolean self_drop;
    private Aeroporto airport;

    public Personale(int id, boolean self_drop, Aeroporto airport)
    {
        this.id = id;
        this.self_drop=self_drop;
        this.airport=airport;
    }

    public void run()
    {

    }


}

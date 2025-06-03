public class Passeggeri extends Thread
{
    private int Nperosne;
    private boolean priority;
    private int id;
    private int dim_bagaglio;

    private Aeroporto airport;


    public Passeggeri(int id,int Npersone, boolean priority, int dim_bagaglio, Aeroporto airport)
    {
        this.id=id;
        this.Nperosne= Npersone;
        this.priority = priority;
        this.dim_bagaglio=dim_bagaglio;
        this.airport=airport;
    }

    public void run()
    {

    }


}

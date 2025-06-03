public class Passeggeri extends Thread
{
    private int Nperosne;
    private int id;
    private int dim_bagaglio; // 1 = ordinario --- 2 = grande

    private Aeroporto airport;


    public Passeggeri(int id,int Npersone, boolean priority, int dim_bagaglio, Aeroporto airport)
    {
        this.id=id;
        this.Nperosne= Npersone;
        this.dim_bagaglio=dim_bagaglio;
        this.airport=airport;
    }

    public void run()
    {
        airport.in_coda(this);
    }
    // Getter methods with "this"
    public int getNperosne()

    {
        return this.Nperosne;
    }

    public long getId()
    {
        return this.id;
    }

    public int getDim_bagaglio()
    {
        return this.dim_bagaglio;
    }

    public Aeroporto getAirport()
    {
        return this.airport;
    }

}

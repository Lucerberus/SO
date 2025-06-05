public class Analista extends Thread
{
    private int id;
    private int n_clienti_S;

    public Analista (int id)
    {
        this.id=id;
        n_clienti_S=0;
    }

    @Override
    public void run()
    {

    }

    @Override
    public long getId() {
        return id;
    }

    public int getN_clienti_S() {
        return n_clienti_S;
    }
    public void Incr_n_clienti_S()
    {
        n_clienti_S+=1;
    }
    public void Decr_n_clienti_S()
    {
        if(getN_clienti_S() !=0)
            n_clienti_S-=1;
    }
}

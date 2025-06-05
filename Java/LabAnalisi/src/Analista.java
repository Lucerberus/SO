public class Analista extends Thread
{
    private int id;
    private int n_clienti_S;
    private Laboratorio lab;

    public Analista (int id, Laboratorio lab)
    {
        this.id=id;
        n_clienti_S=0;
        this.lab=lab;
    }

    @Override
    public void run()
    {
        while (true) {
            try {
                lab.ServiCliente(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public long getid() {
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
    @Override
    public String toString() {
        return "Analista{" +
                "id=" + id +
                '}';
    }
}

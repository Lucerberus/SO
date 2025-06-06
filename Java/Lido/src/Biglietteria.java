public class Biglietteria extends Thread
{
    private int id;
    private Lido lido;

    public Biglietteria(int id, Lido lido)
    {
        this.id = id;
        this.lido = lido;
    }

    public void run()
    {
        while (true)
        {
            lido.servi_clienti(this);
        }
    }


    public int getid()
    {
        return id;
    }

    @Override
    public String toString() {
        return "Biglietteria{" +
                "id=" + id +
                '}';
    }
}

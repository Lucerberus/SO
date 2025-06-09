public class Cameriere extends Thread
{
    private Pizzeria pizzeria;
    private int id;

    public Cameriere(Pizzeria pizzeria, int id)
    {
        this.pizzeria = pizzeria;
        this.id = id;
    }

    public void run()
    {
        while (true)
        {
            pizzeria.servi(this);
        }
    }

    public int getid() {
        return id;
    }

    @Override
    public String toString() {
        return "Cameriere{" +
                "id=" + id +
                '}';
    }
}

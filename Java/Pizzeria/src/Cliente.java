public class Cliente extends Thread
{
    private int gruppo;
    private int id;
    private Pizzeria pizzeria;

    public Cliente(int gruppo, int id, Pizzeria pizzeria)
    {
        this.gruppo = gruppo;
        this.id = id;
        this.pizzeria = pizzeria;
    }
    public void run()
    {
        while(true)
        {
            if(pizzeria.in_line(this))
            {
                break;
            }else
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int getGruppo() {
        return gruppo;
    }
    public int getid() {
        return id;
    }
    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", gruppo=" + gruppo +
                '}';
    }
}

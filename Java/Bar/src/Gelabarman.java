public class Gelabarman extends Thread
{
    private int id;
    private double prezzo_caffe;
    private double prezzo_gelato_brioche, prezzo_gelato_cono;
    private Bar baretto;

    public Gelabarman(int id, double prezzo_caffe, double prezzo_gelato_brioche, double prezzo_gelato_cono, Bar baretto)
    {
        this.id = id;
        this.baretto = baretto;
        this.prezzo_caffe = prezzo_caffe;
        this.prezzo_gelato_brioche = prezzo_gelato_brioche;
        this.prezzo_gelato_cono = prezzo_gelato_cono;
    }

    public void run()
    {
        while(true)
        {
            try {
                baretto.serviCliente(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public long getId() {
        return id;
    }

    public double getPrezzo_caffe() {
        return prezzo_caffe;
    }

    public double getPrezzo_gelato_brioche() {
        return prezzo_gelato_brioche;
    }

    public double getPrezzo_gelato_cono() {
        return prezzo_gelato_cono;
    }

}

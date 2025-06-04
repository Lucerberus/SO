public class Gelabarman extends Thread
{
    private int id;
    private double prezzo_caffe;
    private double prezzo_gelato;
    private Bar baretto;

    public Gelabarman(int id, double prezzo_caffe, double prezzo_gelato, Bar baretto)
    {
        this.id = id;
        this.baretto = baretto;
        this.prezzo_caffe = prezzo_caffe;
        this.prezzo_gelato = prezzo_gelato;
    }

    public void run()
    {

    }
    @Override
    public long getId() {
        return id;
    }

    public double getPrezzo_caffe() {
        return prezzo_caffe;
    }

    public double getPrezzo_gelato() {
        return prezzo_gelato;
    }
}

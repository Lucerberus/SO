public class Cliente extends Thread {
    private int id;
    private boolean caffe;
    private  boolean gelato;
    private int tipo_gelato; // 1 brioche 2 cono
    private double tot;

    public Cliente(int id, boolean caffe, boolean gelato, int tipo_gelato)
    {
        this.id = id;
        this.caffe = caffe;
        this.gelato = gelato;
        this.tipo_gelato = tipo_gelato;
        this.tot = 0.0;
    }

    public void run()
    {

    }
    public long getId() {
        return id;
    }

    public boolean isCaffe() {
        return caffe;
    }

    public boolean isGelato() {
        return gelato;
    }

    public int getTipo_gelato() {
        return tipo_gelato;
    }

    public double getTot() {
        return tot;
    }
    public void IncrTot(double somma)
    {
        this.tot += somma;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", caffe=" + caffe +
                ", gelato=" + gelato +
                ", tipo_gelato=" + tipo_gelato +
                ", tot=" + tot +
                '}';
    }
}

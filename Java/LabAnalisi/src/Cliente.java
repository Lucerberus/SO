import java.util.Random;

public class Cliente extends Thread {
    private int id;
    private int id_Analista;
    private int tipo_analisi; // 1 brioche 2 cono
    private Random r = new Random();
    private Laboratorio lab;

    public Cliente(int id, int tipo_analisi, Laboratorio lab)
    {
        this.id = id;
        this.tipo_analisi = tipo_analisi;
        id_Analista=-1;
        this.lab=lab;
    }

    public void run()
    {
        lab.in_fila(this);
    }
    public long getid() {
        return id;
    }

    public int getTipo_analisi() {
        return tipo_analisi;
    }

    public void setId_Analista(long id_Analista) {
        this.id_Analista = (int) id_Analista;
    }

    public int getId_Analista() {
        return id_Analista;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", id_Analista=" + id_Analista +
                ", tipo_analisi=" + tipo_analisi +
                '}';
    }
}

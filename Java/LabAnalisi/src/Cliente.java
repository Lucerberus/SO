public class Cliente extends Thread {
    private int id;
    private int id_Analista;
    private int tipo_analisi; // 1 brioche 2 cono


    public Cliente(int id, int tipo_analisi)
    {
        this.id = id;
        this.tipo_analisi = tipo_analisi;
    }

    public void run()
    {
        try {
            Thread.sleep((int) (Math.random() * 500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        };

    }
    public long getId() {
        return id;
    }

    public int getTipo_analisi() {
        return tipo_analisi;
    }

    public void setId_Analista(int id_Analista) {
        this.id_Analista = id_Analista;
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

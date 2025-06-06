public class Clienti extends Thread
{
    private int id;
    private int numero_gruppo;
    private boolean abbonamento;
    private Lido lido;
    private int id_secondoGrupppo;



    public Clienti(int id, int numero_gruppo, boolean abbonamento, Lido lido)
    {
        this.id = id;
        this.numero_gruppo = numero_gruppo;
        this.abbonamento = abbonamento;
        this.lido = lido;
        if( numero_gruppo >2)
            id_secondoGrupppo = id+10_000;
        else
            id_secondoGrupppo = -1;
    }
    public void run()
    {
        lido.in_coda(this);
    }
    public int getid()
    {
        return id;
    }

    public int getNumeroGruppo()
    {
        return numero_gruppo;
    }

    public boolean isAbbonamento()
    {
        return abbonamento;
    }

    public Lido getLido()
    {
        return lido;
    }
    public int getId_secondoGrupppo()
    {
        return id_secondoGrupppo;
    }
    public void setId_secondoGrupppo(int id_secondoGrupppo)
    {
        this.id = id_secondoGrupppo;
    }

    @Override
    public String toString()
    {
        return "Clienti{" +
                "id= " + id +
                ", numero_gruppo= " + numero_gruppo +
                ", abbonamento= " + abbonamento +
                ", id_secondoGrupppo= " + id_secondoGrupppo +
                ", lido= " + lido +
                '}';
    }
    
}

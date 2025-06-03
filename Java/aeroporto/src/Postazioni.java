public class Postazioni extends Thread {
    private int id;
    private boolean self_drop; // true se è self
    private Aeroporto airport;

    public Postazioni(int id, boolean self_drop, Aeroporto airport) {
        this.id = id;
        this.self_drop = self_drop;
        this.airport = airport;
    }

    public void run()
    {

    }

    public long getId() {
        return id;
    }

    public boolean isSelfDrop() {
        return self_drop;
    }

    public Aeroporto getAirport() {
        return airport;
    }
}

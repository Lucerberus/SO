import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    // Numero di postazioni totali in aeroporto
    private static final int N_POSTAZIONI = 10;
    // Numero minimo e massimo di passeggeri da creare
    private static final int MIN_PASSEGGERI = 20;
    private static final int MAX_PASSEGGERI = 50;

    public static void main(String[] args) {
        Random random = new Random();
        Aeroporto aeroporto = new Aeroporto();

        // 1) Creo le postazioni in modo casuale, metà assistite e metà self‐drop
        List<Postazioni> listaPostazioni = new ArrayList<>();
        for (int i = 0; i < N_POSTAZIONI; i++) {
            // Supponiamo che il costruttore Postazioni accetti un boolean:
            // true = postazione assistita, false = postazione self‐drop
            boolean assistita = (i < N_POSTAZIONI / 2);
            Postazioni p = new Postazioni(i + 1, assistita, aeroporto);
            listaPostazioni.add(p);
            p.start();
        }
        // 3) Decido un numero casuale di passeggeri tra MIN_PASSEGGERI e MAX_PASSEGGERI
        int numPasseggeri = MIN_PASSEGGERI + random.nextInt(MAX_PASSEGGERI - MIN_PASSEGGERI + 1);
        System.out.println("Creazione di " + numPasseggeri + " passeggeri...\n");

        // 4) Creo e avvio i thread dei passeggeri
        List<Thread> threadPasseggeri = new ArrayList<>();
        for (int id = 1; id <= numPasseggeri; id++) {
            // Ogni passeggero viene creato con un riferimento all'aeroporto
            // e a un bagaglio casuale: true = bagaglio grande (solo assistita), false = bagaglio ordinario
            int bagaglioGrande = 1 + random.nextInt(2 - 1 + 1);
            int npersone = 1 + random.nextInt(2 - 1 + 1);
            Passeggeri passeggero = new Passeggeri(id,npersone, bagaglioGrande, aeroporto);
            Thread t = new Thread(passeggero, "Passeggero-" + id);
            threadPasseggeri.add(t);
            t.start();
        }

        // 5) Attendo che tutti i thread passeggeri terminino
        for (Thread t : threadPasseggeri) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nTutti i passeggeri hanno completato il check‐in. Chiusura aeroporto.");
    }
}

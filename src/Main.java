import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Créer un tableau d'entiers
        int[] tableau = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Créer un pool de threads
        int nombreThreads = 4; // Par exemple, 4 threads
        ExecutorService pool = Executors.newFixedThreadPool(nombreThreads);

        // Diviser le travail et soumettre les tâches au pool
        int taillePartie = tableau.length / nombreThreads;
        int debut = 0;
        int fin = taillePartie;

        // Créer un tableau pour stocker les résultats des calculs de somme
        int[] sommesPartielles = new int[nombreThreads];

        for (int i = 0; i < nombreThreads; i++) {
            if (i == nombreThreads - 1) {
                fin = tableau.length; // Pour s'assurer que le dernier thread traite les éléments restants
            }
            Sommeur2 sommeur2 = new Sommeur2(tableau, debut, fin);
            pool.submit(sommeur2);
            debut = fin;
            fin += taillePartie;
        }

        // Arrêter le pool et attendre la fin de toutes les tâches
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("Erreur lors de l'attente de l'achèvement du pool de threads.");
            e.printStackTrace();
        }

        // Calculer la somme totale à partir des résultats de chaque Sommeur
        int sommeTotale = 0;
        for (int i = 0; i < nombreThreads; i++) {

            sommeTotale += sommesPartielles[i];
        }

        // Afficher la somme totale
        System.out.println("Somme totale: " + sommeTotale);
    }
}

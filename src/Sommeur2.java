class Sommeur2 implements Runnable {
    private int[] tableau;
    private int debut;
    private int fin;
    private int somme;

    public Sommeur2(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        somme = 0;
        for (int i = debut; i < fin; i++) {
            somme += tableau[i];
        }
    }

    public int getSomme() {
        return somme;
    }
}

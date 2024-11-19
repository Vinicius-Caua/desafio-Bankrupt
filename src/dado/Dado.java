package dado;

public class Dado {
    public int lancar() {
        return (int) (Math.random() * 6) + 1; // Retorna um número entre 1 e 6
    }
}


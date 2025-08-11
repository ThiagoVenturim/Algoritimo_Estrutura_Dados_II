class Booble {
    private int arrays[];
    private int tamanho;

    public Booble(int vetor[]) {
        this.arrays = vetor;
        this.tamanho = vetor.length;
    }

    public Booble() {
        this.arrays = new int[0];
        this.tamanho = 0;
    }

    public void setVetor(int vetor[]) {
        this.arrays = vetor;
        this.tamanho = vetor.length;
    }

    public int[] getVetor() {
        return this.arrays;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void metodo() {
        for (int i = 0; i < this.tamanho - 1; i++) {
            for (int j = i + 1; j < this.tamanho; j++) {
                if (arrays[i] > arrays[j]) { // ordem crescente
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
    }
}

public class testdrivebooble {
    public static void main(String[] args) {
        Booble booble = new Booble();
        int arrays[] = {3, 5, 1, 6, 9};

        booble.setVetor(arrays);

        booble.metodo(); // ordena o vetor

        int resultado[] = booble.getVetor();
        for (int i = 0; i < booble.getTamanho(); i++) {
            System.out.println(resultado[i]);
        }
    }
}

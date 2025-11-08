import java.util.*;

class Camisa {
    public String tamanho;
    public int aux;
    public String nome;

    public Camisa(String tamanho, String nome) {
        this.tamanho = tamanho;
        this.nome = nome;
        if (tamanho.charAt(0) == 'P') this.aux = 0;
        else if (tamanho.charAt(0) == 'M') this.aux = 1;
        else this.aux = 2;
    }
}

class Camisetas {
    public Camisa[] branco;
    public Camisa[] vermelho;
    public int i;
    public int j;

    public Camisetas(int tamanho) {
        branco = new Camisa[tamanho];
        vermelho = new Camisa[tamanho];
        i = j = 0;
    }

    public void inserir(String cor, String tamanho, String nome) {
        if (cor.equalsIgnoreCase("branco")) {
            branco[i++] = new Camisa(tamanho, nome);
        } else {
            vermelho[j++] = new Camisa(tamanho, nome);
        }
    }

    public void quicksort(Camisa[] array, int esq, int dir) {
        int i = esq;
        int j = dir;
        Camisa pivo = array[(esq + dir) / 2];

        while (i <= j) {
            while (array[i].aux < pivo.aux ||
                  (array[i].aux == pivo.aux && array[i].nome.compareTo(pivo.nome) < 0)) {
                i++;
            }
            while (array[j].aux > pivo.aux ||
                  (array[j].aux == pivo.aux && array[j].nome.compareTo(pivo.nome) > 0)) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (esq < j) quicksort(array, esq, j);
        if (i < dir) quicksort(array, i, dir);
    }

    public void swap(Camisa[] array, int i, int j) {
        Camisa tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void chamar() {
        if (i > 0) quicksort(branco, 0, i - 1);
        if (j > 0) quicksort(vermelho, 0, j - 1);

        for (int k = 0; k < i; k++) {
            System.out.println("branco " + branco[k].tamanho + " " + branco[k].nome);
        }
        for (int k = 0; k < j; k++) {
            System.out.println("vermelho " + vermelho[k].tamanho + " " + vermelho[k].nome);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rotacoes= 0;
        int x = scanner.nextInt();
        scanner.nextLine(); // consome quebra de linha

        while (x > 0 ) {
            if(rotacoes>0){System.out.println();}
            Camisetas ca = new Camisetas(x);

            for (int i = 0; i < x; i++) {
                String nome = scanner.nextLine().trim();
                String cor = scanner.next();
                String tamanho = scanner.next();
                scanner.nextLine(); // consome quebra de linha

                ca.inserir(cor, tamanho, nome);
            }
            rotacoes++;
            ca.chamar();
            x = scanner.nextInt();
            if (x > 0) scanner.nextLine();
        }
        scanner.close();
    }
}

package Atividades.ListaProva.FilaRecreio;
import java.util.*;

public class Main {
    public static void quicksort(int vetor[], int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivo = vetor[(esq + dir) / 2];
        while (i <= j) {
            while (vetor[i] > pivo) i++;   // ordem decrescente
            while (vetor[j] < pivo) j--;
            if (i <= j) {
                int tmp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = tmp;
                i++;
                j--;
            }
        }
        if (esq < j) quicksort(vetor, esq, j);
        if (i < dir) quicksort(vetor, i, dir);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int num = scanner.nextInt(); // número de casos de teste
        
        for (int i = 0; i < num; i++) {
            int tam = scanner.nextInt();
            int[] vetor = new int[tam];

            for (int j = 0; j < tam; j++) {
                vetor[j] = scanner.nextInt();
            }

            int[] copy = Arrays.copyOf(vetor, tam); // copia correta
            quicksort(vetor, 0, tam - 1);

            int mudaram = 0;
            for (int j = 0; j < tam; j++) {
                if (vetor[j] != copy[j]) mudaram++;
            }

            System.out.println(tam-mudaram);
        }
    }
}

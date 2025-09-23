import java.util.Scanner;

class Grid {
    private int[] largada;
    private int[] chegada;
    private int tamanho;

    public Grid(int tamanho) {
        largada = new int[tamanho];
        chegada = new int[tamanho];
        this.tamanho = tamanho;
    }

    public void setGrid(int index, int pos) {
        largada[index] = pos;
    }

    public void setChegada(int index, int pos) {
        chegada[index] = pos;
    }

    public void comparar() {
        // Cria uma cópia da largada para manipulação
        int[] aux = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            // Mapeia cada elemento da largada para sua posição na chegada
            for (int j = 0; j < tamanho; j++) {
                if (largada[i] == chegada[j]) {
                    aux[i] = j;
                    break;
                }
            }
        }

        // Contar ultrapassagens usando Bubble Sort (cada troca = ultrapassagem)
        int mov = 0;
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (aux[j] > aux[j + 1]) {
                    // troca
                    int temp = aux[j];
                    aux[j] = aux[j + 1];
                    aux[j + 1] = temp;
                    mov++;
                }
            }
        }

        System.out.println(mov);
    }
}

public class GridMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, num;

        while (scanner.hasNextInt()) {
            num = scanner.nextInt();
            if (num < 2) break;
            Grid grid = new Grid(num);

            for (int i = 0; i < num; i++) {
                a = scanner.nextInt();
                grid.setGrid(i, a);
            }

            for (int i = 0; i < num; i++) {
                b = scanner.nextInt();
                grid.setChegada(i, b);
            }

            grid.comparar();
        }

        scanner.close();
    }
}

/*
Explicação:
1. Transformamos cada elemento da largada para sua posição correspondente na chegada.
2. Depois, usamos Bubble Sort para ordenar esse array de posições.
3. Cada troca feita pelo Bubble Sort corresponde a uma ultrapassagem.
4. A variável 'mov' guarda o total de ultrapassagens.

Complexidade:
- Mapeamento de posições: O(N^2) (porque cada elemento da largada busca sua posição na chegada)
- Bubble Sort: O(N^2) no pior caso
- Espaço extra: O(N) para o array auxiliar
- Total: O(N^2) tempo e O(N) espaço
*/

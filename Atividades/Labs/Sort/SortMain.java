import java.util.*;

class Sort {
    private int modulo;
    private int[] lista;
    private int tamanho;
    private int n;

    public Sort(int modulo, int tamanho) {
        this.modulo = modulo;
        this.tamanho = tamanho;
        lista = new int[tamanho];
        n = 0;
    }

    public void adicionar(int numero) {
        if (n >= tamanho) return;
        lista[n] = numero;
        n++;
    }

    public void ordenarEMostrar() {
        ordenar();
        mostrar();
    }

    private void ordenar() {
        // Transformamos em Integer[] para usar Comparator
        Integer[] temp = new Integer[n];
        for (int i = 0; i < n; i++) temp[i] = lista[i];

        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int modA = a % modulo;
                int modB = b % modulo;

                if (modA != modB) {
                    return Integer.compare(modA, modB); // ordem pelo módulo
                }

                // empate no módulo
                boolean aImpar = (a % 2 != 0);
                boolean bImpar = (b % 2 != 0);

                if (aImpar && !bImpar) return -1; // ímpar antes de par
                if (!aImpar && bImpar) return 1;  // par depois de ímpar

                if (aImpar && bImpar) {
                    return Integer.compare(b, a); // ímpares em ordem decrescente
                }

                return Integer.compare(a, b); // pares em ordem crescente
            }
        });

        // copia de volta para o array original
        for (int i = 0; i < n; i++) lista[i] = temp[i];
    }

    private void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.println(lista[i]);
        }
    }
}

public class SortMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            if (N == 0 && M == 0) {
                System.out.println("0 0");
                break;
            }

            Sort sort = new Sort(M, N);

            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();
                sort.adicionar(num);
            }

            System.out.println(N + " " + M);
            sort.ordenarEMostrar();
        }

        sc.close();
    }
}

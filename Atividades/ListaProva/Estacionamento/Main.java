import java.util.*;

class Pilha {
    private int[][] array;
    private int n; // topo
    private int tamanho;

    public Pilha(int tamanho) {
        this.array = new int[tamanho][2];
        this.n = 0;
        this.tamanho = tamanho;
    }

    public boolean inserir(int entrada, int saida) {
        // enquanto houver elementos e a nova entrada for depois ou igual à saída do topo,
        // desempilha (libera espaço)
        while (n > 0 && entrada >= array[n - 1][1]) {
            n--;
        }

        // agora, se couber, insere respeitando ordem não-increasing de 'saida' no topo
        if (n < tamanho) {
            if (n == 0 || saida <= array[n - 1][1]) {
                array[n][0] = entrada;
                array[n][1] = saida;
                n++;
                return true;
            }
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        while (x != 0 && y != 0) {
            boolean resp = true;
            Pilha pl = new Pilha(y);

            for (int i = 0; i < x; i++) {
                int entrada = scanner.nextInt();
                int saida = scanner.nextInt();

                if (resp) { // só tenta inserir se ainda está válido
                    if (!pl.inserir(entrada, saida)) {
                        resp = false;
                        // não break: precisamos consumir os pares restantes — mas ignoramos as tentativas
                    }
                }
                // se resp já for false apenas consumimos os pares (já foram lidos)
            }

            System.out.println(resp ? "Sim" : "Nao");

            x = scanner.nextInt();
            y = scanner.nextInt();
        }
    }
}

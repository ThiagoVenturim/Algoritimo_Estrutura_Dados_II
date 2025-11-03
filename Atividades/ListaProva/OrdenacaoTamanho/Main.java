import java.util.Scanner;

class Funcoes {

    public void swap(String[] str, int i, int j) {
        String tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public void mostrar(String[] str, int tamanho) {
        for (int i = 0; i <= tamanho; i++) {
            System.out.print(str[i]);
            if (i < tamanho) System.out.print(" ");
        }
        System.out.println();
    }

    // Ordenação estável por tamanho decrescente
    public void ordenaPorTamanho(String[] str, int tamanho) {
        for (int i = 1; i <= tamanho; i++) {
            String atual = str[i];
            int j = i - 1;

            // Move palavras menores para frente, mantendo estabilidade
            while (j >= 0 && str[j].length() < atual.length()) {
                str[j + 1] = str[j];
                j--;
            }

            str[j + 1] = atual;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Funcoes funcoes = new Funcoes();

        int x = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < x; i++) {
            String linha = scanner.nextLine();
            String[] conjunto = new String[50];
            int cont = 0;
            conjunto[0] = "";

            for (int j = 0; j < linha.length(); j++) {
                if (linha.charAt(j) == ' ') {
                    cont++;
                    conjunto[cont] = "";
                } else {
                    conjunto[cont] += linha.charAt(j);
                }
            }

            funcoes.ordenaPorTamanho(conjunto, cont);
            funcoes.mostrar(conjunto, cont);
        }

        scanner.close();
    }
}

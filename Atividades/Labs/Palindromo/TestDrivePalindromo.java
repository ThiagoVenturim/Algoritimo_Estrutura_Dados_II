package Atividades.Palindromo;
import java.util.Scanner;

class Palindromo {
    private String frase;

    public Palindromo() {
        this.frase = "";
    }

    public Palindromo(String frase) {
        this.frase = frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    // Método iterativo (Questão 1)
    public boolean verificarIterativo() {
        int tamanho = frase.length();
        boolean resp = true;
        for (int i = 0; i < tamanho / 2; i++) {
            if (frase.charAt(i) != frase.charAt(tamanho - 1 - i)) {
                resp = false;
                break;
            }
        }
        return resp;
    }

    // Método recursivo (versão pública que inicializa)
    public boolean palindromoRecursivo(String s) {
        return palindromoRecursivo(s, 0);
    }

    // Método recursivo real
    private boolean palindromoRecursivo(String s, int i) {
        boolean resp;
        int j = s.length() - 1 - i;
        if (i >= j) {
            resp = true;
        } else if (s.charAt(i) != s.charAt(j)) {
            resp = false;
        } else {
            resp = palindromoRecursivo(s, i + 1);
        }
        return resp;
    }
}

public class TestDrivePalindromo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Palindromo palindromo = new Palindromo();
        String frase;

        do {
            frase = scanner.nextLine();
            if (frase.length() == 3 && 
                frase.charAt(0) == 'F' && 
                frase.charAt(1) == 'I' && 
                frase.charAt(2) == 'M') {
                break; // leitura termina em "FIM"
            }

            palindromo.setFrase(frase);

            // Versão iterativa:
            // if (palindromo.verificarIterativo()) System.out.println("SIM");
            // else System.out.println("NAO");

            // Versão recursiva:
            if (palindromo.palindromoRecursivo(frase)) System.out.println("SIM");
            else System.out.println("NAO");

        } while (true);

        scanner.close();
    }
}

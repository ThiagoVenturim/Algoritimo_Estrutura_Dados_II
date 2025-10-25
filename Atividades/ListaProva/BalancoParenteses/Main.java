import java.util.*;

class Parenteses {
    private String entrada;

    public Parenteses() {
        this.entrada = null;
    }

    public Parenteses(String entrada) {
        this.entrada = entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public void chamar() {
        if (classificar(entrada, 0, 0)) {
            System.out.println("correct");
        } else {
            System.out.println("incorrect");
        }
    }

    // pos = posição atual
    // count = número de '(' abertos
    public boolean classificar(String str, int pos, int count) {
        if (count < 0) return false; // fechou demais
        if (pos == str.length()) return count == 0; // terminou e está equilibrado?

        char c = str.charAt(pos);
        if (c == '(') {
            return classificar(str, pos + 1, count + 1);
        } else if (c == ')') {
            return classificar(str, pos + 1, count - 1);
        } else {
            return classificar(str, pos + 1, count); // ignora outros caracteres
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Parenteses p = new Parenteses(scanner.nextLine());
            p.chamar();
        }
        scanner.close();
    }
}

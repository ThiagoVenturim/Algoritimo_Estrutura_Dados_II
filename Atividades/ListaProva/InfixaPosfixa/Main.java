import java.util.*;

class Celula {
    public Celula prox;
    public String elemento;

    public Celula() {
        this("");
    }

    public Celula(String elemento) {
        this.prox = null;
        this.elemento = elemento;
    }
}

class Pilha {
    public Celula topo;

    public Pilha() {
        this.topo = null;
    }

    public void inserir(String elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = topo;
        topo = tmp;
    }

    public String remover() {
        if (topo == null) return "";
        String elemento = topo.elemento;
        topo = topo.prox;
        return elemento;
    }

    public String peek() {
        if (topo == null) return "";
        return topo.elemento;
    }

    public boolean vazia() {
        return topo == null;
    }
}

class InfixaPosfixa {

    // Retorna precedência de operadores
    public static int precedencia(char c) {
        switch (c) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default:  return -1;
        }
    }

    // Converte expressão infixa para pós-fixa usando Pilha personalizada
    public static String converter(String expressao) {
        StringBuilder resultado = new StringBuilder();
        Pilha pilha = new Pilha();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == ' ') continue; // ignora espaços

            // Se for operando (letra ou número)
            if (Character.isLetterOrDigit(c)) {
                resultado.append(c);
            }

            // Parêntese de abertura
            else if (c == '(') {
                pilha.inserir(String.valueOf(c));
            }

            // Parêntese de fechamento
            else if (c == ')') {
                while (!pilha.vazia() && !pilha.peek().equals("(")) {
                    resultado.append(pilha.remover());
                }
                pilha.remover(); // remove '('
            }

            // Operador
            else {
                while (!pilha.vazia() &&
                       precedencia(c) <= precedencia(pilha.peek().charAt(0))) {
                    resultado.append(pilha.remover());
                }
                pilha.inserir(String.valueOf(c));
            }
        }

        // Esvaziar pilha restante
        while (!pilha.vazia()) {
            resultado.append(pilha.remover());
        }

        return resultado.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String expressao = sc.nextLine();
            System.out.println(InfixaPosfixa.converter(expressao));
        }

        sc.close();
    }
}

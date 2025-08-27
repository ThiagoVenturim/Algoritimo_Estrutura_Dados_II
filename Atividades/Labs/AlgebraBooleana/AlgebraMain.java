package Atividades.Labs.AlgebraBooleana;

import java.util.*;

// Classe que contém toda a lógica da Álgebra Booleana
class Algebra {
    private String expressao;

    // Construtor padrão
    public Algebra() {
        this.expressao = "";
    }

    // Construtor com parâmetro
    public Algebra(String expressao) {
        this.expressao = expressao;
    }

    // Getter
    public String getExpressao() {
        return this.expressao;
    }

    // Setter
    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    // Método público que resolve a expressão armazenada e retorna 0 ou 1
    public int resolverExpressao(int n, int[] valores) {
        String expr = this.expressao;

        // Substituir variáveis A, B, C... pelos valores
        for (int i = 0; i < n; i++) {
            char var = (char) ('A' + i);
            String sVal = Integer.toString(valores[i]);

            StringBuilder nova = new StringBuilder();
            for (int j = 0; j < expr.length(); j++) {
                char c = expr.charAt(j);
                if (c == var) {
                    nova.append(sVal);
                } else {
                    nova.append(c);
                }
            }
            expr = nova.toString();
        }

        int resultado = eval(expr);
        return resultado;  // retorna 0 ou 1
    }

    // Avaliador recursivo da expressão
    private int eval(String expr) {
        expr = expr.trim();

        if (expr.equals("0") || expr.equals("1")) {
            return expr.charAt(0) - '0';
        }

        if (expr.startsWith("not(")) {
            String inside = expr.substring(4, expr.length() - 1);
            int valor = eval(inside);
            return (valor == 1) ? 0 : 1;
        }

        // and(...), or(...)
        if (expr.startsWith("and(") || expr.startsWith("or(")) {
            boolean isAnd = expr.startsWith("and(");
            String inside = expr.substring(isAnd ? 4 : 3, expr.length() - 1);

            List<String> args = splitArgs(inside);

            if (isAnd) {
                for (String arg : args) {
                    if (eval(arg) == 0) return 0;
                }
                return 1;
            } else {
                for (String arg : args) {
                    if (eval(arg) == 1) return 1;
                }
                return 0;
            }
        }

        throw new RuntimeException("Expressão inválida: " + expr);
    }

    // Divide argumentos dentro de parênteses
    private List<String> splitArgs(String expr) {
        List<String> args = new ArrayList<>();
        int nivel = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == ',' && nivel == 0) {
                args.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                if (c == '(') nivel++;
                if (c == ')') nivel--;
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            args.add(sb.toString().trim());
        }
        return args;
    }
}

// Classe principal apenas para ler entrada e chamar Algebra
public class AlgebraMain {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) continue;

            // Caso de parada: entrada "0"
            if (linha.equals("0")) break;

            String[] partes = linha.split(" ", 2);
            int n = Integer.parseInt(partes[0]);
            String resto = partes[1].trim();

            String[] tokens = resto.split(" ", n + 1);
            int[] valores = new int[n];
            for (int i = 0; i < n; i++) {
                valores[i] = Integer.parseInt(tokens[i]);
            }
            String expressao = tokens[n];

            // Cria objeto Algebra
            Algebra algebra = new Algebra(expressao);

            // Resolve
            int resultado = algebra.resolverExpressao(n, valores);

            // Imprime 0 ou 1
            System.out.println(resultado);
        }

        scanner.close();
    }    
}
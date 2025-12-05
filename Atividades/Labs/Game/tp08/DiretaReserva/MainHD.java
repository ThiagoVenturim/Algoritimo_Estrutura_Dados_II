import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

class Function {

    private void swap(int i, int j, Leitura[] game) {
        Leitura temp = game[i];
        game[i] = game[j];
        game[j] = temp;
    }

    public String[] separarPalavras(String linha, int tamanho) {
        String[] palavras = new String[tamanho];
        for (int i = 0, j = 0; i < tamanho && j < linha.length(); i++) {
            boolean parar = true;
            StringBuilder str = new StringBuilder();
            for (; parar && j < linha.length(); j++) {
                if (linha.charAt(j) == ',') {
                    parar = false;
                    if (j + 1 < linha.length() && linha.charAt(j + 1) == ' ') j++;
                } else if (linha.charAt(j) != ']' && linha.charAt(j) != '[') {
                    str.append(linha.charAt(j));
                }
            }
            if (str.length() > 1 && str.charAt(0) == '\'') {
                str.deleteCharAt(0);
                if (str.charAt(str.length() - 1) == '\'') str.deleteCharAt(str.length() - 1);
            }
            palavras[i] = str.toString();
        }
        return palavras;
    }

    public int quantidadeDePalavras(String linha) {
        if (linha == null || linha.isEmpty()) return 0;
        int quantidade = 1;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == ',') quantidade++;
        }
        return quantidade;
    }

    public int tranformarInt(String linha) {
        if (linha == null || linha.isEmpty()) return 0;
        int numero = 0;
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            if (Character.isDigit(c)) numero = numero * 10 + (c - '0');
        }
        return numero;
    }

    public float transformarFloat(String linha) {
        if (linha == null || linha.isEmpty() || linha.equals("tbd")) return 0f;
        linha = linha.replace(',', '.').trim();
        try {
            return Float.parseFloat(linha);
        } catch (Exception e) {
            return 0f;
        }
    }

    public String formatarData(String date) {
        if (date == null || date.isEmpty()) return "01/01/0000";
        boolean enc = false;
        for (int i = 0; i < date.length(); i++) {
            if (date.charAt(i) == ',') enc = true;
        }
        if (!enc) {
            StringBuilder test = new StringBuilder();
            for (int i = 0; i < date.length(); i++) {
                test.append(date.charAt(i));
                if (date.charAt(i) == ' ') test.append("01, ");
            }
            date = test.toString();
        }
        String mes = date.substring(0, 3);
        switch (mes) {
            case "Jan": mes = "01"; break;
            case "Feb": mes = "02"; break;
            case "Mar": mes = "03"; break;
            case "Apr": mes = "04"; break;
            case "May": mes = "05"; break;
            case "Jun": mes = "06"; break;
            case "Jul": mes = "07"; break;
            case "Aug": mes = "08"; break;
            case "Sep": mes = "09"; break;
            case "Oct": mes = "10"; break;
            case "Nov": mes = "11"; break;
            case "Dec": mes = "12"; break;
            default: mes = "01"; break;
        }
        StringBuilder completo = new StringBuilder();
        for (int i = 4; i < date.length(); i++) {
            if (date.charAt(i) == ',') {
                completo.append("/" + mes + "/");
                i += 2;
            }
            completo.append(date.charAt(i));
        }
        String nova = completo.toString();
        if (nova.charAt(1) == '/') nova = "0" + nova;
        return nova;
    }

    public void quickSor(int esq, int dir, Leitura[] game) {
        int i = esq, j = dir;
        int pivo = game[(esq + dir) / 2].getId();
        while (i <= j) {
            while (game[i].getId() < pivo) i++;
            while (game[j].getId() > pivo) j--;
            if (i <= j) {
                swap(i, j, game);
                i++;
                j--;
            }
        }
        if (esq < j) quickSor(esq, j, game);
        if (i < dir) quickSor(i, dir, game);
    }

    public int pesquisaBinaria(Leitura[] game, int x, int esq, int dir) {
        if (esq > dir) return -1;
        int meio = (esq + dir) / 2;
        if (game[meio].getId() == x) return meio;
        else if (game[meio].getId() < x)
            return pesquisaBinaria(game, x, meio + 1, dir);
        else
            return pesquisaBinaria(game, x, esq, meio - 1);
    }
}


class Game extends Function {
    private int id;
    private String name;
    private String releaseDate;
    private int estimatedOwners;
    private float price;
    private String[] suppportedLanguages;
    private int metacriticScore;
    private float userScore;
    private int achievements;
    private String[] publishers;
    private String[] developers;
    private String[] categories;
    private String[] genres;
    private String[] tags;
    private String linha;

    public Game(String linha) {
        this.linha = linha;
    }

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRelaseDate(String releaseDate) { this.releaseDate = formatarData(releaseDate); }
    public void setEstimatedOwners(int estimatedOwners) { this.estimatedOwners = estimatedOwners; }
    public void setPrice(float price) { this.price = price; }
    public void setSuppportedLanguages(String[] suppportedLanguages, int tamanho) { this.suppportedLanguages = suppportedLanguages; }
    public void setMetacriticScore(int metacriticScore) { this.metacriticScore = metacriticScore; }
    public void setUserScore(float userScore) { this.userScore = userScore; }
    public void setAchievements(int achievements) { this.achievements = achievements; }
    public void setPublishers(String[] publishers, int tamanho) { this.publishers = publishers; }
    public void setDevelopers(String[] developers, int tamanho) { this.developers = developers; }
    public void setCategories(String[] categories, int tamanho) { this.categories = categories; }
    public void setGenres(String[] genres, int tamanho) { this.genres = genres; }
    public void setTags(String[] tags, int tamanho) { this.tags = tags; }

    // GETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRelaseDate() { return releaseDate; }

    private void imprimirArray(String[] array) {
        System.out.print("[");
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
                if (i < array.length - 1) System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public void imprimir() {
        System.out.print("=> " + id + " ## " + name + " ## " + releaseDate + " ## " +
                estimatedOwners + " ## " + price + " ## ");
        imprimirArray(suppportedLanguages);
        System.out.print(" ## " + metacriticScore + " ## " + userScore + " ## " +
                achievements + " ## ");
        imprimirArray(publishers);
        System.out.print(" ## ");
        imprimirArray(developers);
        System.out.print(" ## ");
        imprimirArray(categories);
        System.out.print(" ## ");
        imprimirArray(genres);
        System.out.print(" ## ");
        imprimirArray(tags);
        System.out.println(" ##");
    }
}

class Leitura extends Game {
    public Leitura prox;  private String linha;

    public Leitura(String linha) {
        super(linha);
        this.linha = linha;
        this.prox = null;
    }



    public void chamarMetodo() {
        Metodo(linha);
    }

    private void Metodo(String linha) {
        int opcao = 0;
        for (int j = 0; j < linha.length(); opcao++) {
            StringBuilder str = new StringBuilder();
            boolean parar = true;
            int aspas = 0;
            for (; j < linha.length() && parar; j++) {
                if (linha.charAt(j) == ',' && aspas == 0) parar = false;
                else if (linha.charAt(j) == '"') aspas = 1 - aspas;
                else str.append(linha.charAt(j));
            }
            classificarLinha(opcao, str.toString());
        }
    }

    public void classificarLinha(int opcao, String str) {
        int tamanho = quantidadeDePalavras(str);
        switch (opcao) {
            case 0: setId(tranformarInt(str)); break;
            case 1: setName(str); break;
            case 2: setRelaseDate(str); break;
            case 3: setEstimatedOwners(tranformarInt(str)); break;
            case 4: setPrice(transformarFloat(str)); break;
            case 5: setSuppportedLanguages(separarPalavras(str, tamanho), tamanho); break;
            case 6: setMetacriticScore(tranformarInt(str)); break;
            case 7: setUserScore(transformarFloat(str)); break;
            case 8: setAchievements(tranformarInt(str)); break;
            case 9: setPublishers(separarPalavras(str, tamanho), tamanho); break;
            case 10: setDevelopers(separarPalavras(str, tamanho), tamanho); break;
            case 11: setCategories(separarPalavras(str, tamanho), tamanho); break;
            case 12: setGenres(separarPalavras(str, tamanho), tamanho); break;
            case 13: setTags(separarPalavras(str, tamanho), tamanho); break;
        }
    }
}
class HashReserva {
    private Leitura[] tabela;
    private int m1 = 21;   
    private int m2 = 9;    
    private int reserva = 0;
    private final int m = m1 + m2;

    public HashReserva() {
        tabela = new Leitura[m];
    }

    public int h(String nome) {
        if (nome == null) return 0; 
        int soma = 0;
        for (int i = 0; i < nome.length(); i++)
            soma += (int) nome.charAt(i);
        return soma % m1;
    }

    public boolean inserir(Leitura game) {
        if (game == null || game.getName() == null) return false;
        int pos = h(game.getName());
        if (tabela[pos] == null) {
            tabela[pos] = game;
            return true;
        } else if (reserva < m2) {
            tabela[m1 + reserva] = game;
            reserva++;
            return true;
        }
        return false;
    }

    public int pesquisarPosicao(String nome) {
        if (nome == null) return -1;
        int pos = h(nome);
        if (tabela[pos] != null && tabela[pos].getName().equals(nome)) {
            return pos;
        } else {
            for (int i = 0; i < reserva; i++) {
                if (tabela[m1 + i] != null && tabela[m1 + i].getName().equals(nome))
                    return m1 + i;
            }
        }
        return -1;
    }

    public boolean pesquisar(String nome) {
        return pesquisarPosicao(nome) != -1;
    }
}

public class MainHD {
    public static void main(String[] args) throws FileNotFoundException {

        File arq = new File("/tmp/games.csv");
        Scanner scfile = new Scanner(arq);
        Scanner scanner = new Scanner(System.in);

        if (scfile.hasNextLine())
            scfile.nextLine();

        HashReserva hash = new HashReserva();
        Leitura[] array = new Leitura[1850];
        Function func = new Function();
        int tamanho = 0;

        while (scfile.hasNextLine()) {
            String arquivo = scfile.nextLine();
            array[tamanho] = new Leitura(arquivo);
            array[tamanho].chamarMetodo();
            tamanho++;
        }

        
        String linha = scanner.nextLine();
        while (!linha.equals("FIM")) {
            int x = func.tranformarInt(linha);
            if (x >= 0 && x < tamanho) {
                hash.inserir(array[x]);
            }
            linha = scanner.nextLine();
        }

        long inicio = System.nanoTime();

        // Pesquisas de nomes
        linha = scanner.nextLine();
        while (!linha.equals("FIM")) {
            int pos = hash.pesquisarPosicao(linha);
            if (pos != -1) {
                System.out.println(linha + ":  (Posicao: " + pos + ") SIM");
            } else {
                System.out.println(linha + ":  (Posicao: " + hash.h(linha) + ") NAO");
            }
            linha = scanner.nextLine();
        }

        long fim = System.nanoTime();
        double tempoExecucao = (fim - inicio) / 1_000_000.0;

        try {
            FileWriter log = new FileWriter("878672_hashReserva.txt");
            log.write("878672\t" + tempoExecucao);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro no log: " + e.getMessage());
        }

        scanner.close();
        scfile.close();
    }
}
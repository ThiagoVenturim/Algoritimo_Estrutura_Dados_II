import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Classe utilitária para funções de parsing e ordenação
class Function {

    private static void swap(int i, int j, Leitura[] game) {
        Leitura temp = game[i];
        game[i] = game[j];
        game[j] = temp;
        MainAA.comp++; // Contabiliza a troca, se necessário, mas geralmente só se conta comparações.
    }

    public static String[] separarPalavras(String linha, int tamanho) {
        String[] palavras = new String[tamanho];
        for (int i = 0, j = 0; i < tamanho && j < linha.length(); i++) {
            boolean parar = true;
            StringBuilder str = new StringBuilder();
            int aspas = 0;
            for (; j < linha.length() && parar; j++) {
                if (linha.charAt(j) == ',') {
                    if (aspas == 0) {
                        parar = false;
                        // Consome espaço extra após a vírgula, se houver.
                        if (j + 1 < linha.length() && linha.charAt(j + 1) == ' ') j++;
                    } else {
                        str.append(linha.charAt(j));
                    }
                } else if (linha.charAt(j) == '"') {
                    aspas = 1 - aspas;
                } else if (linha.charAt(j) != ']' && linha.charAt(j) != '[') {
                    str.append(linha.charAt(j));
                }
            }
            // Remove as aspas simples de arrays de strings (ex: ['Action', 'RPG'])
            if (str.length() > 1 && str.charAt(0) == '\'') {
                str.deleteCharAt(0);
                if (str.length() > 0 && str.charAt(str.length() - 1) == '\'') str.deleteCharAt(str.length() - 1);
            }
            palavras[i] = str.toString().trim();
        }
        return palavras;
    }

    public static int quantidadeDePalavras(String linha) {
        if (linha == null || linha.isEmpty()) return 0;
        int quantidade = 1;
        int aspas = 0;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == '"') aspas = 1 - aspas;
            else if (linha.charAt(i) == ',' && aspas == 0) quantidade++;
        }
        return quantidade;
    }

    public static int tranformarInt(String linha) {
        if (linha == null || linha.isEmpty()) return 0;
        String limpa = linha.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(limpa);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static float transformarFloat(String linha) {
        if (linha == null || linha.isEmpty() || linha.equals("tbd")) return 0f;
        linha = linha.replace(',', '.').trim();
        try {
            return Float.parseFloat(linha);
        } catch (Exception e) {
            return 0f;
        }
    }

    public static String formatarData(String date) {
        if (date == null || date.isEmpty()) return "01/01/0000";
        try {
            // Usa um formato mais robusto para parsing de datas
            String[] parts = date.split(" ");
            String mesStr = parts[0];
            String diaStr = "01";
            String anoStr;

            if (date.contains(",")) {
                // Formato 'MMM DD, YYYY'
                diaStr = parts[1].replace(",", "");
                if (diaStr.length() == 1) diaStr = "0" + diaStr;
                anoStr = parts[2];
            } else {
                // Formato 'MMM YYYY'
                anoStr = parts[1];
            }

            String mes;
            switch (mesStr) {
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

            return diaStr + "/" + mes + "/" + anoStr;
        } catch (Exception e) {
            return "01/01/0000";
        }
    }

    public static void quickSor(int esq, int dir, Leitura[] game) {
        int i = esq, j = dir;
        int pivo = game[(esq + dir) / 2].getId();
        while (i <= j) {
            while (game[i].getId() < pivo) {
                MainAA.comp++;
                i++;
            }
            while (game[j].getId() > pivo) {
                MainAA.comp++;
                j--;
            }
            if (i <= j) {
                MainAA.comp++; // Contabiliza a comparação i <= j
                swap(i, j, game);
                i++;
                j--;
            }
        }
        if (esq < j) quickSor(esq, j, game);
        if (i < dir) quickSor(i, dir, game);
    }

    public static int pesquisaBinaria(Leitura[] game, int x, int esq, int dir) {
        if (esq > dir) return -1;
        int meio = (esq + dir) / 2;
        MainAA.comp++; // Comparações da pesquisa binária
        if (game[meio].getId() == x) return meio;
        else if (game[meio].getId() < x)
            return pesquisaBinaria(game, x, meio + 1, dir);
        else
            return pesquisaBinaria(game, x, esq, meio - 1);
    }
}

class Game {
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

    public Game() {}

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name.trim(); }
    public void setRelaseDate(String releaseDate) { this.releaseDate = Function.formatarData(releaseDate.trim()); }
    public void setEstimatedOwners(int estimatedOwners) { this.estimatedOwners = estimatedOwners; }
    public void setPrice(float price) { this.price = price; }
    public void setSuppportedLanguages(String[] suppportedLanguages) { this.suppportedLanguages = suppportedLanguages; }
    public void setMetacriticScore(int metacriticScore) { this.metacriticScore = metacriticScore; }
    public void setUserScore(float userScore) { this.userScore = userScore; }
    public void setAchievements(int achievements) { this.achievements = achievements; }
    public void setPublishers(String[] publishers) { this.publishers = publishers; }
    public void setDevelopers(String[] developers) { this.developers = developers; }
    public void setCategories(String[] categories) { this.categories = categories; }
    public void setGenres(String[] genres) { this.genres = genres; }
    public void setTags(String[] tags) { this.tags = tags; }

    // GETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public int getEstimatedOwners() { return this.estimatedOwners; }

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
    private String linha;

    public Leitura(String linha) {
        super();
        this.linha = linha;
    }

    public void chamarMetodo() {
        Metodo(linha);
    }

    // Método de parsing da linha
    private void Metodo(String linha) {
        int opcao = 0;
        int aspas = 0;
        for (int j = 0; j < linha.length() && opcao < 14; opcao++) {
            StringBuilder str = new StringBuilder();
            boolean parar = true;
            for (; j < linha.length() && parar; j++) {
                char c = linha.charAt(j);
                if (c == ',' && aspas == 0) {
                    parar = false;
                } else if (c == '"') {
                    aspas = 1 - aspas;
                } else {
                    str.append(c);
                }
            }
            classificarLinha(opcao, str.toString().trim());
        }
    }

    public void classificarLinha(int opcao, String str) {
        int tamanho = Function.quantidadeDePalavras(str);
        String[] palavras;
        switch (opcao) {
            case 0: setId(Function.tranformarInt(str)); break;
            case 1: setName(str); break;
            case 2: setRelaseDate(str); break;
            case 3: setEstimatedOwners(Function.tranformarInt(str)); break;
            case 4: setPrice(Function.transformarFloat(str)); break;
            case 5: palavras = Function.separarPalavras(str, tamanho); setSuppportedLanguages(palavras); break;
            case 6: setMetacriticScore(Function.tranformarInt(str)); break;
            case 7: setUserScore(Function.transformarFloat(str)); break;
            case 8: setAchievements(Function.tranformarInt(str)); break;
            case 9: palavras = Function.separarPalavras(str, tamanho); setPublishers(palavras); break;
            case 10: palavras = Function.separarPalavras(str, tamanho); setDevelopers(palavras); break;
            case 11: palavras = Function.separarPalavras(str, tamanho); setCategories(palavras); break;
            case 12: palavras = Function.separarPalavras(str, tamanho); setGenres(palavras); break;
            case 13: palavras = Function.separarPalavras(str, tamanho); setTags(palavras); break;
        }
    }
}

// NÓ DA ÁRVORE INTERNA (BST de Nomes)
class No {
    public Leitura elemento;
    public No esq;
    public No dir;

    public No(Leitura elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}

// ÁRVORE INTERNA (BST de Nomes)
class Arvore {
    public No raiz;

    public Arvore() {
        this.raiz = null;
    }

    private No inserir(Leitura elemento, No raiz) {
        if (raiz == null) {
            raiz = new No(elemento);
        } else {
            String novo = elemento.getName();
            String atual = raiz.elemento.getName();

            // Adiciona comparação de String ao contador
            int cmp = novo.compareTo(atual);
            MainAA.comp++;

            if (cmp < 0) {
                raiz.esq = inserir(elemento, raiz.esq);
            } else if (cmp > 0) {
                raiz.dir = inserir(elemento, raiz.dir);
            }
            // Não insere se for igual, segue o padrão BST
        }
        return raiz;
    }

    public void inserir(Leitura elemento){
        raiz = inserir(elemento, raiz);
    }

    // PESQUISA NA ÁRVORE INTERNA (por nome)
    private boolean pesquisa(String nome, No i) {
        if (i == null) return false;

        // Adiciona comparação de String ao contador
        int cmp = nome.compareTo(i.elemento.getName());
        MainAA.comp++;

        if (cmp < 0) {
            System.out.print("esq ");
            return pesquisa(nome, i.esq);
        }
        else if (cmp > 0) {
            System.out.print("dir ");
            return pesquisa(nome, i.dir);
        }
        else {
            return true;
        }
    }

    public boolean pesquisa(String nome){
        return pesquisa(nome, raiz);
    }
}

// NÓ DA ÁRVORE EXTERNA (BST de inteiros 0-14)
class NoA {
    public int elemento;
    public Arvore raizA; // Árvore interna
    public NoA esq;
    public NoA dir;

    public NoA(int elemento){
        this.elemento = elemento;
        this.raizA = new Arvore();
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreArvore {
    public NoA raiz;

    public ArvoreArvore() {
        this.raiz = null;
    }

    private NoA inserirNo(int elemento, NoA raiz) {
        if (raiz == null) {
            raiz = new NoA(elemento);
        } else if (elemento < raiz.elemento) {
            MainAA.comp++;
            raiz.esq = inserirNo(elemento, raiz.esq);
        } else if (elemento > raiz.elemento) {
            MainAA.comp++;
            raiz.dir = inserirNo(elemento, raiz.dir);
        } else {
            MainAA.comp++; // elemento == raiz.elemento
        }
        return raiz;
    }

    public void inserirNo(int elemento) {
        raiz = inserirNo(elemento, raiz);
    }

    // Encontra o nó da árvore externa (bucket) baseado na chave e insere na árvore interna
    private void inserirElementoRec(Leitura tmp, NoA raiz) {
        if (raiz == null) return;

        int chave = tmp.getEstimatedOwners() % 15;

        // Adiciona a comparação de int ao contador
        MainAA.comp++;
        if (chave == raiz.elemento) {
            raiz.raizA.inserir(tmp);
        } else if (chave < raiz.elemento) {
            MainAA.comp++;
            inserirElementoRec(tmp, raiz.esq);
        } else {
            MainAA.comp++; // (chave > raiz.elemento)
            inserirElementoRec(tmp, raiz.dir);
        }
    }

    public void inserirElemento(Leitura tmp) {
        inserirElementoRec(tmp, raiz);
    }

    private boolean pesquisaRec(String nome, NoA raiz) {
        if (raiz == null) return false;

        // Tenta pesquisar na árvore interna do nó atual
        if (raiz.raizA.pesquisa(nome)) return true;

        System.out.print("ESQ ");
        if (pesquisaRec(nome, raiz.esq)) return true;

        System.out.print("DIR ");
        return pesquisaRec(nome, raiz.dir);
    }

    public void pesquisa(String nome) {
        System.out.print("raiz ");
        if (pesquisaRec(nome, raiz)) {
            System.out.println(" SIM");
        } else {
            System.out.println(" NAO");
        }
    }
}


public class MainAA{
   
    public static int comp = 0;
    
    public static void main(String[] args) throws FileNotFoundException {
        File arq = new File("/tmp/games.csv"); 
        Scanner scfile = new Scanner(arq);
        Scanner scanner = new Scanner(System.in);

        // Pula o cabeçalho
        if (scfile.hasNextLine()) scfile.nextLine();

        ArvoreArvore arvoreDeGames = new ArvoreArvore();
        // O tamanho do array de Leitura precisa ser grande o suficiente para o CSV
        Leitura[] array = new Leitura[1850]; 
        int tamanho = 0;

        while (scfile.hasNextLine()) {
            String arquivo = scfile.nextLine();
            array[tamanho] = new Leitura(arquivo);
            array[tamanho].chamarMetodo();
            tamanho++;
        }

        Function.quickSor(0, tamanho - 1, array);

        int[] aux = {7,3,11,1,5,9,13,0,2,4,6,8,10,12,14};
        for (int x : aux) arvoreDeGames.inserirNo(x);

        String linha = scanner.nextLine();
        while (!linha.equals("FIM")) {
            int x = Function.tranformarInt(linha);
            int pos = Function.pesquisaBinaria(array, x, 0, tamanho - 1);

            if (pos != -1) {
                arvoreDeGames.inserirElemento(array[pos]);
            }

            linha = scanner.nextLine();
        }

        long inicio = System.nanoTime();
        linha = scanner.nextLine();
        while (!linha.equals("FIM")) {
            System.out.print("=> "+linha + " => ");
            arvoreDeGames.pesquisa(linha.trim());
            linha = scanner.nextLine();
        }
        long fim = System.nanoTime();
        double tempoExecucao = (fim - inicio) / 1_000_000.0;

        try {
            FileWriter log = new FileWriter("878672_arvoreArvore.txt");
            log.write("878672\t" + comp + "\t" + String.format(Locale.US, "%.3f", tempoExecucao));
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo de log: " + e.getMessage());
        }
        
        scanner.close();
        scfile.close();
    }
}
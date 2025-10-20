import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

class Function{

    public int comparacoes;
     private void swap(int i, int j , Game []game){
        Game temp = game[i];
        game[i] = game[j];
        game[j] = temp;
        comparacoes=0;
     }
    
    public String[] separarPalavras(String linha, int tamanho){
        String[] palavras = new String[tamanho];
        for(int i=0, j=0 ; i<tamanho || j<linha.length() ; i++){
            boolean parar = true;
            StringBuilder str = new StringBuilder();
           for( ;  parar && j<linha.length()   ; j++){
              if(linha.charAt(j) == ','){
                    parar= false;
                    if(linha.charAt(j+1) == ' '){
                        j++;
                    }
                }else if(linha.charAt(j) != ']' && linha.charAt(j) != '[' ){
                str.append(linha.charAt(j));
            }
        }
        if (str.length() > 0 && str.charAt(0) == '\'') {
            StringBuilder td= new StringBuilder();
            for(int k=1; k<str.length()-1; k++ ){
                td.append(str.charAt(k));
            }
            str = td ;
        }

        palavras[i] = str.toString();
        }
        return palavras;
    }

    public int quantidadeDePalavras(String linha){
        int quantidade = 1;
        for(int i=0; i<linha.length(); i++){
            if(linha.charAt(i) == ','){
                quantidade++;
                
            }
        }
        return quantidade;
    }

    public int  tranformarInt(String linha){
        int numero = 0;
        for(int i=0; i<linha.length(); i++){
            numero = numero * 10 + (linha.charAt(i) - '0');
        }
        return numero;
    }   

    public float transformarFloat(String linha) {
        String limpa = linha.trim(); 
        limpa = limpa.replace(',', '.');
        return Float.parseFloat(limpa);
    }

    public String formatarData(String date){
        boolean enc= false;
        for( int i=0; i<date.length(); i++){
            if (date.charAt(i) == ','){
                enc =true;   
            }
        }
        if(!enc){
        StringBuilder test = new StringBuilder();
        for( int i=0; i<date.length(); i++){
            test.append(date.charAt(i));
            if(date.charAt(i) == ' ' ){
                test.append("01, ");
            }
        }    
            date= test.toString();
        }
        String mes= "";
        for(int i=0; i<3 && i<date.length(); i++){  mes += date.charAt(i);}
        switch (mes) {
            case "Jan": mes= "01"; break;
            case "Feb": mes= "02"; break;
            case "Mar": mes= "03"; break;
            case "Apr": mes= "04"; break;
            case "May": mes= "05"; break;
            case "Jun": mes= "06"; break;
            case "Jul": mes= "07"; break;
            case "Aug": mes= "08"; break;
            case "Sep": mes= "09"; break;
            case "Oct": mes= "10"; break;
            case "Nov": mes= "11"; break;
            case "Dec": mes= "12"; break;
            default: mes="01"; break;
        }
        StringBuilder completo = new StringBuilder();
        for( int i=4; i<date.length(); i++){
            if (date.charAt(i) == ','){
                completo.append("/"+ mes + "/");
                i+=2;
            }
            completo.append(date.charAt(i));
        }
        String nova = completo.toString();
        completo = new StringBuilder();
            if(nova.charAt(1)== '/'){
                completo.append("0" + nova); 
                nova = completo.toString();
            }
        return nova;
    }

   public void quickSorLetra(int esq, int dir, Leitura[] game) {
        int i = esq;
        int j = dir;
        String pivo = game[(esq + dir) / 2].getName();

        while (i <= j) {
            while (game[i].getName().compareToIgnoreCase(pivo) < 0){i++;}
            while (game[j].getName().compareToIgnoreCase(pivo) > 0){j--;        }
            if (i <= j) {
                swap(i, j, game);
                i++;
                j--;    
            }
        }

        if (esq < j) quickSorLetra(esq, j, game);
        if (i < dir) quickSorLetra(i, dir, game);
    }

    

    public void quickSor(int esq, int dir, Leitura[] game){
        int i, j, pivo; 
        for( i =esq, j = dir, pivo = game[(esq+dir)/2].getId() ; i<=j ;){ 
            while(game[i].getId() <pivo){ i++;}
            while(game[j].getId() >pivo){ j--;}
            if(i<=j){
                swap(i, j, game);
                i++;
                j--;
            }
        }
        if(esq<j){ quickSor(esq, j, game);}
        if(dir>i){ quickSor(i, dir, game);}    
    }

    public int pesquisaBinaria(Leitura [] game, int x, int dir, int esq){
        if(esq>dir){
            return 0;
        }else{
            int meio = (dir+esq)/2;
            if(game[meio].getId()== x){
                return meio;
            }else if(game[meio].getId()< x ){
                return pesquisaBinaria(game, x, dir, meio+1);
            }else{
                return pesquisaBinaria(game, x,  meio-1,esq);
            }
        }
    }

   public boolean pesquisaBinariaLetra(Leitura[] game, String str, int esq, int dir) {
        if (esq > dir) {
            return false;
        } else {
            int meio = (esq + dir) / 2;
            comparacoes++;
            int cmp = game[meio].getName().compareToIgnoreCase(str);

            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                // O nome no meio é menor → procurar à direita
                return pesquisaBinariaLetra(game, str, meio + 1, dir);
            } else {
                // O nome no meio é maior → procurar à esquerda
                return pesquisaBinariaLetra(game, str, esq, meio - 1);
            }
        }
    }
}
class Game extends Function{

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

    public Game(String linha){
        this.linha = linha;        
    }

    public void setId(int id) { this.id = id; } 
    public void setName(String name){this.name=name; }
    public void setRelaseDate(String releaseDate){ this.releaseDate=formatarData(releaseDate);}
    public void setEstimatedOwners(int estimatedOwners){ this.estimatedOwners=estimatedOwners; }
    public void setPrice(float price){this.price=price; }
    public void setSuppportedLanguages(String[] suppportedLanguages, int tamanho){ 
        this.suppportedLanguages = new String[tamanho];
        this.suppportedLanguages = suppportedLanguages; 
    }
    public void setMetacriticScore(int metacriticScore){ this.metacriticScore=metacriticScore; }
    public void setUserScore(float userScore){ this.userScore=userScore;}
    public void setAchievements(int achievements){ this.achievements=achievements; }
    public void setPublishers(String []publishers, int tamanho){ 
        this.publishers = new String[tamanho];
        this.publishers  = publishers;
    }
    public void setDevelopers(String []developers,  int tamanho){ 
        this.developers = new String[tamanho];
        this.developers = developers; 
    }
    public void setCategories(String []categories, int tamanho ){ 
        this.categories = new String[tamanho];
        this.categories = categories; 
    }
    public void setGenres(String []genres, int tamanho){ 
        this.genres = new String[tamanho];
        this.genres = genres; 
    }
    public void setTags(String []tags, int tamanho ){ 
        this.tags = new String[tamanho];
        this.tags = tags; 
    }
  
    
    public int getId(){ return  id; } 
    public String getName(){ return name; }
    public String getRelaseDate(){ return releaseDate;}
    public int getEstimatedOwners(){ return estimatedOwners; }
    public float getPrice(){return price; }
    public String[] getSuppportedLanguages(){return suppportedLanguages; }
    public int getMetacriticScore(){ return metacriticScore; }
    public float getUserScore(){  return userScore;}
    public int getAchievements(){return achievements; }
    public String[] getPublishers(){ return  publishers; }
    public String[] getDevelopers(){  return developers; }
    public String[] getCategories(){  return categories; }
    public String[] getGenres(){ return genres; }
    public String[] getTags(){ return tags; }
  
   

    private void imprimirArray( String[] game) {
        System.out.print( "[" );
        int cout=0;
        if (game != null) {
            for (String elem : game) {
                System.out.print(elem);
                if(game.length>1 && game.length-1!=cout ){ System.out.print(", ");}
                cout++;
            }
            
        }
        System.out.print("]");
    }

    public void imprimir(){
        System.out.print(
            "=> "+ getId() + " ## " + 
            getName() + " ## " + 
            getRelaseDate() + " ## " + 
            getEstimatedOwners() + " ## " +      
            getPrice() + " ## "
        );

        imprimirArray(getSuppportedLanguages());

        System.out.print(
            " ## " + getMetacriticScore() + " ## " +
             getUserScore() + " ## " + 
             getAchievements() + " ## "
        );

        imprimirArray( getPublishers());
        System.out.print(" ## ");
        imprimirArray( getDevelopers());
        System.out.print(" ## ");
        imprimirArray( getCategories());
        System.out.print(" ## ");
        imprimirArray( getGenres());
        System.out.print(" ## ");
        imprimirArray( getTags());
        System.out.print(" ##");
    }

    public void Mostrar(){
        System.out.println(linha);
    }

}

class Leitura extends Game{

    private String linha;
    
    public Leitura(String linha) {
        super(linha);
        this.linha= linha;
    }

    public void chamarMetodo(){
        Metodo(linha);
    }   

    private void Metodo(String linha){
        int opcao =0;
        for(int j= 0; j<linha.length(); opcao ++){  
            //System.out.println("Opção: " + opcao);
            StringBuilder str = new StringBuilder();
            boolean parar = true;
            int aspas = 0;
            for( ; j< linha.length() && parar ; j++){
                //System.out.println("Caractere: " + linha.charAt(j) + " - " + j);
                if( linha.charAt(j) == ',' && (aspas == 0 || aspas ==2 )){
                    parar = false;
                }else if( linha.charAt(j) == '\"'){
                    aspas++;
                    if(aspas == 2){
                        parar = false;
                        j++;
                    }
                }else {
                    str.append(linha.charAt(j));
                }
               

                
            }
            //System.out.println("Opcao: " + opcao + " - " + str.toString()); 
            classificarLinha(opcao, str.toString());
            //System.out.println("----" );
        }
            
}


    public void classificarLinha(int opcao, String str){
            int tamanho= quantidadeDePalavras(str);
        
        switch (opcao) {
            case 0:
                setId(tranformarInt(str));
                break;
            case 1:
                setName(str);
                break;
            case 2:
                setRelaseDate(str);
                break;
            case 3:
                setEstimatedOwners(tranformarInt(str));
                break;
            case 4:
                setPrice(transformarFloat(str));
                break;
            case 5:
                setSuppportedLanguages(separarPalavras(str ,tamanho), tamanho);
                break;
            case 6:
                setMetacriticScore(tranformarInt(str));
                break;
            case 7:
                setUserScore(transformarFloat(str));
                break;
            case 8:
                setAchievements(tranformarInt(str));
                break;
            case 9:
                setPublishers(separarPalavras(str ,tamanho), tamanho);    
                break;
            case 10:
                setDevelopers(separarPalavras(str ,tamanho), tamanho);
                break;
            case 11:
                setCategories(separarPalavras(str, tamanho), tamanho);
                break;
            case 12:
                setGenres(separarPalavras(str, tamanho), tamanho);
                break;
            case 13:
                setTags(separarPalavras(str ,tamanho), tamanho);
                break
                ;
            default:
            System.out.println("Opção inválida");
                break;
        }
    }

}

public class GameMainBinario {
    public static void main(String[] args)  throws FileNotFoundException {
        File arq = new File("/tmp/games.csv");
                //File arq = new File("games.csv");
        Scanner scfile = new Scanner(arq);
        Scanner scanner = new Scanner(System.in);
         if (scfile.hasNextLine()){
          scfile.nextLine();
        }
        Leitura[] game= new Leitura[1848];
        Function func = new Function();
        
        for (int i=0; i<1848 && scfile.hasNextLine() ; i++){
            String arquivo = scfile.nextLine();
            game[i] = new Leitura(arquivo);
            game[i].chamarMetodo();
        }

        func.quickSor(0, game.length-1, game);


        //for(int i =0; i<1848; i++){ game[i].imprimir(); System.out.println();}
        
        int tamanhoBinario=0;
        Leitura[] prontoBinario= new Leitura[300];
        String linha= scanner.nextLine();
        while ((linha.charAt(0) != 'F'  &&  linha.charAt(1) != 'I' && linha.charAt(0) != 'M' && scanner.hasNextLine()) ) {
            int x  = func.tranformarInt(linha);
            x=func.pesquisaBinaria(game, x, game.length-1 ,0);
            prontoBinario[tamanhoBinario++] = game[x];
            linha= scanner.nextLine();
        }
        
        func.quickSorLetra(0, tamanhoBinario-1, prontoBinario);
        //for(int i =0; i<tamanhoBinario-1; i++){ prontoBinario[i].imprimir(); System.out.println();}
        linha = scanner.nextLine();

        long inicio = System.nanoTime();
        while (!linha.equalsIgnoreCase("FIM") && scanner.hasNextLine()) {

            if (func.pesquisaBinariaLetra(prontoBinario, linha, 0, tamanhoBinario - 1)) {
                System.out.println(" SIM");
            } else {
                System.out.println(" NAO");
            }

            linha = scanner.nextLine();
        }
        long fim = System.nanoTime();
        double tempoExecucao = (fim - inicio) / 1_000_000.0;

        try {
            FileWriter log = new FileWriter("878672_mergesort.txt");
            log.write("878672\t" + func.comparacoes + "\t"  + String.format("%.3f", tempoExecucao));
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo de log: " + e.getMessage());
        }
        scanner.close();
        scfile.close();
    }   
}

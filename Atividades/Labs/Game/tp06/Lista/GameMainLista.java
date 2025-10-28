import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Function{

     private void swap(int i, int j , Game []game){
        Game temp = game[i];
        game[i] = game[j];
        game[j] = temp;
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
          this.linha = linha; 
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

class Lista{
    private int tamanho;
    private Leitura []array;
    private int  n;
   


    public Lista(int tamanho){
        this.tamanho=tamanho;
        array= new Leitura[tamanho];
        this.n=0;
    }

  
    public void inserirInicio(Leitura leitura) {
        if( n<tamanho){
            for (int i= n ; i>0; i--) {
                array[i]= array[i-1];
            }
            array[0]= leitura;
            n++;
        }
    }

    public void inserirFim(Leitura leitura) {
        if( n<tamanho){
            array[n]= leitura;
            n++;
        }
    }

    public void inserirPos(Leitura leitura, int pos) {
       if(pos<0 || pos>n || n==tamanho){return;}
       else if(pos==0){ inserirInicio(leitura); }
       else if(pos == n){ inserirFim(leitura);}
       else{
            for(int i=n; i>pos; i--){
                array[i]= array[i-1];
            }
            array[pos]= leitura;
            n++;
       }

    }
    public Leitura removerInicio() {
        if(n==0){
            return null;
        }else{
            Leitura tmp = array[0];
            n--;
            for(int i=0; i<n; i++){
                array[i]= array[i+1];
            }
           return tmp;
        }
    }

    public Leitura removerFim() {
        if(n==0){
            return null;
        }else{
           return array[--n];
        }
    }

    public Leitura removerPos(int pos) {
         if(n==0 || pos<0||pos>n){
            return null;
        }else if(pos==0){ return removerInicio();}
        else if(pos==n-1){ return removerFim();
        }else{
            Leitura tmp = array[pos];
            n--;
            for(int i=pos; i<n; i++){
                array[i]= array[i+1];
            }
           return tmp;
        }
    }

    public void Mostrar(){
        if(0!=n){
            for(int i=0;i<n; i++){
                array[i].imprimir();
                System.out.println();
            }
        }
    }
    
}

public class GameMainLista {
    public static void main(String[] args)  throws FileNotFoundException {
        File arq = new File("tmp/games.csv");
        //File arq = new File("games.csv");
        Scanner scfile = new Scanner(arq);
        Scanner scanner = new Scanner(System.in);
         if (scfile.hasNextLine()){
          scfile.nextLine();
        }

        Leitura[] array= new Leitura[1850];
        Function func = new Function();

        int tamanho=0;
        while (scfile.hasNextLine()) {
            String arquivo = scfile.nextLine();
            array[tamanho] = new Leitura(arquivo);
            array[tamanho].chamarMetodo();
            tamanho++;
        }

        Lista game= new Lista(tamanho);

        func.quickSor(0, tamanho-1, array);
        String linha =scanner.nextLine();
        while ((linha.charAt(0) != 'F'  &&  linha.charAt(1) != 'I' && linha.charAt(0) != 'M' && scanner.hasNextLine()) ) {
            int x  = func.tranformarInt(linha);
            x=func.pesquisaBinaria(array, x, tamanho-1 ,0);
            game.inserirInicio(array[x]);
            linha= scanner.nextLine();
        }
       game.Mostrar();
       
        scanner.close();
        scfile.close();
    }   
}

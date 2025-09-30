import java.util.*;

class Function{

    public String[] separarPalavras(String linha, int tamanho){
        String[] palavras = new String[tamanho];
        for(int i=0, j=0 ; i<tamanho || j<linha.length() ; i++){
            boolean parar = true;
            StringBuilder str = new StringBuilder();
           for( ;  parar && j<linha.length()   ; j++){
                if(linha.charAt(j) == ','){
                    parar= false;
                }else if(linha.charAt(j) != ']' && linha.charAt(j) != '[' && linha.charAt(j) != '\'' && linha.charAt(j) != ' '  ){
                str.append(linha.charAt(j));
            }
        }
        //System.out.println("Palavra " + i + ": " + str.toString());
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
        String mes= "";
        for(int i=0; i<3 && i<date.length(); i++){  mes += date.charAt(i);}
        switch (mes) {
            case "Jan":
                mes= "01";
                break;
            case "Feb":
                mes= "02";
                break;
            case "Mar":
                mes= "03";
                break;
            case "Apr":
                mes= "04";
                break;
            case "May":
                mes= "05";
                break;
            case "Jun":
                mes= "06";
                break;
            case "Jul":
                mes="07";
                break;
            case "Aug":
                mes="08";
                break;
            case "Sep":
                mes="09";
                break;
            case "Oct":
                mes="10";
                break;
            case "Nov":
                mes="11";
                break;
            case "Dec":
                mes="12";
                break;
            default:
                mes="01";
                break;
        }
        StringBuilder completo = new StringBuilder();
        for( int i=4; i<date.length(); i++){
            if (date.charAt(i) == ','){
                completo.append("/"+ mes + "/");
                i+=2;
                
            }
            completo.append(date.charAt(i));
        }

        return completo.toString();
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
  
   
    
    private void imprimirArray(String titulo, String[] array) {
        System.out.print( titulo + ": ");
        if (array != null) {
            for (String elem : array) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }

    public void imprimir(){
        System.out.println(
            "ID: "+ getId() + "\n" + 
            "Name: " + getName() + "\n" + 
            "Date: " + getRelaseDate() + "\n" + 
            "Estimated Owners: "+ getEstimatedOwners() + "\n" +      
            "Price: "+ getPrice()
        );

        imprimirArray("Supported Languages", getSuppportedLanguages());

        System.out.println(
            "MetacriticScore: "+ getMetacriticScore() + "\n" + 
            "UserScore: "+getUserScore() + "\n" + 
            "Achievements: "+getAchievements()
        );

        imprimirArray("Publishers", getPublishers());
        imprimirArray("Developers", getDevelopers());
        imprimirArray("Categories", getCategories());
        imprimirArray("Genres", getGenres());
        imprimirArray("Tags", getTags());
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
                if( linha.charAt(j) == ',' && (aspas == 0 ||  (opcao !=2 && opcao != 1 &&opcao !=5 && opcao < 9))){
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
    


public class GameMain {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);


         if (scanner.hasNextLine()) {
          scanner.nextLine();
        }

        Leitura game;

        for (int i=0; i<1848 && scanner.hasNextLine(); i++){
            System.out.println("----- Jogo: " + (i+1) + " -----");
            String linha = scanner.nextLine();
            game = new Leitura(linha);
            game.chamarMetodo();
            game.imprimir();
            System.out.println("");
        }
        scanner.close();
    }   
}

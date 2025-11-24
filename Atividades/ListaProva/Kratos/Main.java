import java.util.*;

class No{
    public String nome;
    public int poder, matou, mortes;
    public No esq,  dir;
    
    public No(){
        this("", 0,0,0);
    }

    public No(String nome, int poder, int matou, int mortes ){
        this.nome= nome;
        this.poder=poder;
        this.matou=matou;
        this.mortes=mortes;
        this.esq= this.dir= null;
    }
}

class Arvore{
    private No raiz;    
    
    public Arvore(){raiz=null;}

    public No inserir(String nome, int poder, int matou, int mortes, No raiz){
        if(raiz ==null){
            raiz = new No(nome, poder, matou, mortes);
        }else if(
            (raiz.poder > poder) ||
            (raiz.poder == poder && raiz.matou > matou) || 
            (raiz.poder == poder && raiz.matou == raiz.matou && raiz.mortes > mortes ) 
        ){
            raiz.esq =inserir(nome, poder, matou, mortes, raiz.esq);
        }else if(
            (raiz.poder <  poder) ||
            (raiz.poder == poder && raiz.matou < matou) || 
            (raiz.poder == poder && raiz.matou == raiz.matou && raiz.mortes < mortes ) 
        ){
            raiz.dir =inserir(nome, poder, matou, mortes, raiz.dir);
        }
        return raiz;
    }

    public void inserir(String nome, int poder, int matou, int mortes){
        raiz= inserir(nome, poder, matou, mortes, raiz);
    }
    
    public void getMaior(){
        No tmp= raiz;
        for( ; tmp.dir!=null; tmp = tmp.dir);
        System.out.println(tmp.nome);
    }

}

public class Main{
    public static void main(String [] args){
        Scanner scanner  = new Scanner(System.in);
        Arvore arvore = new Arvore();
        int tamanho= scanner.nextInt();
        for(int i =0 ; i <tamanho; i++){
            String nome=scanner.next();
            int poder=scanner.nextInt();
            int matou=scanner.nextInt();
            int mortes=scanner.nextInt();
            arvore.inserir(nome, poder, matou, mortes);
          
        }
        arvore.getMaior();
        scanner.close();
    }
}
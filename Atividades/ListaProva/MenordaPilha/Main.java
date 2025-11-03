import java.util.*;

class Celula{
    public int elemento;
    public Celula prox;
    public Celula ant;

    public Celula(){
        this(0);
    }
    public Celula(int elemento){
        this.elemento=elemento;
        this.prox=null;
        this.ant=null;
    }

}

class Pilha{
    public Celula topo;

    public Pilha(){
       this.topo=null;
    }

    public void inserir(int elemento){
        if(topo==null){
            topo = new Celula(elemento);
        }else{
            Celula tmp = new Celula(elemento);
            tmp.prox= topo;
            topo.ant = tmp;
            topo = tmp;
        }
    }

    public void remover(){
        if(topo==null){
            return; 
        }else{
           topo = topo.prox;
        }
    }   
     public void min(){
        if(topo==null){
        return;
        }
        System.out.println(min(topo)); 
     }

    public int min(Celula tmp){ 
       int min= tmp.elemento;
       for( Celula aux =tmp.prox ; aux!= null; aux = aux.prox){
            if(min>  aux.elemento ){
                min =aux.elemento;
            }
       }
       return min;
    }



}

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Pilha pl = new Pilha();
        int tam = scanner.nextInt();
        scanner.nextLine();

        for(int i =0; i<tam && scanner.hasNextLine(); i++ ){
            String linha = scanner.nextLine();
            if(linha.compareToIgnoreCase("MIN")==0){
                pl.min();
            }else if(linha.compareToIgnoreCase("POP")==0){
                pl.remover();
            }else{
                int num=0;
                for(int k = linha.length()-1 , pos =1 ; k>=0 && linha.charAt(k) >= '0' && linha.charAt(k) <= '9' ; k--){
                        num+= (linha.charAt(k) - '0')*pos;
                        pos*=10;
                }
                pl.inserir(num);
            }
        }

        
        scanner.close();
    }
}
import java.util.*;

class Celula{
    public int elemento;
    public Celula prox;
    
    public Celula(){
        this.elemento=0;
        this.prox= null;
    }
    public Celula(int x){
        this.elemento=x;
        this.prox= null;
    }
}

class Fila{
    private Celula inicio;

    public Fila(){ 
        inicio = new Celula();
    }

    public void inserir(int x ){
        Celula tmp = inicio;
        int num =  1;
        for(; tmp.prox != null ; tmp = tmp.prox){
            num++;
        } 

        tmp.prox = new Celula(x);
    } 

    public int remover(){
        int valor = inicio.prox.elemento;
        inicio = inicio.prox;
        return valor;
    }

    public void mostrar(){
        Celula tmp = inicio.prox;
        for( ; tmp!=null ; tmp = tmp.prox ){
            System.out.print(tmp.elemento + " ");
        }
    }
    
} 

public class FilaFlexivel{
    public static void main(String[] args){
        Fila fila = new Fila();
        fila.inserir(1);
        fila.inserir(2);
        fila.inserir(3);
        fila.inserir(4);
        fila.inserir(5);
        fila.inserir(1234);
        fila.mostrar();
    }
}
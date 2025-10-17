import java.util.Scanner;

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

class Lista{
    private Celula primeiro, ultimo;

    public int tamanho() {
      int tamanho = 0;
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
    
    public Lista(){
            primeiro = new Celula();
            ultimo = primeiro;
    }

    public void inserirFim(int x){
        ultimo.prox= new Celula(x);
        ultimo = ultimo.prox;
    }

    public void inserirInicio(int x){

        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }
        tmp= null;

    } 

    public int removerFim(){
        if(primeiro == ultimo){
            System.out.println("Erro: Lista Vazia");
            return -1;
        }
        Celula i;
        for(i=primeiro; i.prox != ultimo; i=i.prox);
        int elemento = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return elemento;
    }

    public int removerInicio(){
        if(primeiro == ultimo){
            System.out.println("Erro: Lista Vazia");
            return -1;
        }
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        int elemento = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public void inserir(int x, int pos ){
        int tamanho= tamanho();
        if(pos<0 || pos>tamanho){
            System.out.println("Erro: Posicao Invalida");
        }else if(pos==0){
            inserirInicio(x);
        }else if(pos==tamanho){
            inserirFim(x);
    
        }else{
            Celula i = primeiro;
            for(int j= 0; j<pos; j++, i=i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public int remover(int pos){
        int elemento, tamanho= tamanho();
        if(primeiro == ultimo || pos<0 || pos>=tamanho){
            System.out.println("Erro: Lista Vazia");
            return -1;
        }else if(pos==0){
            elemento = removerInicio();
        }else if(pos==tamanho-1){
            elemento = removerFim();
        }else{
            Celula i = primeiro;
            for(int j=0; j<pos; j++, i=i.prox);
            Celula tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }    
        return elemento;
    }

    public void mostrar(){
        System.out.print("[ ");
        for(Celula i= primeiro.prox; i!= null; i=i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

}
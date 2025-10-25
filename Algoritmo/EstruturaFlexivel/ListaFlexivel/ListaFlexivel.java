import java.util.Scanner;

class Celula{
    public int elemento;
    public Celula prox;
    public Celula ant;

    public Celula(){
        this(0);
    }
    public Celula(int x){
        this.elemento=x;
        this.prox= null;
        this.ant=null;
    }
}

class Lista{
    
    Celula inicio;
    Celula fim;

    public Lista(){
        inicio = fim = new Celula();
    }

    public void inserirInicio(int x){
        inicio.elemento = x;
        inicio.ant = new Celula();
        inicio.ant.prox= inicio;
        inicio= inicio.ant;
    }

    public int removerInicio(){
        if(inicio == fim){return -1;}
        else{
            Celula tmp = inicio.prox;
            inicio =inicio.prox;
            inicio.ant = null;
            return tmp.elemento;
        }
        
    }

    public void inserirFim(int x){
        fim.prox = new Celula(x);
        fim.prox.ant = fim;
        fim = fim.prox;
    }

    public int removerFim(){
        if(inicio == fim){return -1;}
        else{
            Celula tmp = fim;
            fim =fim.ant;
            return tmp.elemento;
        }
    }

    public void inserirPos(int pos, int x){
        int tamanho= getTamanho();
        if(pos< 0 || pos>tamanho){ return;}
        else if(pos==0){inserirInicio(x);}
        else if(pos==tamanho){inserirFim(x);}
        else{
            Celula tmp = inicio.prox;
            for(int i =0; i<pos-1; i++, tmp = tmp.prox);
            tmp.prox.ant  = new Celula(x);
            tmp.prox.ant.prox=  tmp.prox;
            tmp.prox= tmp.prox.ant;
            tmp.prox.ant = tmp;
        }
    }

    public int removerPos(int pos){
        int tamanho= getTamanho();
        if(pos< 0 || pos>tamanho){ return -1;}
        else if(pos==0){ return removerInicio();}
        else if(pos==tamanho){ return removerFim();}
        else{
            Celula tmp = inicio.prox;
            for(int i =0; i<pos; i++, tmp = tmp.prox);
            tmp.prox = tmp.ant;
            tmp.ant.prox= tmp.prox;
            return tmp.elemento;
        }
    }

    public int getTamanho(){
        int tamanho = 0;
        for(Celula tmp = inicio.prox; tmp!=null; tamanho++, tmp =tmp.prox){}
        return tamanho;
    }

    public void mostrar(){
       
        for(Celula tmp = inicio.prox; tmp!=null;  tmp =tmp.prox){
            System.out.print(  tmp.elemento +  " ");
        }
         System.out.println();
    }

    private void inverter(Celula i , Celula j){
        if(i == j || i.ant == j){return;}
        else{
        int tmp = i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp;
        inverter( i.prox , j.ant);
        }
    }

    public void inverter(){
        inverter(inicio.prox, fim);
    }
    
    public void quicksort(Celula esquerda, Celula direita) {
        if (direita != null && esquerda != direita && esquerda != direita.prox) {
            Celula pivo = particionar(esquerda, direita);
            quicksort(esquerda, pivo.ant);
            quicksort(pivo.prox, direita);
        }
    }

    private Celula particionar(Celula esquerda, Celula direita) {
        int valorPivo = direita.elemento;
        Celula i = esquerda.ant;

        for (Celula j = esquerda; j != direita; j = j.prox) {
            if (j.elemento <= valorPivo) {
                i = (i == null) ? esquerda : i.prox;
                swap(i, j);
            }
        }

        i = (i == null) ? esquerda : i.prox;
        swap(i, direita);
        return i;
    }

    public Celula getPivo(int pos){
        Celula tmp =inicio.prox;
        for (int i = 0; i < pos && tmp != null; i++, tmp = tmp.prox) { }
        return tmp;
    }

  

    public void swap(Celula i, Celula j){
        int tmp =  i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp; 
    }
    public void quicksort(){
        quicksort(inicio, fim);
    }
}
public class ListaFlexivel{
    public static void main(String[] args){
        Lista ls = new Lista();
        ls.inserirInicio(7);
        ls.inserirFim(1);
         ls.inserirInicio(4);
        ls.inserirFim(12354);
         ls.inserirInicio(74);
        ls.inserirFim(15);
        ls.inserirPos(1, 10);
        ls.mostrar();
        ls.quicksort();
        ls.mostrar();

    }
}
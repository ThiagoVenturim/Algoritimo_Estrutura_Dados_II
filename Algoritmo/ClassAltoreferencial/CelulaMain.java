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
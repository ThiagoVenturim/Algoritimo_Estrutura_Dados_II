import java.util.*;

import Algoritmo.EstruturaFlexivel.FIlaFlexivel.Celula; 

class Celula{
    public int elemento;
    public Celula dir;
    public Celula esq;
    public Celula sup;
    public Celula inf;

    public Celula(){
       this(0);
    }
    public Celula(int x){
        this.elemento=x;
        this.dir= null;
        this.esq= null;
        this.sup= null;
        this.inf= null;
    }
}

class Matriz{
    private int linha;
    private int colunas;
    private Celula inicio;

    public Matriz( ){
        this(3 , 3);
    }

    public Matriz(int linha, int colunas){
        this.linha= linha;
        this.colunas =colunas;

        inicio = new Celula(); 
        Celula colNova = inicio;
        
        for(int i =0; i<linha; i++){
            if(i != 0){
                colNova.inf = new Celula();
                colNova.inf.sup = colNova;
                colNova= colNova.inf;
            }
            Celula linNova = colNova;
            for(int j=1; j<colunas; j++, linNova = linNova.dir ){
                linNova.dir = new Celula();
                linNova.dir.esq = linNova;
                if(i != 0){
                    linNova.dir.sup= linNova.sup.dir;
                    linNova.sup.dir.inf= linNova.dir.sup;
                }
            }
        }
    }

    public void inserirElemento(Scanner scanner){
        System.out.println("Inserir elemento: ");
        for(Celula i = inicio ; i!= null  ; i = i.inf){
            for(Celula j=i; j!=null; j = j.dir ){
                j.elemento = scanner.nextInt();
            }
            System.out.println();
        }
    }

    public void mostrar(){
        for(Celula i = inicio; i!= null; i = i.inf){

            for(Celula j=i; j!=null; j = j.dir ){
                System.out.print( " " + j.elemento );
            }
            System.out.println();
        }
    }

}

public class MatrizMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Matriz matriz = new Matriz(3, 3);
        matriz.inserirElemento(scanner);
        matriz.mostrar();
    }
}
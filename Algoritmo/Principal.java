package Algoritmo;
import java.util.Scanner;
import java.util.Random;

class Geracao{
    protected int[] arrays;
    protected int n;

    public Geracao(){
        arrays = new int[100];
        n = arrays.length;
    }
    
    public Geracao(int tamanho){
        arrays = new int[tamanho];
        n=arrays.length;
    }

    public void crecente(){
        for(int i =0; i<n; i++){ arrays[i]= 1; }
    }

    public void decrescente(){
        for(int i =0;i<n ; i++){arrays[i]= n-1-i; }
    }

    public void aleatorio(){
        Random rand= new Random();
        crecente();
        for(int i=0; i<n ; i++){
            swap(i, Math.abs(rand.nextInt()) % n);
        }
    }

    public void entradaPadrao(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arrays = new int[n];
        for(int i = 0; i<n; i++){
            arrays[i] = scanner.nextInt();
        }
    }

    public void entrada(int[] vet){
        n= vet.length;
        arrays= new int[n];
        for(int i =0; i<n; i++){arrays[i]= vet[i];}
    }

    public void mostrar(){
        System.out.print("[ ");
        for(int i = 0; i< n; i++){
            System.out.println( i + " = " + arrays[i]);
        }
        System.out.println(" ]");
    } 

    public void swap(int i, int j ){
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public long now(){
        return new Date.getTime();
    } 

    public boolean isOrdenado(){
        for(int i =1; i<n;i++){
            if(arrays[i] <arrays [i-1]){
                return false;
            }
        }
    return true;
    }

    public void sort(){ }

}

class Selecao extends Geracao{

    public Selecao(){ super(); }
    public Selecao(int tamanho){ super(tamanho);}

    @Override
    public void sort(){
        for(int i =0; i <(n -1); i++){
            int menor = i;
            for(int j =(i+1); j< n; j++ ){
                if(arrays[menor] > arrays[j]){ menor = j ; }
            }
            if(menor != i){
                swap(i, menor);
            }
        }
    }

}   

class Bolha extends Geracao{

    public Bolha(){ super(); }
    public Bolha(int tamanho){ super(tamanho);}

    @Override
    public void sort(){
        for(int i =0; i <n; i++){
            for(int j =(i+1); j< n; j++ ){
                if(arrays[i] > arrays[j]){swap(i, j); }
            }
        }
    }

    
}   

class Counting extends Geracao{


}


class Heapsort extends Geracao{

}


class Inseracao extends Geracao{

}


class Shellsort extends Geracao{

}


public class Principal{
    public static void main(String [] args){
        Geracao algoritmo;
        int n =(args.length < 1) ? 1000: Integer.parseInt(args[0]);
        double inicio, fim;

        //-----Iniciando Algoritmos Geral-----
        algoritmo = new Bolha(n);
        //algoritmo = new Countingsort(n);
        //algoritmo = new Heapsort(n);
        //algoritmo = new Inseracao(n);
        //algoritmo = new Mergesort(n);
        //algoritmo = new Quicksort(n);
        //algoritmo = new Selecao(n);
        //algoritmo = new Shellsort(n);

        //-----Geracao do Conjunto-----
        algoritmo.aleatorio();
        //algoritmo.crecente();
        //algoritmo.decrecente();

        //-----Mostrar Conjunto Ordenado---
        //algoritmo.mostar();

        //-----Execucao de Algoritmo de Ordenado---
        inicio = algoritmo.now();
        algoritmo.sort();
        fim = algoritmo.now();

        // Mostrar Conjunto ordenado e tempo
        System.out.printf("Tempo para ordermar" + (fim- inicio)/1000.0 + "s");
        System.out.println("isOrdenado: "+ algoritmo.isOrdenado());


    }
}
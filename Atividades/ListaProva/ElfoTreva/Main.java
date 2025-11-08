import java.util.*;

class Rena{
    public String nome;
    public int peso;
    public int idade;
    public float altura;

    public Rena(String nome, int peso, int idade, float altura){
        this.altura=altura;
        this.nome=nome;
        this.idade=idade;
        this.peso=peso;
    }
}

class Treno{
    private Rena []renas;
    private int tamanho;
    private int conjunto;
    private int n;

    public Treno(int tamanho, int conjunto){
        this.renas= new Rena[tamanho];
        this.tamanho=tamanho;
        this.n=0;
        this.conjunto=conjunto;
    }

    public void inserir(String nome, int peso, int idade, float altura){
        this.renas[n++]= new Rena(nome, peso, idade, altura);
    }

    public void quicksort(){
        quicksort(renas,0 , tamanho-1);
    }

    public void quicksort(Rena []array, int esq, int dir){
        int i=esq;
        int j=dir;
        Rena pivo = array[(esq+dir)/2];
        while (i<=j) {
            while(
            ( array[i].peso > pivo.peso) || // maior peso vem antes
            (array[i].peso == pivo.peso && array[i].idade < pivo.idade) || // mesma peso, menor idade vem antes
            (array[i].peso == pivo.peso && array[i].idade == pivo.idade && array[i].altura < pivo.altura) // mesma idade e peso, menor altura vem antes
            ){i++;}

            while(
             (array[j].peso < pivo.peso) || // menor peso vem depois
            (array[j].peso == pivo.peso && array[j].idade > pivo.idade) || // mesma peso, maior idade vem depois
            (array[j].peso == pivo.peso && array[j].idade == pivo.idade && array[j].altura > pivo.altura) // mesma idade e peso, maior altura vem depois
            ){j--;}
            
            if(i<=j) {
                swap(array, i , j);
                i++;
                j--;    
            }
        }
        if(esq<j){quicksort(array, esq, j);}
        if(dir>i){quicksort(array, i, dir);}
    }

    public void swap(Rena []array, int i , int j){
        Rena tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void mostrar(){
        for(int i =0 ; i<conjunto;i++){
            System.out.println((i+1) + " - " + renas[i].nome);
        }
    }
}

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int x= scanner.nextInt();
        for(int i=0 ; i<x; i++){
            int tamanho = scanner.nextInt();
            int conjunto = scanner.nextInt();
            Treno treno = new Treno(tamanho, conjunto);
            scanner.nextLine();
            for(int j=0; j<tamanho ; j++){
                String nome = scanner.next();
                int peso = scanner.nextInt();
                int idade = scanner.nextInt();
                String entrada = scanner.next().replace(',', '.');
                float altura = Float.parseFloat(entrada);
                treno.inserir(nome, peso, idade, altura);
            
            }
            treno.quicksort();
            System.out.println( "CENARIO {" + (i+1) + "}" );
            treno.mostrar();
            
        }
    }
}

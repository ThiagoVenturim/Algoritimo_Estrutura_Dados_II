import java.util.*;

class Pilha{
    private int array[];
    private int tamanho;
    private int n;

    public Pilha(int tamanho){
        this.array = new int[tamanho];
        this.tamanho=tamanho;
        this.n=0;
    }

    public void inserir(int x){
        array[n++] = x;
    }


    public int verificar(){
        int resp = 1;
        int cont=0;
        int aux=0;
        for(int i=0; i<3; i++){
               aux+= array[i];
               cont++;
                if(aux%3==0){
                    cont=0;
                    aux=0;
                    resp=1;
                }else{
                    resp=0;
                }
        }
        return resp;
    }
}

public class Main{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        while(x!=0){
            x*=3;
            int aux=1;
            for(int j= 0; j<x/3; j++){
                 Pilha pl = new Pilha(3);
                for(int i=0; i<3; i++){
                    pl.inserir(scanner.nextInt());
                }
                if(pl.verificar()==0){
                    aux=0;
                }
            }
            System.out.println(aux);
            x = scanner.nextInt();
        }
        
    }
}
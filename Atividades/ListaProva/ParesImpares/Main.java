import java.util.*;

public class Main{

    public static void quicksort(int vetor[], int esq, int dir){
        int i=esq;
        int j=dir;
        int pivo = vetor[(esq+dir)/2];
        while(i<=j){
            while(vetor[i] < pivo){ i++; }
            while(vetor[j] > pivo){ j--; }
            
            if(i<=j){
                int tmp = vetor[i];
                vetor[i]= vetor[j];
                vetor[j] = tmp;
                j--;
                i++;
            }
        }
        if(esq<j ){ quicksort(vetor, esq, j);}
        if(dir>i ){ quicksort(vetor, i , dir );}
    }

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        int tam= scanner.nextInt();

        int pares = 0;
        int impares = 0;
        int [] par = new int [tam];
        int [] impar = new int [tam];
        int x=0;
        for(int i=0; i<tam;i++){
            x =scanner.nextInt();
            if(x %2==0){
                par [pares++] = x; 
            }else{
                impar [impares++] = x; 
            }
        }
        quicksort(par, 0, pares-1);
        quicksort(impar, 0, impares-1);

        for(int i=0; i<pares; i++){System.out.println(par[i]); }
        for(int i=impares-1; i>=0; i--){System.out.println(impar[i] ); }
    }
}
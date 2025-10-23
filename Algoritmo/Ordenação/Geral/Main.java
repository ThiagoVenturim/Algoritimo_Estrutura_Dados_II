
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

class Algortimo{
    protected int [] array;
    protected int tamanho;
    protected int comparacao;
    protected int movimentacao;

    public Algortimo(){
        this(0, null);
    }

    public Algortimo(int tamanho ,int[] array){
        this.array = array;
        this.tamanho = tamanho;
    }

    public void setArray(int [] array){ 
        this.array = array; 
        setTamanho(array.length);
    }

    private void setTamanho(int tamanho){ 
        this.tamanho = tamanho;
    }

    public int[] array(){
        return array;
    }

    public void swap(int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    } 

    public void mostrar(){
        for(int i = 0; i<tamanho ; i++){
            System.out.print( array[i] + " ");
        }
        System.out.println();        
    }
    
  
}


class QuickSort extends Algortimo {
    public QuickSort(int []array){
        super( array.length,array);
    }

    
    public void ordenar(){
        movimentacao=0;
        comparacao=0; 
        ordenar(array, 0, tamanho-1);
    }

    private void ordenar(int [] array, int esq, int dir){
        int i =esq, j = dir;
        int pivo = array[(esq+dir)/2];

        while(i<=j){
            while(array[i]<pivo){ i++; comparacao++;}
            while(array[j]>pivo){ j--; comparacao++;}
            if( i<=j){
                swap(i , j);
                i++;
                j--;
                movimentacao++;
            }
        }
        if(esq<j){ordenar(array, esq, j);}
        if(dir>i){ordenar(array, i, dir);}
    }
}

class MergeSort extends Algortimo{

    public MergeSort(int [] array){
        super( array.length, array );
    }

    public void ordenar(){
        merge(0, tamanho-1);
    }

    private void merge(int esq, int dir){
        if(esq <dir){
            int meio= (esq+dir)/2;
            merge(esq, meio);
            merge(meio+1, dir);
            intercalar(esq, meio, dir);
        }
    }

    private void intercalar(int esq, int meio, int dir){
        int nEsq = (meio+1)-esq;
        int nDir = dir-meio;
        int [] arrayEsq= new int[nEsq+1];
        int []arrayDir= new int[nDir+1];
        arrayEsq[nEsq] =arrayDir[nDir] = 0x7FFFFFFF;

        int iEsq, iDir, i;
        for(iEsq =0; iEsq< nEsq; iEsq++){
            arrayEsq[iEsq] = array[esq+iEsq];
        }

        for(iDir= 0 ; iDir <nDir; iDir++){
            arrayDir[iDir] = array[(meio+1)+iDir];
        }

        for(iEsq = iDir =0, i=esq; i<=dir; i++){
            array[i] = (arrayEsq[iEsq]<=arrayDir[iDir])? arrayEsq[iEsq++]: arrayDir[iDir++];
        }
    }
}

class HeapSort extends Algortimo{
    public HeapSort(int [] array){
        super( array.length, array );
    }

    public void ordenar( ){
        int []tmp = new int [tamanho+1];
        for(int i = 0;i<tamanho; i++){
            tmp[i+1] = array[i];
        }
        array = tmp;

        for(int tamHeap = 2 ;tamHeap<=tamanho; tamHeap++){
            construir(tamHeap);
        }
        int tamHeap =tamanho;
        while(tamHeap>1){
            swap(1, tamHeap--);
            recostruir(tamHeap);
        }
        tmp=array;
        array = new int[tamanho];
        for(int i=0; i<tamanho; i++){
            array[i] = tmp[i+1];
        }
    }

    public void construir(int tamHeap){
        for(int i = tamHeap; i>1 && array[i]>array[i/2]; i/=2){
            swap(i, i/2);
        }
    }

    public void recostruir(int tamHeap){
        int i= 1;
        while (i<=(tamHeap/2)){
            int filho = getMaiorFilho(i ,tamHeap);
                if(array[i] <array[filho]){
                    swap(i, filho);
                    i=filho;
                }else{
                    i=tamHeap;
                }
            }
    }

    
   public int getMaiorFilho(int i, int tamHeap){
      int filho;
      if (2*i == tamHeap || array[2*i] > array[2*i+1]){
         filho = 2*i;
      } else {
         filho = 2*i + 1;
      }
      return filho;
   }
}

class ShellSort extends Algortimo{
    public ShellSort(int [] array){
        super( array.length, array );
    }

    public void ordenar(){
        int h=1;
        do{ h=(h*3)+1;}while(h<tamanho);
        do{
            h/=3;
            for(int cor=0; cor<h; cor++){insercaoPorCor(cor, h);}
        }while(h!=1);
    }

    public void insercaoPorCor(int cor, int h){
        for(int i = (h+cor); i<tamanho; i+=h){
            int tmp = array[i];
            int j= i-h;
            while((j>=0)&&(array[j]>tmp)){
                array[j+h] = array[j];
                j-=h;
            } 
            array[j+h]=tmp;
        }
    }

}

class Selecao extends Algortimo{
    
    public Selecao(int [] array){
        super( array.length, array );
    }

    public void ordenar(){
        for(int i=0; i<tamanho-1; i++){
            int menor= i;
            for(int j=i+1; j<tamanho ; j++){
                if(array[menor] > array[j]){
                   menor=j;
                }                
            }
            if(menor!=i){
                swap(i, menor);
            }
        }
    }

}

class Insercao extends Algortimo{

    public Insercao(int [] array){
        super(array.length, array);
    }

    public void ordenar(){
        for(int i = 1; i<tamanho; i++){
            int tmp = array[i];
            int j=i-1;
            while (array[j]>tmp && j>=0){
                 array[j+1]= array[j];
                j--;
            }
            array[j+1]=tmp;
        }
    }
}

class Bolha extends Algortimo{

    public Bolha(int [] array){
        super(array.length, array);
    }
        
    public void ordenar(){
        for(int i=0; i<tamanho; i++){
            for(int j=0; j<tamanho ; j++){
                if(array[i] < array[j]){
                    swap(i, j);
                }                
            }
        }
    }
}

public class Main{
    public static void main(String [] args){


        int []array = {80,12,24,70,9,52,41,30,60,51, 976457, 124, 1, -87};
        
        System.out.print("Quick Sort: ");
        QuickSort qs = new QuickSort(array);
        qs.ordenar();
        qs.mostrar();
        qs=null;

        System.out.print("Selecao: ");
        Selecao sl =new Selecao(array);
        sl.ordenar();
        sl.mostrar();
        sl=null;

        System.out.print("Bolha: ");
        Bolha bl = new Bolha(array);
        bl.ordenar();
        bl.mostrar();
        bl=null;

        System.out.print("Insercao: ");
        Insercao in = new Insercao(array);
        in.ordenar();
        in.mostrar();
        in=null;


        System.out.print("Heap Sort: ");
        HeapSort hp = new HeapSort(array);
        hp.ordenar();
        hp.mostrar();
        hp=null;

        System.out.print("Merge: ");
        MergeSort mg = new MergeSort(array);
        mg.ordenar();
        mg.mostrar();
        mg=null;

        System.out.print("Shell: ");
        ShellSort ss = new ShellSort(array);
        ss.ordenar();
        ss.mostrar();
        ss=null;
    }
}
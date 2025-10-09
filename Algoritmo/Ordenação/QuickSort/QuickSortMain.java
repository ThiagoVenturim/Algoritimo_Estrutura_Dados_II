import java.util.*;
import java.time.LocalDateTime;

class QuickSort {
    private int tamanho;
    private int [] array;

    public QuickSort(int tamanho) {
        this.tamanho = tamanho;
        array= new int[tamanho];
    }

    public void setTamanho(int tamanho){ this.tamanho=tamanho; }
    public void setArray(int[] array){ this.array=array;}
    
    private void swap(int i, int j , int []array){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private int numAleatorio(int i, int j){
        return (int)(Math.random() * (j - i + 1) + i);
    }
    private int medianaDeTres(int[] array, int esq, int dir) {
        int meio = (esq + dir) / 2;

        int a = array[esq];
        int b = array[meio];
        int c = array[dir];

        if ((a - b) * (c - a) >= 0) return esq;
        else if ((b - a) * (c - b) >= 0) return meio;
        else return dir;
    }



    private void quickSort(int esq, int dir, int []array){
        int i, j, pivo; 
        for( i =esq, j = dir, pivo = array[(esq+dir)/2] ; i<=j ;){ 
            while(array[i]<pivo){ i++;}
            while(array[j]>pivo){ j--;}
            if(i<=j){
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if(esq<j){ quickSort(esq, j, array);}
        if(dir>i){ quickSort(i, dir, array);}          
    }

    private void quickSorFi(int esq, int dir, int[] array){
        int pivo, i,  j ;
        for ( i = esq, j = dir -1, pivo = array[dir]; i <= j; ) {
            while (i <= j && array[i] < pivo){ i++;}
            while (i <= j && array[j] >= pivo){ j--;}
            if (i < j) {
                
              swap(i, j, array);
            }
        }
        swap(i, dir, array);
        
        if (esq < i - 1) {
            quickSorFi(esq, i - 1, array);
        }
        if (i + 1 < dir) {
            quickSorFi(i + 1, dir, array);
        }

    }
    private void quickSorFiIn(int esq, int dir, int[] array) {
    if (esq >= dir) return; // caso base para evitar índices inválidos

    int pivo = array[esq];
    int i = esq + 1;
    int j = dir;

    while (i <= j) {
        while (i <= j && array[i] <= pivo) i++;
        while (i <= j && array[j] > pivo) j--;
        if (i < j) {
            swap(i, j, array);
            i++;
            j--;
        }
    }
    swap(esq, j, array); // pivô vai para a posição correta

    quickSorFiIn(esq, j - 1, array);
    quickSorFiIn(j + 1, dir, array);
    }

    private void quickSortMediana(int esq, int dir, int[] array) {
        swap(medianaDeTres(array, esq, dir), dir, array); 
        int pivo ,i ,j;

        for (i= esq, j= dir-1 ,pivo = array[dir]; i <= j; ) {
            while (i <= j && array[i] < pivo) i++;
            while (i <= j && array[j] >= pivo) j--;
            if (i < j) {
                swap(i, j, array);
            }
        }

        swap(i, dir, array); 
        if (esq < i - 1) quickSortMediana(esq, i - 1, array);
        if (i + 1 < dir) quickSortMediana(i + 1, dir, array);
    }
    
    private void quickSortRandom(int esq, int dir, int[] array) {
        swap(numAleatorio( esq, dir), dir, array); 
        int pivo ,i ,j;

        for (i= esq, j= dir-1 ,pivo = array[dir]; i <= j; ) {
            while (i <= j && array[i] < pivo) i++;
            while (i <= j && array[j] >= pivo) j--;
            if (i < j) {
                swap(i, j, array);
            }
        }

        swap(i, dir, array); 
        if (esq < i - 1) quickSortRandom(esq, i - 1, array);
        if (i + 1 < dir) quickSortRandom(i + 1, dir, array);
    }
    
    

    public void chamarMostrar(){

    // quickSorFi (pivô no fim)
    int[] a = array.clone();
    long inicio = System.nanoTime();
    quickSorFi(0, tamanho - 1, a);
    long fim = System.nanoTime();
    System.out.println("quickSorFi: " + (fim - inicio) + " ns");
    //for(int i=0; i<tamanho; i++) System.out.print(a[i] + " ");
    //System.out.println();

    // quickSorFiIn (pivô no início)
    int[] b = array.clone();
    inicio = System.nanoTime();
    quickSorFiIn(0, tamanho - 1, b);
    fim = System.nanoTime();
    System.out.println("quickSorFiIn: " + (fim - inicio) + " ns");
    //for(int i=0; i<tamanho; i++) System.out.print(b[i] + " ");
    //System.out.println();

    // quickSortMediana (mediana do início, meio e fim)
    int[] c = array.clone();
    inicio = System.nanoTime();
    quickSortMediana(0, tamanho - 1, c);
    fim = System.nanoTime();
    System.out.println("quickSortMediana: " + (fim - inicio) + " ns");
    //for(int i=0; i<tamanho; i++) System.out.print(c[i] + " ");
    //System.out.println();

    // quickSortRandom (pivô aleatório)
    int[] d = array.clone();
    inicio = System.nanoTime();
    quickSortRandom(0, tamanho - 1, d);
    fim = System.nanoTime();
    System.out.println("quickSortRandom: " + (fim - inicio) + " ns");
    //for(int i=0; i<tamanho; i++) System.out.print(d[i] + " ");
    //System.out.println();
}
}


public class QuickSortMain {

    public static void PreOrdenado(int num){
        System.out.println("\n Testando com "+ num);
        int []array = new int[num];
        for(int i=0; i<num; i++){
            array[i] = i+1;
        }

        QuickSort qs = new QuickSort(num); 
        qs.setArray(array);
        qs.chamarMostrar();
    }

    public static void Aleatorio(int num){
        System.out.println("\nTestando com " + num );
        int[] array = new int[num];
        Random rand = new Random();
        
        for(int i = 0; i < num; i++){
            array[i] = rand.nextInt(num * 10); // números aleatórios de 0 a num*10
        }

        QuickSort qs = new QuickSort(num);
        qs.setArray(array);
        qs.chamarMostrar();
    }

    public static void QuaseOrdenado(int num, double percentualTrocas){
        System.out.println("\nTestando com " + num + " elementos quase ordenados");
        int[] array = new int[num];
        
        // preenche o array de 1 até num
        for(int i = 0; i < num; i++){
            array[i] = i + 1;
        }

        Random rand = new Random();
        int numTrocas = (int)(num * percentualTrocas); // quantos elementos serão trocados
        
        for(int i = 0; i < numTrocas; i++){
            int idx1 = rand.nextInt(num);
            int idx2 = rand.nextInt(num);
            // troca os dois elementos
            int temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }

        QuickSort qs = new QuickSort(num);
        qs.setArray(array);
        qs.chamarMostrar();
    }   


    public static void main(String[] args) {
        System.out.println("-------Pre Ordenado------");
        PreOrdenado(100);
        PreOrdenado(250);
        PreOrdenado(500);
        PreOrdenado(1000);
        PreOrdenado(5000);
        PreOrdenado(10000);

        System.out.println("-------Aleatorio------");
        Aleatorio(100);
        Aleatorio(250);
        Aleatorio(500);
        Aleatorio(1000);
        Aleatorio(5000);
        Aleatorio(10000);
        
        System.out.println("------- Arrays Quase Ordenados ------");
        QuaseOrdenado(100, 0.05);   // 5% dos elementos trocados
        QuaseOrdenado(250, 0.05);
        QuaseOrdenado(500, 0.05);
        QuaseOrdenado(1000, 0.05);
        QuaseOrdenado(5000, 0.05);
        QuaseOrdenado(10000, 0.05);
        
    }
}

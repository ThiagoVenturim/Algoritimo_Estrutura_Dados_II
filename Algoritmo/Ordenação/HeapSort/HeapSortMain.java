package Algoritmo.Ordenação.HeapSort;
import java.util.*;

class HeapSort {
    private int[] array;
    private int n;

    public HeapSort(int[] array) {
        this.array = array;
        this.n = array.length;
    }

    public void ordenar() {
        // Construir heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            recostruir(n, i);
        }

        // Ordenação
        for (int i = n - 1; i > 0; i--) {
            swap(0, i);
            recostruir(i, 0);
        }
    }

    private void recostruir(int tamHeap, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < tamHeap && array[esquerda] > array[maior]) {
            maior = esquerda;
        }

        if (direita < tamHeap && array[direita] > array[maior]) {
            maior = direita;
        }

        if (maior != i) {
            swap(i, maior);
            recostruir(tamHeap, maior);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void imprimir() {
        System.out.println(Arrays.toString(array));
    }
}

public class HeapSortMain {
    public static void main(String[] args) {
        int[] teste = {12, 11, 13, 5, 6, 7};
        HeapSort heapSort = new HeapSort(teste);

        System.out.println("Array original:");
        heapSort.imprimir();

        heapSort.ordenar();

        System.out.println("Array ordenado:");
        heapSort.imprimir();
    }
}

package Atividades.Selecao;

import java.util.Scanner;

class Selecao{
    private int vetor[];// variavies de instancia
    private int tamanho;

    public Selecao(){ } // contrutor sem nada

    public Selecao(int vetor[], int tamanho){  //contrutor
        this.tamanho=tamanho;
        this.vetor=vetor;
    }
    
    public int getTamanho(){return tamanho; } //get para retornar o  tamanho
    public void setVetor(int vetor[]){this.vetor=vetor;} 
    public void setTamanho(int tamanho){
        if(tamanho> 0){
            this.tamanho=tamanho;
        }
    }

    public void mostrarVetor(){
        for(int i =0; i<tamanho; i++){
            System.out.print( vetor[i] + " ");
        }
    }

    public void swap(int a , int b){
        int temp = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = temp;
    }   

    public void ordenar(){
        for(int i =0; i< (tamanho-1); i++){
            int menor= i;
            for(int j = (i+1); j<tamanho ; j++){
                if( vetor[j] < vetor[menor]){
                    menor = j;
                }
            }
            if(vetor[menor] != vetor[i]){
                swap(i, menor);
            }
        }
    }

}

public class SelecaoMain{
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        Selecao selecao = new Selecao();
        
        System.out.print("Digite o tamanho do seu vetor: ");
        int tamanho = scanner.nextInt();
        selecao.setTamanho(tamanho);
        
        int vetor[]= new int[tamanho];
        System.out.print(" Preencha o vetor com os numeros ");
        for(int i=0;i< tamanho; i++){
            vetor[i]= scanner.nextInt();
        }
        selecao.setVetor(vetor);

        selecao.ordenar();
        selecao.mostrarVetor();


        scanner.close();
    }
}
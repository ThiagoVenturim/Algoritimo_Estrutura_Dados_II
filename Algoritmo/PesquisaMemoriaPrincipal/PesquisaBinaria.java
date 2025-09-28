package Algoritmo.Pesquisa_Memoria_Principal;
import java.util.Scanner;

class Pesquisa{
    private int[] vetor;
    private int tamanho;

    public Pesquisa(){ }
    public Pesquisa(int vetor[],int tamanho){
        this.tamanho=tamanho;
        this.vetor = vetor; 
    }

    public void setTamanho(int tamanho){if(tamanho>0){this.tamanho=tamanho;}}
    public void setVetor(int[] vetor) { this.vetor = vetor; }
    public int getTamanho(){return tamanho;}

    public boolean verificador(int num){
        for( int esq=0, dir = this.tamanho - 1; esq<=dir ;){
            int meio = (esq + dir) / 2;
            if (this.vetor[meio] == num) {
                return true; // Número encontrado
            } else if (this.vetor[meio] < num) {
                esq = meio + 1; // Busca na metade direita
            } else {
                dir = meio - 1; // Busca na metade esquerda
            }
        }
        return false;
    }

    public boolean chamarRecursividade(int x){
        return verificadorRecursivo(vetor, x ,  tamanho-1, 0);
    }

    private boolean verificadorRecursivo(int []vetor, int x, int dir, int esq){
        if(esq>dir){
            return false;
        }else{
            int meio = (dir+esq)/2;
            if(vetor[meio]== x){
                return true;
            }else if(vetor[meio] < x ){
                return verificadorRecursivo(vetor, x, dir, meio+1);
            }else{
                return verificadorRecursivo(vetor, x,  meio-1,esq);
            }
        }
    }
}
public class PesquisaBinaria{
    public  static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        Pesquisa pesquisa = new Pesquisa();
        
        System.out.print("Digite o tamanho do vetor: ");
        int tamanho= scanner.nextInt();
        pesquisa.setTamanho(tamanho);
        
        int vetor[]= new int[tamanho];
        System.out.print("Preencha o vetor: ");
        for(int i = 0 ; i<tamanho; i++){
            vetor[i]= scanner.nextInt();
        }
        pesquisa.setVetor(vetor);

        System.out.print("Digite um número para pesquisar: ");
        int num = scanner.nextInt();
        System.out.println(pesquisa.verificador(num));  
        System.out.println("Recursivo"+ pesquisa.chamarRecursividade(num));
        scanner.close();
    }
}
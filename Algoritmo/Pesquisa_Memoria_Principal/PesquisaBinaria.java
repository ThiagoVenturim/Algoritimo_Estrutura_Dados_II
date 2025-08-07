package Algoritmo.Pesquisa_Memoria_Principal;
import java.util.Scanner;

class PesquisaBinaria{
    private int[] vetor;
    private int tamanho;

    public PesquisaBinaria(){
        this.tamanho = 0;
        this.vetor = new int[tamanho];
    }

    public void setVetorTamanho(int[] vetor) {
        this.vetor = vetor;
        this.tamanho = vetor.length;
    }

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
}
class testDrivePesquisaBinaria{
    public  static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        PesquisaBinaria pesquisa = new PesquisaBinaria();
        int[] vetor = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pesquisa.setVetorTamanho(vetor);
        System.out.print("Digite um número para pesquisar: ");
        int num = scanner.nextInt();
        System.out.println(pesquisa.verificador(num));  
    }
}
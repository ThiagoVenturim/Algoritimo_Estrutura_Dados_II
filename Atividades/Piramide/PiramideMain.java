package Atividades.Piramide;
import java.util.Scanner;

class Piramide{
    private char tipo;
    private int tamanho;

    public Piramide(){
        this.tipo= '*';
        this.tamanho=0;
    }

    public Piramide(char tipo, int tamanho){
        this.tamanho=tamanho;
        this.tipo=tipo;
    }

    public void setTamanho(int tamanho){ this.tamanho=tamanho; }
    public void setTipo(char tipo){ this.tipo=tipo;}

    public int getTamanho(){return this.tamanho;}
    public int getTipo(){return this.tipo;}

    void mostrarPiramide(){
    for (int i = 0; i < this.tamanho; i++) {
            // Espaços antes dos caracteres
            for (int j = 0; j < this.tamanho - i - 1; j++) {
                System.out.print(" ");
            }

            // Caracteres da pirâmide
            for (int k = 0; k < (2 * i + 1); k++) {
                System.out.print(this.tipo);
            }

            // Quebra de linha no final da linha da pirâmide
            System.out.println();
    }
}


}

class PiramideMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Piramide p = new Piramide();
        int tamanho = scanner.nextInt();
        char tipo = scanner.next().charAt(0);
        p.setTamanho(tamanho);
        p.setTipo(tipo);

        p.mostrarPiramide();

        scanner.close();
    }
}
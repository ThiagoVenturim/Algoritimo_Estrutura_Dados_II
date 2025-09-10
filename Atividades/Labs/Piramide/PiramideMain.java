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

    public void mostrarPiramide(){
    System.err.println("");
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

    public void mostrarPiramideInvertidade(){
    System.err.println("");
        for(int i = 0; i < this.tamanho; i++){
            for(int j = 0; j!=i; j++){
                System.out.print(" ");
            }

            for(int k= ((tamanho-i)*2)-1; k>0; k--){
                System.out.print(tipo);
            }
            System.out.println("");
        }
    }

    public void mostrarMeiaPiramide(){
        System.out.println("");
        for(int i=0; i<tamanho; i++){
            for(int j = 0; j<i+1; j++){
                System.out.print(tipo);
            }
            System.out.println("");
        }
    }

    public void mostrarMeiaPiramideInvertida(){
        System.out.println("");
        for(int i =0; i<tamanho; i++){
            for(int j=tamanho; j>i; j--){
                System.out.print(tipo);
            }
            System.out.println("");
        }
    }

}


class PiramideMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------Gerador de Piramide---------------------");
        System.out.println("                 DIGITE SIM PARA COMEÇAR");
        System.out.println("                 DIGITE NAO PARA SAIR");
        String entrada = scanner.nextLine();

        if(entrada.equals("SIM") == true){
            System.out.println("BEM VINDO!");
            System.out.println("Vamos setar os valores.");
            System.out.print("Digite um numero inteiro para o tamanho: ");
            int tamanho = scanner.nextInt();

            System.out.println("");
            System.out.print(" Digite um simbolo: ");
            char tipo = scanner.next().charAt(0);
            Piramide p = new Piramide(tipo, tamanho);
            System.out.println("");

            while(true){

                System.out.println("1- Trocar valores");
                System.out.println("2- Mostrar Piramide");
                System.out.println("3- Mostrar Piramide  Inversa"); 
                System.out.println("4- Mostrar  Meia Piramide ");
                System.out.println("5- Mostrar  Inversa Piramide ");
                
                int ent= scanner.nextInt();


                switch (ent) {
                    case 1:
                            System.out.println("Digite o tamanho: ");
                            tamanho= scanner.nextInt();
                            p.setTamanho(tamanho);

                            System.out.println("Digite o tipo: ");
                            tipo = scanner.next().charAt(0);
                        break;
                    case 2:
                            p.mostrarPiramide();
                        break;
                    case 3:
                        p.mostrarPiramideInvertidade();
                        break;

                    case 4:
                            p.mostrarMeiaPiramide();
                        break;
                     case 5:
                            p.mostrarMeiaPiramideInvertida();
                        break;
                    default:
                        System.out.println("Numero Invalido Digite Novamente");
                    break;
                }
            }
        }
    scanner.close();
    }
}
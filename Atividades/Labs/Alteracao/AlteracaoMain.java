
import java.util.Scanner;
import java.util.Random;

//Classe Onde Vamos Contruir o Metodo Pedido
class Alteracao{
    private String str; 

    public Alteracao(){ str = " "; } //contrutores
    public Alteracao(String str){this.str =str; }

    public String getStr(){return str; } 
    public void setStr(String str){this.str=str;}

    public void chamarAleracao(char a, char b){ // recebe os dois char gerados e chama o metodo fazerAleteração
        System.out.println(fazerAleracao(a, b));
    }

    private String fazerAleracao(char a, char b){ // Metodo que Faz a Alteração Aleatória

        StringBuilder formatacao = new StringBuilder(); 
        char resultado; 

        for(int i=0; i< str.length() ; i++){
            if(str.charAt(i)== a){
                resultado = b;    
            }else{
                 resultado = str.charAt(i);
            }
            formatacao.append(resultado);
        }
        return formatacao.toString();
    }

}

public class AlteracaoMain{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Random gerador = new Random();
        gerador.setSeed(4);

        Alteracao alteracao = new Alteracao();


        String str;

        do {
            str = scanner.nextLine();
            if (str.length() == 3 && 
                str.charAt(0) == 'F' && 
                str.charAt(1) == 'I' && 
                str.charAt(2) == 'M') {
                break; // leitura termina em "FIM"
            }
            char a = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            char b = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

            alteracao.setStr(str); //setar a string que o usurario digitou
            alteracao.chamarAleracao(a, b); // chamo o metodo que faz a alteracao


        } while (true);

        scanner.close(); //fechar  o scanner
    }

}
package Atividades.Labs.Inverter;
import java.util.Scanner;

//Classe onde vou fazer o metodo
class Inverter{
    private String str;
    
    //Metodos Construtores
    public Inverter(){str= " ";}
    public Inverter(String str){this.str=str;}
    
    //Metodos Set e Get
    public void setStr(String str){this.str=str;}
    public String getStr(){return str;}

    //Metodo que chama a funcao Inverter com o tamanho e a string
    public void chamarInverter(){
        inverter(str, str.length()-1);
    }
    
    //Metodo inverter que inverte a string
    private void inverter(String str, int tamanho){
        StringBuilder s = new StringBuilder();
        for(int i = tamanho;  i>=0; i--){
            s.append(str.charAt(i));
        }
        System.out.println(s.toString());
    }
    
}

public class InverterMain{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Inverter in = new Inverter();
        String str;
        do {
            str = scanner.nextLine();
            if (str.length() == 3 && 
                str.charAt(0) == 'F' && 
                str.charAt(1) == 'I' && 
                str.charAt(2) == 'M') {
                break; // leitura termina em "FIM"
            }

            in.setStr(str);
            in.chamarInverter();
        } while (true);
        scanner.close();
    }
}
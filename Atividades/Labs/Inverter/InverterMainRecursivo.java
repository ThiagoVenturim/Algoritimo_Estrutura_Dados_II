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
        StringBuilder stringbuilder = new StringBuilder();
        int index=0;
        if(str.length()!=0){
            index =str.length()-1;
        }
        System.out.println("Teste: " + inverter( stringbuilder,index,  str) +"\n" );
  
    }
    
    //Metodo inverter que inverte a string
    
    private String inverter(StringBuilder novaBuilder, int tamanho, String str){
        
        if(  tamanho == 0){
            novaBuilder.append(str.charAt(tamanho));
            return  str= novaBuilder.toString();
        }else{
            novaBuilder.append(str.charAt(tamanho));
            tamanho--;
            return inverter( novaBuilder,  tamanho,  str);
        }
    
    }
}
public class InverterMainRecursivo{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Inverter in = new Inverter();
        String str;
        do {
            str = MyIO.readLine();

            MyIO.print("chega aqui" + str);

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
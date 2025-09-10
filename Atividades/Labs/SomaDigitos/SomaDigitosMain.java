import  java.util.Scanner;

class SomaDigitos{
    private int num;

    public SomaDigitos(){num = 0 ;} 
    public SomaDigitos(int num){this.num=num;}

    public void setNum(int num){this.num=num;}
    public int getNum(){return num;}
    
    public void chamarMostrar(){
        System.out.println(fazerOperacao(num));
    }

    private int fazerOperacao(int num){
        if(num<=0){
            return 0;
        }else{
           
            return num%10 + fazerOperacao(num/10);
        }
    }

}

public class SomaDigitosMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        SomaDigitos somadigitos= new SomaDigitos();

        while ( scanner.hasNextInt()){
            int num = scanner.nextInt();
            somadigitos.setNum(num);
            somadigitos.chamarMostrar();

        }       
        
        scanner.close();
     }

}

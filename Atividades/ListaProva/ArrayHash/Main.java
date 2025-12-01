import java.util.*;



public class Main{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        for(int i=0; i<x; i++){
            int k = scanner.nextInt();
            scanner.nextLine();
            int soma=0;
            for(int j = 0 ; j<k; j++){
                String str = scanner.nextLine();
                for(int l=0; l<str.length();l++){
                    if(str.charAt(l) >=65 && str.charAt(l) <=90){
                        soma += ((int) str.charAt(l)) -65 + j +l;   
                    }
                }
            }
            System.out.println(soma);
        }
        scanner.close();
    }
}
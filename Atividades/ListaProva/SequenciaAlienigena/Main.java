import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
         String [] array = {"B", "BA","CB","BAA","BCB","CBA","DAB", "BAAA"};
        int x= scanner.nextInt();
        while(x!=0){
                x-=1;
                int pos = x%8 ;  
                for(int i=0; i <x/8; i++ )
                System.out.println( array[pos]);
               x= scanner.nextInt();
        }
    }
}
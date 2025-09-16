import java.util.Scanner;


class Duendes{
    private String array[];

    public Duendes(int tamanaho){
        array = new String[tamanaho];
    
    }

    public void setArray(int index, String str){
        array[index] = str;
    }


    public void mostrarDuendes(){
        for(int i=0; i<array.length;i ++){
            System.out.println(array[i]);
        }
    }

    public void ordenarDuendes(){
        if( array.length < 0) {System.exit(0);} 
        for(int i =0; i<array.length-1; i++){
            int maior=i;
            for(int j= i+1; j<array.length; j++){
                if(ehMenor(array[maior], array[j])){
                    maior =  j; 
                }
            }
            if(maior != i){
                swap(maior, i);
            }
        }
    }

    public boolean ehMenor(String d1, String d2){
    int idadeD1=0;
    int  tamanaho = d1.length()-1;
        for(int i= tamanaho, aux =1; i>=0; i--, aux*=10){
            if(d1.charAt(i) == ' '){
                break;
            }
            if(d1.charAt(i) >='1' && d1.charAt(i) <='9'){
                idadeD1+=  (d1.charAt(i) -'0') * aux;
            }
        }

    tamanaho = d2.length()-1;
     int idadeD2=0;
        for(int i= d2.length()-1, aux = 1; i>=0; i--, aux*=10){
            if(d2.charAt(i) == ' '){
                break;
            }
            if(d2.charAt(i) >='1' && d2.charAt(i) <='9'){
                idadeD2+=  ( d2.charAt(i) -'0') * aux;
            }
        }
        
        //System.out.println( "Nome: "+ d1 + " Nome: "+ d2  );
        if(idadeD1<idadeD2){return true;}
        else if(idadeD1 == idadeD2 ){
            for(int i= 0 ; i< d1.length() && i< d2.length(); i++){
                if(d1.charAt(i) <d2.charAt(i)){
                    //System.out.println("opcao maior eh " + d2 + d1.charAt(i) + "  " + d2.charAt(i));
                    return true;
                }else if(d1.charAt(i)> d2.charAt(i)) {
                    break;
                }
            }
        }
        return false;
    }

    public void swap(int i, int j){
        String temp =  array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void montarEquipes(){
        
        for(int i = 0; i <  array.length/3 ; i++){
            System.out.println("Time " + (i+1));
            for(int j = i  ; j < array.length ; j+=array.length/3){
                System.out.println(array[j]);
            }
             System.out.println();
        }
    }
}

public class DuendesMain{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String entrada;

        scanner.nextLine();
        if( num%3==0){
            Duendes duendes = new Duendes(num);
            for(int i=0; i<num; i++){
                entrada = scanner.nextLine();
                duendes.setArray(i, entrada);
            }
             
            duendes.ordenarDuendes();
            duendes.mostrarDuendes(); 
            //System.out.println();
            duendes.montarEquipes();
        }
        
        scanner.close();
    }
}
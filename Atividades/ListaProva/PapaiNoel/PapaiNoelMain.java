//package Atividades.ListaProva.PapaiNoel;
import java.util.Scanner;


class PapaiNoel{

    private String[] array;
    private int tamanaho;
    private char[] simbolo;

    public PapaiNoel(int tamanaho){
        this.array= new String[tamanaho];
        this.tamanaho = array.length;
        this.simbolo = new char[tamanaho]; 
    }

    public void setString(String str, int index, char simbolo){
        StringBuilder nova = new StringBuilder();
        for(int i =0; i< str.length() ;i++){
            if(str.charAt(i) != ' '){ nova.append(str.charAt(i));}
        }
        this.array[index] = nova.toString();
        this.simbolo[index] = simbolo;
    }

    public void meusMetodos(){ 
        //System.out.println("Chamar para ordenar");
        selecao();

        mostrar();
        
    }

    private void swap(int i, int j){
        String temp = array[i];
        array[i] =  array[j];
        array[j] = temp;
    }

    private void selecao(){
        int menor;
        String str1, str2;

        for(int i = 0; i<tamanaho+1; i++){
            menor = i ;

            for(int j=i+1; j<tamanaho; j++){
                str1 = array[menor];
                str2= array[j];
                //System.out.println(" Comparando"+ str1 + " com" + str2);

                for(int k = 0; k < str1.length() && k < str2.length();k++ ){
                    char var1= str1.charAt(k), var2 = str2.charAt(k);
                    //System.out.println(" Olhando o caract" + var1 + "com "+ var2);
                    if( var1 > var2){
                        //System.out.println(" Variavel 2 maior que a 1");
                        menor = j;
                    }
                    if(var1 != var2){
                        break;
                    }
                }   
            }

            if(menor != i){
               // System.out.println("Trocando variavel " + array[menor]+ "com " + array[i]);
                swap(menor, i);
            }

        }
    }


    private void mostrar(){
    int mais=0, menor =0;
        for(int i =0; i<tamanaho; i++){
            
            System.out.println(array[i]);
            if(simbolo[i]== '+'){
                mais++;
            }else{
                menor++;
            }
        }
        System.out.println("Se comportaram: "+ mais + " | Nao se comportaram: "+menor);
    }
}

public class PapaiNoelMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        String str;
        int num = scanner.nextInt();
        char simbolo;

        if( num >= 0 && num <= 100){
            PapaiNoel noel = new PapaiNoel(num);
            for(int i=0; i<num; i++){
                simbolo = scanner.next().charAt(0);
                str = scanner.nextLine();

                if(str.length()<=20){
                    noel.setString(str, i, simbolo);
                }

            }
            noel.meusMetodos();
        }
        scanner.close();
    }
}
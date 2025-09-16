import java.util.Scanner;

//Classe onde vou fazer o metodo
class Inverter{
    private String str;
    private char[] vetor;   
    
    //Metodos Construtores
    public Inverter(){
        str= "";
        // vetor = new char [str.length()];
    }
    public Inverter(String str){
        this.str=str;
       // vetor = new char [str.length()];
       /* for(int i = 0; i<str.length(); i++){
            vetor[i]= str.charAt(i);
        }*/
    }
    
    //Metodos Set e Get
    public void setStr(String str){
        this.str=str;
        /*vetor = new char [this.str.length()];
        for(int i = 0; i<str.length(); i++){
            vetor[i]= str.charAt(i);
        }*/
    }
    public String getStr(){return str;}

    //Metodo que chama a funcao Inverter com o tamanho e a string
    public void chamarInverter(){
        StringBuilder stringbuilder = new StringBuilder();
        int index=0;
        if(str.length()!=0){
            index =str.length()-1;
        }
         str = inverter( stringbuilder,index,  str) ;
        System.out.print(str +"\n" );
        //MyIO.print(str +"\n" );

        //inverterChar( 0, index);
        //mostrar();
  
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

    /*private void inverterChar (int index, int tamanho){
        if(index>tamanho){
            return;
        }else{
            if(vetor[index] != vetor[tamanho]){ 
             swap(index, tamanho);
            }
        inverterChar ( index+1,  tamanho-1);
        }
    }

    private void mostrar(){
        for(int i=0; i<vetor.length ; i++){
            System.out.print(vetor[i]);
        }
        System.err.println("");
    }

    private void swap(int index, int tamanho){
        char temp = vetor[index];
        vetor[index] =   vetor[tamanho];
        vetor[tamanho] =   temp;
    }*/

    

}
public class InverterMainRecursivo{
    public static boolean verdade(String str){
    if (str.length() == 3 && 
                str.charAt(0) == 'F' && 
                str.charAt(1) == 'I' && 
                str.charAt(2) == 'M') {
                return false;
            }
        return  true;
    }
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Inverter in = new Inverter();
        String str;
        //str = MyIO.readLine();

        str = scanner.nextLine();
        while (verdade(str)) {

            in.setStr(str);
            in.chamarInverter();
            //str = MyIO.readLine();

            str = scanner.nextLine();
        }
        scanner.close();
    }
}
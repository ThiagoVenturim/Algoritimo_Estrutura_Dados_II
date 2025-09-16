import java.util.Scanner;

class LabelNoel{
    private String linguas[];
    private String traducao[];

    public LabelNoel(int tamanaho){
        linguas = new String[tamanaho];
        traducao = new String[tamanaho];
    }

    public void setLinTra(int index, String str, String str1){
        linguas[index]= str;
        traducao[index]= str1;
      //  System.out.println("Adicionando linguas "+ str + " no index" + index  + "na traducao "+ str1);
    }
    
    public String Traducao( String str){
      //   System.out.println(str+ " procurando");
        int pos=0;
        for(int i=0; i<linguas.length; i++){
           // System.out.println("Peocurando na"+  linguas[i]);
            if(linguas[i].equals(str)){
                pos=i;
                break;
            }
        }

    return  traducao[pos];
    }

    public void mostrar(){
        for(int i= 0; i<linguas.length; i++){
            System.out.println("Lingua "+  linguas[i] + " traducao "+traducao[i] +" no index" + i );
        }
    }
}

public class LabelNoelMain{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        scanner.nextLine();
        if(num>1 && num< 100){

        LabelNoel noel = new LabelNoel(num);

        String ling,trad ;
        for(int i = 0; i<num; i++){
            ling = scanner.nextLine();

            trad = scanner.nextLine();
            noel.setLinTra(i, ling, trad);

        }
        
        num = scanner.nextInt();
        scanner.nextLine();
        for(int i =0 ;i <num; i++){

            ling = scanner.nextLine();
            System.out.println(ling);

            trad = scanner.nextLine();
            System.out.println(noel.Traducao(trad)+ "\n");
        }
       // noel.mostrar();
    }
    scanner.close();
    }
}
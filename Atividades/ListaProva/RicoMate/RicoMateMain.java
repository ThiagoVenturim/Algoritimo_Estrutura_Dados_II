import java.util.Scanner;
import java.util.Locale;

class RicoMate{

    private int tamanho;
    private float litro;
    private float copo;
    private String[] nomes;

    public RicoMate(int tamanho){
        this.tamanho = tamanho;
        nomes= new String[tamanho];
    }

    public void setLitros(float litro){this.litro = litro;}
    public void setCopo(float copo){this.copo= copo;}

    public void setNomes(String nome){
    int j=0;
        for(int i=0; i<tamanho; i++, j++){
            StringBuilder str = new StringBuilder();
            for(; j < nome.length() && nome.charAt(j) != ' ' ; j++ ){
                str.append(nome.charAt(j));
            }
            nomes[i]= str.toString();
        }
    }

    public void mostrarNomes(){
        for(int i=0; i<tamanho; i++){
            System.out.println(nomes[i]);
        }
    }
    
    public void reiMate(){
        int aux = (int)(litro/copo);
        int i= aux%tamanho;
        float resto = litro- copo*aux ;
        
        System.out.printf("%s %.1f", nomes[i], resto);
        
    
    }
}

public class RicoMateMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        
        int entrada = scanner.nextInt();
        float  litro = scanner.nextFloat();
        float copo = scanner.nextFloat();
        scanner.nextLine();
        
        RicoMate rc = new RicoMate (entrada);
        rc.setLitros(litro);
        rc.setCopo(copo);
        
        String str = scanner.nextLine();

        rc.setNomes(str);
        rc.mostrarNomes();
        rc.reiMate();


        scanner.close();
    }
}
import java.util.Scanner;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    
    public void reiMate(DecimalFormat df){

        int aux = (int)(litro/copo);
        int i= aux%tamanho;
        float resto = litro- copo*aux ;
        String formatado = String.format("%.1f", resto);
       
        System.out.println(nomes[i] +" " + df.format(resto));
        
    
    }
}

public class RicoMateMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        
        int entrada = scanner.nextInt();
        float  litro = scanner.nextFloat();
        float copo = scanner.nextFloat();
        scanner.nextLine();
        
        RicoMate rc = new RicoMate (entrada);
        rc.setLitros(litro);
        rc.setCopo(copo);
        
        String str = scanner.nextLine();

        rc.setNomes(str);
        //rc.mostrarNomes();
        rc.reiMate(df);


        scanner.close();
    }
}
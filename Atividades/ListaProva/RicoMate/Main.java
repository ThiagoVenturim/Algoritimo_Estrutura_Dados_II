import java.util.*;

class Participante{
    private String[] name;
    private int tamanho;
    private float litros;
    private float consumo;
    
    public Participante(int tamanho, String[] name, float litros, float consumo){
        this.tamanho= tamanho;
        this.name = new String[tamanho];   
        this.name = name;
        this.litros = litros;
        this.consumo= consumo;
    }
    
    public void setTamanho(int tamanho){this.tamanho= tamanho;}
    public void setName(String[] name){ this.name = name;  }
    public void setLitros(float litros){this.litros = litros;}
    public void setConsumo(float consumo ){this.consumo= consumo;}

    public int getTamanho(){return tamanho; }
    public float getConsumo(){return consumo; }
    public float getLitros(){return litros;}
    public String[] getName(){return name;}
    

    public void ganhou(){
        int ganhador = 0;
        while (consumo < litros){
            if(ganhador==3){
                ganhador=0;
            }
            litros -= consumo;
            ganhador++;
        }
       System.out.printf("%s %.2f",name[ganhador] , litros);
    } 
}
   

 class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int tamanho = scanner.nextInt();
        float litros = scanner.nextFloat();
        float consumo = scanner.nextFloat();
        String []names= new String[tamanho];

        for(int i=0; i<tamanho; i++){
            names[i]= scanner.next();
        }

        Participante part = new Participante(tamanho, names, litros, consumo);
        part.ganhou();
        
        scanner.close() ;
    }
}
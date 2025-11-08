import java.util.Scanner;

class Placa{
    private String placa;

    public Placa(){ placa = " ";}
    public Placa(String placa){ this.placa = placa;}

    public void setPlaca(String placa){this.placa = placa;}
    public String getPlaca(){return placa;}

    public void verificarPlaca(){
        int reposta=1 ;
        if(placa.length()>8 || placa.length()<7 ){ reposta = 0; 
        }else if(placa.length()==8){    
            reposta = 1;
            for(int i =0; i<placa.length(); i++){
                if(i<3){
                    if(placa.charAt(i) < 'A' || placa.charAt(i) >'Z' ){
                        reposta=0;
                        
                    }
                }else if(i==3){
                    if(placa.charAt(i) != '-'){
                        reposta=0;
                       
                    }
                }else if( i>=4){
                    if(placa.charAt(i) < '0' || placa.charAt(i) >'9' ){
                        reposta=0;
                      
                    }
                }
            }
        }else if(placa.length()==7){
             reposta = 2;
            for(int i =0; i<placa.length(); i++){
                if(i<3){
                    if(placa.charAt(i) < 'A' || placa.charAt(i) >'Z' ){
                        reposta=0;
                    }
                }else if(i==3 || i>4){
                    if(placa.charAt(i) < '0' || placa.charAt(i) >'9' ){
                        reposta=0;
                    }
                }else if( i==4){
                    if(placa.charAt(i) < 'A' || placa.charAt(i) >'Z' ){
                        reposta=0;
                    }
                }
            }
        } 
        System.out.println(reposta);
        return;
    }
}

public class PlacaMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Placa p = new Placa();
        String str = scanner.nextLine();
        p.setPlaca(str);
        p.verificarPlaca();

        scanner.close();
    }
}
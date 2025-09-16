import java.util.*;

class TorneioTenis{
    private String []sequencia;

    public TorneioTenis(){
        sequencia= new String[6];
    }

    public void setSequencia(String um, int index){
        sequencia[index] = um; 
    }

    public void contar(){
        int vitoria= 6;
        for(int i=0; i<6; i++){
            if(sequencia[i].charAt(0) == 'P'){
                vitoria--;
            }
        }
        if(vitoria >=5){
            vitoria= 1;
        }else if(vitoria >=3){
            vitoria =2;
        }else if(vitoria >=1){
            vitoria =3;
        }else{
            vitoria= -1;
        }

        System.out.println(vitoria);

    }
}

public class TorneioTenisMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str;
        TorneioTenis tor = new TorneioTenis();
        for(int i = 0; i<6; i++){
            str  = scanner.nextLine();
            tor.setSequencia(str, i);
        }   
        tor.contar();
        scanner.close();
    }
}
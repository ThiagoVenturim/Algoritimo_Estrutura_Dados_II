package Atividades.Maiusculo;
import java.util.Scanner;

class Maiusculo{
    private String str;

    public Maiusculo() {  this.str = ""; }
    public Maiusculo(String str) { this.str = str; }

    public void setStr(String str){ this.str = str; }

    public int contarMaiusculoRecursivo(String str) {
        if (str.isEmpty()) {
            return 0;
        } else if (Character.isUpperCase(str.charAt(0))) {
            return 1 + contarMaiusculoRecursivo(str.substring(1));
        }
        return contarMaiusculoRecursivo(str.substring(1));
    }


    public int contarMaiusculoIterativo(){
        int cont = 0;
        for(int i = 0; i < this.str.length(); i++) {
            if(Character.isUpperCase(str.charAt(i))) {
                cont++;
            }
        }
        return cont;
    }
}

public class MaiusculoMain{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maiusculo maiusculo = new Maiusculo();

        while(true) {
            String input = scanner.nextLine();
            if(input.equals("FIM")) {
                break;
            }
            maiusculo.setStr(input);
            System.out.println("Recursivo: " + maiusculo.contarMaiusculoRecursivo(input));
            System.out.println("Iterativo: " + maiusculo.contarMaiusculoIterativo());
        }
        scanner.close();
    }
}

package Atividades.Labs.Is; 
import java.util.Scanner;

class Is{

    private String str;

    public Is(){str = "";}
    public Is(String str){this.str=str; }

    public void setStr(String str){this.str=str; }
    public String getStr(){return str;}

    // Ordem correta: vogais, consoantes, inteiro, real
    public void chamarMetodo(){
        simNao(testVogais());
        simNao(testConsoante());
        simNao(testInteger());
        simNao(testReal());
        System.out.println("");
    }   

    private void simNao(boolean teste){
        if(teste){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }
    }

    // Verifica se a string contém apenas vogais (A-Z, a-z sem acento)
    private boolean testVogais(){
        if(str.length() == 0) return false;
        boolean resp = true;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);

            // rejeita caracteres especiais (não letra)
            boolean letraMaius = (c >= 'A' && c <= 'Z');
            boolean letraMinus = (c >= 'a' && c <= 'z');
            if(!(letraMaius || letraMinus)){
                resp = false;
                break;
            }

            // precisa ser vogal
            if(!(c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||
                 c=='a'||c=='e'||c=='i'||c=='o'||c=='u')){
                resp = false;
                break;
            }
        }
        return resp;
    }

    // Verifica se a string contém apenas consoantes (A-Z, a-z sem acento e não vogal)
    private boolean testConsoante(){
        if(str.length() == 0) return false;
        boolean resp = true;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);

            // rejeita caracteres especiais (não letra)
            boolean letraMaius = (c >= 'A' && c <= 'Z');
            boolean letraMinus = (c >= 'a' && c <= 'z');
            if(!(letraMaius || letraMinus)){
                resp = false;
                break;
            }

            // rejeita se for vogal
            if(c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||
               c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
                resp = false;
                break;
            }
        }
        return resp;
    }

    // Verifica se é inteiro (apenas dígitos)
    private boolean testInteger(){
        if(str.length() == 0) return false;
        boolean resp = true;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c < '0' || c > '9'){
                resp = false;
                break;
            }
        }
        return resp;
    }

    // Verifica se é real (dígitos e no máximo um separador . ou ,)
    private boolean testReal(){
        if(str.length() == 0) return false;
        boolean resp = true;
        boolean temSeparador = false;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);

            if(c >= '0' && c <= '9'){
                continue;
            } else if(c == '.' || c == ','){
                if(temSeparador){
                    resp = false;
                    break;
                }
                temSeparador = true;
            } else {
                resp = false;
                break;
            }
        }
        return resp;
    }

}

public class IsMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str;
        Is is = new Is();
        
        do {
            str = scanner.nextLine();
            if (str.equals("FIM")) {
                break; // leitura termina em "FIM"
            }

            is.setStr(str);
            is.chamarMetodo();           
        } while (true);

        scanner.close();
    }
}

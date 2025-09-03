
import java.util.Scanner;
class Anagrama{
    private String palavra1;
    private String palavra2;

    public Anagrama(){
        this.palavra1 = "";
        this.palavra2 = "";
    }

    public Anagrama(String palavra1, String palavra2){
        this.palavra1 = palavra1;
        this.palavra2 = palavra2;
    }

    public void setPalavras(String palavra){ // metodo que recebe a string e separa as palavras
    boolean primeiraPalavra = true;
    StringBuilder p1 = new StringBuilder();
    StringBuilder p2 = new StringBuilder();
        for(int i= 0; i<palavra.length(); i++){
            if(palavra.charAt(i) == ' ' || palavra.charAt(i)== '-'){
              primeiraPalavra = false;
            } else {
                if(primeiraPalavra){
                    p1.append(palavra.charAt(i));
                } else {
                    p2.append(palavra.charAt(i));
                }
            }
        }
        this.palavra1 = p1.toString();
        this.palavra2 = p2.toString();
    }

    public void chamarMetodo(){ //chamar o metodo que verifica se é anagrama 
        if(metodoAnagrama(palavra1, palavra2)){
            System.out.println("SIM");
        } else {
            MyIO.print("NÃO\n");            
        }
    }

    
    private boolean metodoAnagrama(String palavra1, String palavra2) { //metodo que verifica se é anagrama 
        if (palavra1.length() != palavra2.length()) {return false;  } 

        StringBuilder p2 = new StringBuilder(palavra2);

        for (int i = 0; i < palavra1.length(); i++) {
            char c = Character.toLowerCase(palavra1.charAt(i));
            boolean achou = false;
            
            for (int j = 0; j < p2.length(); j++) {
                if (c == Character.toLowerCase(p2.charAt(j))) {
                    p2.deleteCharAt(j); // remove o caractere encontrado
                    achou = true;
                    break;
                }
            }

            if (!achou) { // se não achou o caractere correspondente
                return false;
            }
    }

    return true; // se removeu todos, são anagramas
    }
    
}

class AnagramaMain{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Anagrama anagrama = new Anagrama();
        String palavra;
        do {
                palavra = scanner.nextLine();
                if (palavra.length() == 3 && 
                    palavra.charAt(0) == 'F' && 
                    palavra.charAt(1) == 'I' && 
                    palavra.charAt(2) == 'M') {
                    break; // leitura termina em "FIM"
                }

                anagrama.setPalavras(palavra);

                anagrama.chamarMetodo();
            } while (true);       
            scanner.close();
        }
}

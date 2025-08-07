import java.util.Scanner;

class Palindromo{
    private String frase;

    public Palindromo(){
        this.frase = " ";
    }

    public Palindromo(String frase){
        this.frase = frase;
    }

    public void setFrase(String frase) {  this.frase = frase; }
    
    public void VerificarIterativo(){
        frase = frase.toLowerCase(); // usar tolower para deicar todas as letras minusculas
        int tamanho= frase.length(); // passar o tamanho da string
        for(int i =0; i< tamanho/2 ; i++ ){ //inicio do loop para verificar 
            if(frase.charAt(i) != frase.charAt(tamanho -1 - i) ){
                System.out.println("NAO");
                return;
            }
        }
    System.out.println("Sim");
    return;
    }
}

class TestDrivePalindromo{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Palindromo palindromo = new Palindromo();
        String frase;
        do{
            frase = scanner.nextLine();
            if(frase.compareToIgnoreCase("FIM")==0 ){ break;  }
            palindromo.setFrase(frase);
            palindromo.VerificarIterativo();
        }while(true);
    }
}
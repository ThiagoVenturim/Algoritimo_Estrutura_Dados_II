import java.util.Scanner;

// Classe que implementa a cifra de César
class Cesar {
    private String palavra; // Armazena a palavra a ser cifrada
    
    // Construtor inicializa a palavra como string vazia
    public Cesar() { 
        palavra = ""; 
    }

    // Método para definir a palavra
    public void setPalavra(String palavra) { 
        this.palavra = palavra; 
    }

    // Método para obter a palavra
    public String getPalavra() { 
        return palavra; 
    }
    
    // Método que aplica a cifra de César à palavra
    public void cifraCesar() {
        StringBuilder cifrada = new StringBuilder(); // Para construir a string cifrada

        // Percorre cada caractere da palavra
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i); // Pega o caractere atual

            // Cifra apenas caracteres ASCII imprimíveis (32 a 126)
            if (c >= 32 && c <= 126) {
                int posicao = c - 32; // Ajusta a posição para 0-94
                int novaPosicao = (posicao + 3) % 95; // Aplica o deslocamento da cifra
                c = (char) (32 + novaPosicao); // Converte de volta para caractere
            }

            cifrada.append(c); // Adiciona o caractere cifrado à string final
        }

        // Imprime a palavra cifrada
        System.out.println(cifrada.toString());
    }
}

// Classe principal para executar o programa
public class CesarMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8"); // Scanner para ler entrada com suporte a acentos
        Cesar c = new Cesar(); // Cria objeto da classe Cesar

        // Loop para ler várias palavras até o usuário digitar "FIM"
        while (true) {
            String palavra = scanner.nextLine(); // Lê a palavra do usuário
            if (palavra.equalsIgnoreCase("FIM")) { // Verifica se é "FIM" (encerra)
                break;
            }
            c.setPalavra(palavra); // Define a palavra no objeto
            c.cifraCesar(); // Aplica a cifra de César e imprime
        }   

        scanner.close(); // Fecha o scanner
    }
}

import java.util.Scanner;

class Test{ 
public String recursividade(String str, int index){
    if (index < 0 || index >= str.length()) {
        return str;
    }

    if(str.length() == 1){
        return str;
    }else if(str.charAt(index)== '('){
        str = resolverExpressao(str, index);
        return recursividade(str, str.length()-1);
    }else{
        return recursividade(str, index-1);
    }
}


public String resolverExpressao(String str, int index){
    77System.out.println("Resolvendo : '(' em " + index);

    StringBuilder novaStr = new StringBuilder();

    // pegar nome do operador
    int start = index - 1;
    while (start >= 0 && Character.isLetter(str.charAt(start))) {
        start--;
    }
    StringBuilder auxBuilder = new StringBuilder();

    for(int i = start + 1; i < index; i++){
        auxBuilder.append(str.charAt(i));
    }
    String operador = auxBuilder.toString();
    // encontrar o fechamento do parêntese
    int j = index;
    int parenteses = 1;
    while (parenteses > 0 && ++j < str.length()) {
        if (str.charAt(j) == '(') parenteses++;
        else if (str.charAt(j) == ')') parenteses--;
    }
    System.out.println("Operador: " + operador + ", índice fechamento: " + j);
    String conteudo = str.substring(index + 1, j); // argumentos entre parênteses
    char operacao = '0';

    switch(operador){
        case "and":
            operacao = '1';
            for (char c : conteudo.toCharArray()) {
                if (c == '0') { operacao = '0'; break; }
            }
            break;

        case "or":
            operacao = '0';
            for (char c : conteudo.toCharArray()) {
                if (c == '1') { operacao = '1'; break; }
            }
            break;

        case "not":
            operacao = (conteudo.trim().charAt(0) == '0') ? '1' : '0';
            break;
    }

    // reconstruir string substituindo a expressão por '0' ou '1'
    novaStr.append(str.substring(0, start + 1));
    novaStr.append(operacao);
    novaStr.append(str.substring(j + 1));

    System.out.println("Substituindo " + operador + " por " + operacao);
    System.out.println("Nova string: " + novaStr);

    return novaStr.toString();
}

public String formatarLinha(String linha, boolean[] variaveis){ // formatar linhas das Variaveis por 0 ou 1
        StringBuilder str = new StringBuilder();
        
        for(int i =0; i< linha.length(); i++){
            if(linha.charAt(i)!= ' '){
                if(linha.charAt(i) >= 'A' && linha.charAt(i) <= 'Z'){
                    str.append(variaveis[linha.charAt(i)-65] ? '1' : '0');
                }else{
                    str.append(linha.charAt(i));
                }
            }
        }
    return str.toString();
    }


}

public class TestMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Test teste = new Test();
        int num;
          while( (num = scanner.nextInt())!= 0){ // loop para rodar ate a condicição inicial ser 0
           
            boolean[] variavies = new boolean[num]; // array de variaveis
           
            for(int i =0; i<num; i++){
                variavies[i] = scanner.next("[0-9]").charAt(0)  != '0';
            }

            String linha = scanner.nextLine(); // le a expressao
             linha = teste.formatarLinha( linha,  variavies);
        System.out.println(teste.recursividade(linha, linha.length()-1));
        }
        scanner.close();
    }
}
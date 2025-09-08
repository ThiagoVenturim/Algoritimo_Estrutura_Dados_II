import java.util.Scanner;


class Algoritmo{

    private boolean []variaveis;
    private String linha;

    public Algoritmo(){ //contrutor padrao
        this.variaveis = new boolean[3];
        this.linha = "";
    }

    public Algoritmo(String linha, boolean[] variaveis, int num){ //contrutor com parametros
        this.linha = linha;
        this.variaveis = new boolean[num];
        for(int i =0; i<num; i++){
            this.variaveis[i] = variaveis[i];
        }   
    }

    void setVariaveis(boolean[] variaveis){ // setar variaveis
        this.variaveis = new boolean[variaveis.length];
        for(int i =0; i<variaveis.length; i++){
            this.variaveis[i] = variaveis[i];
        }
    }

    void setLinha(String linha){this.linha = linha;}
    String getLinha(){ return this.linha;}

    public void chamarMetodos(){
       formatarLinha(linha, variaveis);
       //System.out.println(linha);
        resolverString(linha);
    }


    private void formatarLinha(String linha, boolean[] variaveis){ // formatar linhas das Variaveis por 0 ou 1
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
        this.linha= str.toString();
    }

    private void resolverString(String linha){ // metodo que vai chamar os outros metodos para resolver a opercao
        for(int i = linha.length()-1; i> 0; i--){
            if(linha.charAt(i)== '('){
                String operador = extracaoOperadores(linha, i);
                //System.out.println(operador);
                boolean[] parametros = extracaoParametros(linha, i);
                i-=operador.length();
                linha = atualizarExpressao(linha, i, resolverExpressao(operador, parametros) ? "1": "0");
            }
        }
        System.out.println(linha);
    }

    private String extracaoOperadores(String linha, int index){ //extrair os opeadores que and or not
        StringBuilder str = new StringBuilder();

        do{
            index--;
            str.append(linha.charAt(index));
        }while(index > 0 && linha.charAt(index-1) >= 'a' && linha.charAt(index-1) <= 'z');
        
        return str.reverse().toString();
    }

    private boolean[] extracaoParametros(String linha, int index){ // extrai os parametros 0 ou 1
       StringBuilder str = new StringBuilder();

       while (index< linha.length()-1 && linha.charAt(index+1) !=')') {
            index++;

            if(linha.charAt(index) == '0' || linha.charAt(index)=='1'){
                str.append(linha.charAt(index));
            }
       }
       String valores = str.toString();
       boolean[] parametros = new boolean[valores.length()];
       for(int i =0; i < valores.length(); i++){
            parametros[i]=valores.charAt(i) =='1';
       }
       return parametros;
    }

    private boolean resolverExpressao(String operador, boolean [] parametros){ // resolve a expressao
        boolean resultado = false;

        switch (operador) {
            case "not":
                resultado = !parametros[0];
                break;
            case "or":
                for(int i=0; i<parametros.length; i++){
                    if(parametros[i]){
                        resultado = true;
                        i= parametros.length;
                    }
                }
                break;
            case "and":
                resultado = true;
                for(int i=0; i<parametros.length; i++){
                    if(!parametros[i]){
                        resultado = false;
                        i = parametros.length;
                    }
                } 
            break;
        }
        return resultado;
    }
    
    private  String atualizarExpressao(String linha, int fimIndex, String pacote){ // atualiza  a linha toda as vezes ate chegar a ultima e resolver
        StringBuilder str= new StringBuilder();

        for(int i =0 ; i< fimIndex; i++){
            str.append(linha.charAt(i));
        }

        for(int i = fimIndex; i < linha.length(); i++){
            if(linha.charAt(i)== ')'){
                str.append(pacote);
                i++;

                while(i < linha.length()){
                    str.append(linha.charAt(i));
                    i++;
                }
            }
        }
        return str.toString();
    }


}

public class AlgoritmoMain{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        int num; // numero de variaveis

        while( (num = scanner.nextInt())!= 0){ // loop para rodar ate a condicição inicial ser 0
            boolean[] variavies = new boolean[num]; // array de variaveis
           
            for(int i =0; i<num; i++){
                variavies[i] = scanner.next("[0-9]").charAt(0)  != '0';
            }

            String linha = scanner.nextLine(); // le a expressao
            Algoritmo algoritmo = new Algoritmo(linha, variavies, num); // instancia o objeto algoritmo
            algoritmo.chamarMetodos();// chama o metodo para formatar a linha
        }

        scanner.close();
    }
}
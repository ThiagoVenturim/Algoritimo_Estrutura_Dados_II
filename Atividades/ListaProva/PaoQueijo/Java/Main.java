import java.util.*;

public class Main{

    public static void troca(int [][]matriz, int linhas, int colunas){
        int [][]nova = new int[linhas][colunas];
    
        for(int i=0; i<linhas; i++){
            for(int j=0 ; j<colunas; j++){
                if( matriz[i][j] == 0 ){
                    int cont=0;
                    if(i!=0){
                        cont += (matriz[i-1][j] == 1 ) ? 1:0;  
                    }
                    if(j!=0){
                        cont += (matriz[i][j-1] == 1 ) ? 1:0;  
                    }
                    if(i!=linhas-1){
                        cont += (matriz[i+1][j] == 1 ) ? 1:0;  
                    }   
                    if(j!=colunas-1){
                        cont += (matriz[i][j+1] == 1 ) ? 1:0;  
                    }
                    nova[i][j] = cont;
                }else if( matriz[i][j] == 1 ){
                    nova[i][j] = 9;
                }
            }
        }
        matriz=nova;
        
            imprimir(nova, linhas, colunas);
    }

    public static void imprimir (int [][]matriz, int linhas, int colunas){
        for(int i=0; i<linhas; i++){
            for(int j=0 ; j<colunas; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNextInt()){
            int linhas = scanner.nextInt();
            int colunas = scanner.nextInt();
            int [][]matriz = new int[linhas][colunas];

            for(int i=0; i<linhas; i++){
                for(int j=0 ; j<colunas; j++){
                    matriz[i][j] =  scanner.nextInt();
                }
            }

            troca( matriz, linhas, colunas);
        }
    }

}
import java.util.*;

class Insercao{
    private int []array;
    private int tamanho;
    private int fim;

   

    public Insercao(int []array){
        this.tamanho = array.length;
        this.array = array;
    }

    public void metodo(){
        for(int i=1; i<tamanho; i++){
            int tmp=array[i];
            int j=i-1;
            for(; j>=0 && array[j]>tmp; j--){
                 array[j+1] = array[j];
            }
            array[j+1]=tmp;
        }
    }

    public void mostrar(){
        for(int i=0; i<tamanho; i++){
            System.out.print(array[i] + " ");
        }
    }

}

public class InsercaoMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tamanho= scanner.nextInt();
        int []vetor = new int[tamanho];
        for(int i=0;i<tamanho;i++){
            vetor[i] =scanner.nextInt();
        }
        Insercao in = new Insercao(vetor);
        in.metodo();
        in.mostrar();
    }
}
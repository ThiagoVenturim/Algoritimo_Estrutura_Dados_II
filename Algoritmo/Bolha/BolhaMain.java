import java.util.Scanner;

class Bolha{
    private int vetor[];
    private int tamanho;

    public Bolha(){ }

    public Bolha(int tamanho, int vetor[]){
        this.tamanho=tamanho;
        this.vetor=vetor;
    }

    public void setTamanho(int tamanho){
        if(tamanho>0){
            this.tamanho=tamanho;
        }
    }

    public void setVetor(int vetor[]){this.vetor=vetor;}
    public int getTamanho(){return tamanho;}
    public void MostrarVetor(){
        for(int i=0; i<tamanho; i++){
            System.out.print( vetor[i] + " ");
        }
    }

    public void swap(int i, int j){
        int terc= vetor[i];
        vetor[i]= vetor[j];
        vetor[j]= terc;
    }


    public void Ordenar(){
        for(int i = 0; i<tamanho ; i++){
            for(int j =1+i ; j <tamanho; j++ ){
                if(vetor[j]< vetor[i]){
                    swap(j, i);
                }
            }
        }
    }

}

    public class BolhaMain{
        public static void main(String[] args){
            Scanner scanner = new Scanner (System.in);
            Bolha bolha =new Bolha();
           

            System.out.println("Digite o tamanho do arrays para ordenar: ");
            int tamanho = scanner.nextInt();
            bolha.setTamanho(tamanho);
            int vetor[] = new int[tamanho];

            System.out.println("Preencha seu arrays: ");
            for(int i =0; i<tamanho; i++){
                vetor[i] = scanner.nextInt();
            }

            bolha.setVetor(vetor);

            bolha.Ordenar();
            bolha.MostrarVetor();

            scanner.close();
        }
    }
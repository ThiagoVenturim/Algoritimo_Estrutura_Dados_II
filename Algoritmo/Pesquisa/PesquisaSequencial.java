package Algoritmo.Pesquisa;

class PesquisaSequencial{
    private int[] vetor;
    private int tamanho;

    public PesquisaSequencial(){
    this.tamanho = 0;
    this.vetor = new int[tamanho];
    }
    public void setVetor(int[] vetor) {
        this.vetor = vetor;
        this.tamanho = vetor.length;
    }

    public boolean verificador(int num ){
        for(int i =0; i <tamanho; i++){
            if(vetor[i] == num){
                return true ;
            }
        }
        return false;
    }
}
class testDrivePesquisaSequencial{
    public static void main(String[] args) {
        PesquisaSequencial pesquisa = new PesquisaSequencial();
        // Adicionando elementos ao vetor
        int[] vetor = {1, 2, 3, 4, 5};
        pesquisa.setVetor(vetor);
        System.out.println(pesquisa.verificador(5));
    }
}
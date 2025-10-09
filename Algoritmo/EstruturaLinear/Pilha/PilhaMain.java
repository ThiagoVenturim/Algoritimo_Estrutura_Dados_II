package Algoritmo.EstruturaLinear.Pilha;

class Pilha{
    private int array[];
    private int ultimo;

    public Pilha(int max){
        array= new int[max];
        ultimo=0;
    }

    public void empilhar(int elemento){
        if(ultimo >= array.length){ throw new RuntimeException("Erro"); }
        array[ultimo]= elemento;
        ultimo++;
    }
    public int desempilhar(){
        if(ultimo <= 0){ throw new RuntimeException("Erro"); }
        return array[--ultimo];
    }

    public void mostrar(){

        for(int i=ultimo--; i>=0; i--){
            System.out.print(array[i] +" ");
        }
    }
}

public class PilhaMain {
     public static void main(String[] args) {
        Pilha pilha = new Pilha(5);

        pilha.empilhar(10);
        pilha.empilhar(20);
        pilha.empilhar(30);

        pilha.mostrar(); // imprime 30 20 10

        System.out.println("Saiu: " + pilha.desempilhar()); // Saiu: 30
        pilha.mostrar(); // imprime 20 10
    }
}

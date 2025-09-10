 class FilaLinear{
    private int array[];
    private int ultimo;

    public FilaLinear(int max){
        array = new int[max];
        ultimo= 0;
    }

    public void enfileirar(int elemento){ 
        if(ultimo>=array.length){  throw new RuntimeException("Erro"); }
        array[ultimo] =elemento;
        ultimo++;
    }

    public int desenfileirar(){
        if(ultimo<=0){ throw new RuntimeException("Erro");}
        int resp= array[0];
        ultimo--;
        for(int i=0; i<ultimo; i++){
            array[i] =array[i+1];
        }
        
        return resp;
    }

    public void mostrar(){
        for(int i =0; i<ultimo; i++){
            System.out.println(array[i]);
        }
    }
}
public class FilaLinearMain {
    public static void main(String[] args) {
        FilaLinear fila = new FilaLinear(5);

        fila.enfileirar(1);
        fila.enfileirar(2);
        fila.enfileirar(3);

        fila.mostrar(); // imprime 1, 2, 3

        System.out.println("Saiu: " + fila.desenfileirar()); // Saiu: 1
        fila.mostrar(); // imprime 2, 3
    }
}

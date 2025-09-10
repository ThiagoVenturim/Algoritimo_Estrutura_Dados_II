package Algoritmo.Fila;

 class FilaCircular{
    private int array[];
    private int primeiro, ultimo;

    public FilaCircular(int max){
        array = new int[max];
        primeiro = ultimo= 0;
    }

    public void infilerar(int elemento){ 
        if((ultimo+1)%array.length==primeiro){  throw new RuntimeException("Erro"); }
        array[ultimo] =elemento;
        ultimo = (ultimo+1)%array.length;
    }

    public int desinfilerar(){
        if(primeiro == ultimo){ throw new RuntimeException("Erro");}
        int resp= array[primeiro];
        primeiro= (primeiro+1)%array.length;
        return resp;
    }

    public void mostrar(){
        for(int i =primeiro; i!=ultimo; i=(i+1)%array.length){
            System.out.println(array[i]);
        }
    }

    public int removerMaior10(){
        int indMaior10 = ultimo, resp = -1;
        for(int i = primeiro; i!=ultimo; i = ((i+1)% array.length)){
            if(array[i]>10){
                
            }
        }
    }
}

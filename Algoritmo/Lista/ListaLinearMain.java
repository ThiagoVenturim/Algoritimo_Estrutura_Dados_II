class ListaLinear{
    private int array[];
    private int n;

    public ListaLinear(int max){
        array= new int[max];
        n=0; 
    }

    public void insInicio(int elemento){
        if(n>=array.length){ throw new RuntimeException("Erro");}
        for(int i = n; i>0; i--){
            array[i] =array[i-1];
        }
        array[0] = elemento;
        n++;
    }

    public void insFim(int elemento){
        if(n>=array.length){   throw new RuntimeException("Erro"); }
        array[n] = elemento;
        n++;
    }

    public void insPos(int elemento, int pos){
        if(pos>n || pos < 0 || n>=array.length){   throw new RuntimeException("Erro");}
        for(int i = n; i>pos; i--){
            array[i] = array[i-1];
        }
        array[pos] = elemento;
        n++;
    }

    public int remInicio(){
        if(n==0){     throw new RuntimeException("Erro"); }
        int temp = array[0];
        n--;
        for(int i=0; i<n; i++){
            array[i]= array[i+1];
        }
        return temp;
    }

    public int remFim(){
        if(n==0){     throw new RuntimeException("Erro");
}
        return array[--n];
    }

    public int remPos(int pos){
        if(pos < 0 || pos >=n || n==0){    throw new RuntimeException("Erro");}
        int temp = array[pos];
        n--;
        for(int i = pos; i<n; i++){
            array[i] = array[i+1];
        }
        return temp;
    }

    public void mostrar(){
        for(int i =0; i<n; i++){ System.out.print(array[i] + " ");}
    }

    public boolean pesquisa(int elemento){
        for(int i =0; i<n;i++){
            if(array[i]== elemento){ return true; }
        }
    return false;
    }
    

    public void swap(int i, int j){
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    public void inverterRecursivo(int tamanho, int index){
        if(tamanho <= index){
            return;
        }else{
            swap(tamanho, index);
            inverterRecursivo(tamanho-1, index+1);
        }
    }

    
    public void inverterIterativo(){
        int tamanho = n-1;
        for(int i = 0; i<=tamanho ; i++, tamanho--){
            swap(i, tamanho);
        }
    }
}

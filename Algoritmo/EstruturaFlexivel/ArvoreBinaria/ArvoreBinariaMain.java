class No{
    public No esq;
    public No dir;
    public int elemento;

    public No(){
        this(0);
    }
    public No(int elemento){
        this.esq = null;
        this.dir = null;
        this.elemento = elemento;
    }
}

class ArvoreBinaria{
    
    private No raiz;

    public ArvoreBinaria(){this.raiz=null;}

    private No inserir (int x, No raiz) throws Exception{
        if(raiz==null){
            raiz = new No (x);
        }else if(x<raiz.elemento){
            raiz.esq= inserir(x, raiz.esq);
        }else if(x> raiz.elemento){ 
            raiz.dir=  inserir(x, raiz.dir);
        }else if(x==raiz.elemento){
            throw new Exception("Elemento Repetido");
        }
        return raiz;
    }

    public void inserir(int x){ 
        try{
            raiz = inserir(x, raiz);
         }catch(Exception e){
            System.out.println("Elemento Repetido");
         } 
    }

    private void mostrar(No raiz){
        if(raiz==null){
            return;
        }else{
            System.out.println(raiz.elemento + " ");
            mostrar(raiz.esq);
            mostrar(raiz.dir);
        }
    }

    public void mostrar (){ mostrar(raiz);}

    private int pesquisa(int x, No raiz){
        int resp;
        if(raiz== null){
            resp=  0;
        }else{
            if(raiz.elemento == x){
                resp= 1;
            }else if(raiz.elemento > x){
                resp= pesquisa(x, raiz.esq);
            }else{
                resp=  pesquisa(x, raiz.dir);
            }
        }
        return resp;
    }

    public int pesquisa(int x){return pesquisa(x, raiz); }

}

public class ArvoreBinariaMain{
    public static void main(String[] args){
        ArvoreBinaria av = new ArvoreBinaria();
        av.inserir(3);
        av.inserir(2);
        av.inserir(7);
        av.inserir(1);
        av.inserir(8);
        av.inserir(6);
        av.inserir(4);
        av.inserir(9);
        av.mostrar();
        if(av.pesquisa(8)==1){
            System.out.println("")
        }
    }
}
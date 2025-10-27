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

    private void caminharCentral(No i){
        if(i!=null){
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    private void caminharPos(No i){
        if(i!=null){
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    private void caminharPre(No i){
        if(i!=null){
            System.out.print(i.elemento + " ");
            caminharPos(i.esq);
            caminharPos(i.dir);
        }
    }

    private int getTamanho(No i, int cont){
        if(i==null){
           cont --; 
        }else {
            int tmp = getTamanho(i.esq, cont+1);
            int tmp2 = getTamanho(i.dir, cont+1);
            cont = (tmp >tmp2) ? tmp : tmp2;
        }
        return cont;
    }

    public int getTamanho(){
        return getTamanho(raiz, 0);
    }

    public void caminhar(){
        System.out.println("Caminha Pre ");
        caminharPre(raiz);
        System.out.println();
        System.out.println("Caminha Pos ");
        caminharPos(raiz);
        System.out.println();
        System.out.println("Caminha Central ");
        caminharCentral(raiz);
        System.out.println();
     }

}

public class ArvoreBinariaMain{
    public static void main(String[] args){
        ArvoreBinaria av = new ArvoreBinaria();
        av.inserir(6);
        av.inserir(3);
        av.inserir(5);
        av.inserir(1);
        av.inserir(2);

        av.caminhar();
        System.out.println("Tamanho:  " + av.getTamanho());
    
    }
}

class Celula{
    public Celula prox;
    public Celula ant;
    public int elemento;

    public Celula(int elemento){
        this.elemento=elemento;
        this.prox=null;
         this.ant=null;
    }

    public Celula(){
        this.elemento=0;
        this.prox=null;
        this.ant=null;
    }
}

class Pilha{
    public Celula topo;

    public Pilha(){
        topo = null;
    }

    public void inserir(int x){
        if(topo==null){
            topo= new Celula(x);
        }else{
            topo.ant = new Celula(x);
            topo.ant.prox = topo;
            topo = topo.ant;
        }
    }

    public int remover(){
        Celula tmp = topo;
        topo = topo.prox;
        return tmp.elemento;
    }   
    
     public void mostrar(){
        for(Celula tmp = topo; tmp!=null ; tmp = tmp.prox){
            System.out.print( tmp.elemento + " ");
        }
        System.out.println();
    }    

    public int somar(){
        int soma=0;
          for(Celula tmp = topo; tmp!=null ; tmp = tmp.prox){
            soma += tmp.elemento;
        }
        return soma;
    }

    public int somarRec(Celula tmp){
        if(tmp==null){
            return  0;
        }else{
            return tmp.elemento + somarRec(tmp.prox); 
        }
        
    }

    public int maiorRec(Celula tmp){
        int maior;
        if(tmp==null){
            maior=0;
        }else{
            maior = maiorRec(tmp.prox);
            if(tmp.elemento > maior ){
                maior =tmp.elemento;
            }
        }
        return maior;
    }

    public  int chamarSomar(){
        return somarRec(topo);
    }

     public  int maior(){
        return maiorRec(topo);
    }


}

public class PilhaMain {
    public static void main(String [] args){
        Pilha pl = new Pilha();
        pl.inserir(1);
        pl.inserir(2);
        pl.inserir(3);
        pl.inserir(4);
        pl.inserir(6);
        pl.inserir(7);
        pl.inserir(8);
        pl.inserir(9);
        System.out.println("Maior Elementos: " + pl.maior());
        pl.mostrar();
        pl.remover();
        pl.remover();
        pl.remover();
        pl.remover();
        pl.remover();
        pl.mostrar();
        System.out.println("Somas dos Elementos: " + pl.chamarSomar());
        pl.inserir(73);
        pl.inserir(84);
        pl.inserir(12);
        System.out.println("Maior Elementos: " + pl.maior());

    }
}

class CelulaLista{
    CelulaPilha topo;
    CelulaLista prox;
    
    public CelulaLista(){
        this(0);
    }

    public CelulaLista(int elemento){
        this.topo=new CelulaPilha(elemento);
        this.prox= null;     
    }

}

class CelulaPilha{      
    public int elemento;
    public CelulaPilha prox;
    
    public CelulaPilha(){
        this(0);
    }

    public  CelulaPilha( int elemento){
        this.elemento= elemento;
        this.prox= null;
    }
}


class Lista{
    public CelulaLista inicio;
    public CelulaLista fim; 

    public Lista(){
        this.inicio=null;
        this.fim=null;
    }

    public void inserirListaInicio(int elemento){
        if(inicio==null){
            inicio= new CelulaLista(elemento);
            fim=inicio;
        }else{
            CelulaLista tmp =new CelulaLista(elemento);
            tmp.prox= inicio;
            inicio = tmp;
            
        }
    }

    public void inserirListaFim(int elemento){
        if(fim==null){
            fim= new CelulaLista(elemento);
            inicio= fim;
        }else{
            fim.prox = new CelulaLista(elemento);
            fim = fim.prox;
        }
    }

    public void inserirPilhaIncio(int elemento){
        if(inicio==null){
            inserirListaInicio(elemento);
        }else{
            CelulaLista tmp= new CelulaLista(elemento);
            tmp.topo.prox= inicio.topo;
            tmp.prox= inicio.prox;
            inicio = tmp; 
        }
        
    }

    public void inserirPilhaFim(int elemento){
        if(fim==null){
            inserirListaFim(elemento);
        }else{
            CelulaLista tmp = new CelulaLista(elemento);
            CelulaLista aux= getCelulaListaAnt(fim);
            tmp.topo.prox = fim.topo;
            aux.prox= tmp;
            fim = tmp;
        }

    }

    public CelulaLista getCelulaListaAnt(CelulaLista lista){
        CelulaLista tmp = lista;
        for( ; tmp.prox!=lista; tmp =tmp.prox){}
        return tmp;
    }

    public CelulaLista maiorPilha(){
        CelulaLista maior = inicio;
        for(CelulaLista tmp = inicio.prox ; tmp!=null; tmp=tmp.prox){
            for(CelulaPilha i = tmp.topo,  j = maior.topo; i!=null && j!=null ; i=i.prox, j= j.prox ){
                if(j.prox==null && i.prox!=null){
                    maior= tmp;
                }
            }
        }
        return maior;
    }

    public void mostrar(){
        for(CelulaLista i = inicio; i!=null; i = i.prox){
            System.out.print(i.topo.elemento +  "  => ");
            for(CelulaPilha j = i.topo.prox; j!=null; j= j.prox){
                System.out.print(j.elemento +  " ");
            }
        System.out.println();
        }
    }
}

public class CelulaListaMain{
    public static void main(String []args){
        Lista ls = new Lista();
        ls.inserirListaInicio(1);
        ls.inserirPilhaIncio(3);
        ls.inserirPilhaIncio(2);
        ls.inserirPilhaIncio(4);

        ls.inserirListaInicio(7);
        ls.inserirPilhaIncio(2);
         ls.inserirListaInicio(8);
         ls.inserirPilhaIncio(75);  
         ls.inserirPilhaIncio(18);
         ls.inserirPilhaIncio(75);  
         ls.inserirPilhaIncio(18);
         ls.inserirPilhaIncio(75);  
         ls.inserirPilhaIncio(18);
            
        ls.mostrar();
        CelulaLista tmp = ls.maiorPilha();
        System.out.println("Maior Pilha é "+ tmp.topo.elemento );
    }
}

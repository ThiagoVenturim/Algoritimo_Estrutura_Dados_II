import java.util.*;


class CelulaLista {
    public CelulaFila inicioFila;
    public CelulaFila fimFila;
    public CelulaLista prox;
    public CelulaLista ant;
    
    public CelulaLista(){
        this(0);
    }

    public CelulaLista(int x){
        this.inicioFila = new CelulaFila(x);
        fimFila= inicioFila;
        this.prox=null;
        this.ant=null;
    }
}

class CelulaFila {
    public int elemento;
    public CelulaFila prox;
    public CelulaFila ant;

    public CelulaFila(int elemento){
        this.elemento = elemento;
        this.prox=null;
        this.ant=null;
    }
}


class Lista {
    public CelulaLista inicio;
    public CelulaLista fim;

    public Lista(){
        inicio=fim=null;
    }

    public void inserirInicioLista(int elemento){
        if(inicio==null){
            inicio= new CelulaLista(elemento);
            fim=inicio;
        }else{
            inicio.ant =new CelulaLista(elemento);
            inicio.ant.prox= inicio;
            inicio = inicio.ant;
        }
    }

    public void inserirInicioListaFila(int elemento){
        if(inicio!=null){
            inicio.fimFila.prox= new CelulaFila(elemento);
            inicio.fimFila.prox.ant= inicio.fimFila;
            inicio.fimFila= inicio.fimFila.prox;
        }
    }

    public void mostrar(){
        if(inicio!=null){
            for(CelulaLista tmp = inicio; tmp!=null; tmp=tmp.prox){
                CelulaFila pmt= tmp.inicioFila;
                System.out.print(pmt.elemento + " => ");
                for( pmt= pmt.prox; pmt!=null; pmt = pmt.prox){
                    System.out.print(pmt.elemento + "  ");
                }
                System.out.println();
            }
        }
    }

    public CelulaLista maiorFila(){
        CelulaLista maior=null;
        if(inicio!=null){
             maior = inicio;
             int tamanho = getTamanho(maior.inicioFila);
            for(CelulaLista tmp = inicio.prox; tmp!=null; tmp=tmp.prox){
                int aux= getTamanho(tmp.inicioFila);
                if(aux>tamanho){
                    maior=tmp;
                    tamanho=aux;
                }
            }
        }
        return maior;
    }

    public int getTamanho(CelulaFila tmp){
        if(tmp==null){
            return 0;
        }else{
            return 1+ getTamanho(tmp.prox);
        }
    }
}

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Lista ls = new Lista();
        ls.inserirInicioLista(1);
        ls.inserirInicioListaFila(2);
        ls.inserirInicioListaFila(2);
        ls.inserirInicioListaFila(2);
        ls.inserirInicioListaFila(2);
        ls.inserirInicioLista(2);
        ls.inserirInicioListaFila(3);
        ls.inserirInicioLista(3);
        ls.inserirInicioListaFila(4);
        ls.inserirInicioLista(4);
        ls.inserirInicioListaFila(5);

        ls.mostrar();
        CelulaLista tmp = ls.maiorFila();
        System.out.println(" Maior Lista" + tmp.inicioFila.elemento);
    }
}

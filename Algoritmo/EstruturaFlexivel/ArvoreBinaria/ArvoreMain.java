import java.util.*;

 class Celula{
    public Celula esq;
    public Celula dir;
    public int elemento;

    public Celula(){
        this(0);
    }

    public Celula(int elemento){
        this.esq = null;
        this.dir = null;
        this.elemento =elemento;
    }
}

class Arvore{
    private Celula raiz;
    public Arvore(){ raiz=null;}

    public inserir(int x){
        Celula tmp = raiz;
        if(tmp== null){tmp= new Celula(x);}
        else if(){}
    }
}

  
public class ArvoreMain{
    public static void main(String [] args){
        Arvore arvore = new Arvore();
        arvore.inserir(5);
        arvore.inserir(2);
        arvore.inserir(7);
        arvore.inserir(4);
        arvore.inserir(3);
        arvore.inserir(6);
        arvore.mostrar();
    }
}
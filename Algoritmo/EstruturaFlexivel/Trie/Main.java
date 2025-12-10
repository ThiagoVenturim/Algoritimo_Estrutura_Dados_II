import java.util.*;

class No{
    public char elemento;
    public final int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No(char elemento){
        this.elemento = elemento;
        prox = new No[ tamanho];
        for(int i=0; i<tamanho;i++){prox[i]=null; }
    }

    public No(){
        this(' ');
    }

    public static int hash(char x){
        return (int)x;
    }
}

class ArvoreTrie{
    private No raiz;

    public ArvoreTrie(){
        raiz = new No();
    }
    public void inserir(String palavra){
        inserir(palavra, raiz,0);
    }
    private void inserir(String palavra, No raiz, int i){
        if(raiz.prox[palavra.charAt(i)]==null){
            raiz.prox[palavra.charAt(i)] = new No(palavra.charAt(i));
            if( i == palavra.length()-1){
                raiz.prox[palavra.charAt(i)].folha=true;
            }else{
                inserir(palavra, raiz.prox[palavra.charAt(i)], i+1);
            }
        }else if(raiz.prox[palavra.charAt(i)].folha == false && i<palavra.length() -1){
            inserir(palavra, raiz.prox[palavra.charAt(i)], i+1);
        }
    } 

    public boolean pesquisar(String palavra, No raiz, int i){
        boolean resp;
        if(raiz.prox[palavra.charAt(i)]==null){
            resp =false;
        }else if(i==palavra.length()-1){
            resp = (raiz.prox[palavra.charAt(i)].folha == true);
        }else if(i <palavra.length()-1 ){
            resp= pesquisar(palavra, raiz.prox[palavra.charAt(i)], i+1);
        }
        return resp;
    }


}

public class Main{
    public static void main(String[] args){
        
    }
}
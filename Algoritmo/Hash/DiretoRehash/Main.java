import java.util.*;

class Hash{
    private int []tabela;
    private int m;
    private int NULO = -1;
    public Hash(int m){
        this.m = m;
        this.tabela = new int[m];
        for(int i=0;i<m;i++){tabela[i] =NULO;}
    }

    public int hash(int elemento){
        return elemento % m;
    }

    public int rehash(int elemento){
        return (elemento+1) % m;
    }

    public boolean inserir(int elemento){
        boolean resp = false;
        if(elemento != NULO){
            int pos = hash(elemento);
            if(tabela[pos]==NULO){
                tabela[pos] = elemento;
                resp = true;
            }else{
                pos = rehash(elemento);
                if(tabela[pos]==NULO){
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = hash(elemento);
        if(tabela[pos]== elemento){
            resp = true;
        }else{
            pos = rehash(elemento);
            if(tabela[pos]==elemento){
                resp = true;
            }
        }
        return resp;
    }

}

public class Main{
    public static void main(String [] args){
    }
}
import java.util.*;

class Hash{
    int tabela[];
    int m1, m2, m , reserva;
    final int NULO = -1;

    public Hash(){
        this(13, 7);
    }

    public Hash(int m1, int m2){
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1+m2;
        this.tabela = new int[tabela.m];
        for(int i=0; i<m1; i++){tabela[i]= NULO;}
        this.reserva=0;
    }

    public int hash(int elemento){ return elemento % m1; }

    public boolean inserir(int elemento){
        boolean resp = false;
        if(elemento!=NULO){
            int pos =  hash(elemento);
            if(tabela[pos] == NULO){
                tabela[pos] = elemento;
                resp = true;
            }else if(reserva < m2){
                tabela[reserva+m1] = elemento;
                resp = true;
                reserva++
            }
        }
        return resp;
    }
    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = hash(elemento);
        if(tabela[pos]== elemento){
            resp =true;
        }else if(tabela[pos] != NULO){
            for(int i=0; i<reserva; i++){
                if(tabela[m1+i]==elemento){
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }
}


public class Main{
    public static void Main(String[] args){

    }
}
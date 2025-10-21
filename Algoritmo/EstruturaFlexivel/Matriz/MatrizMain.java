import java.util.*;

class Celula{
    public int elemento;
    public Celula dir;
    public Celula esq;
    public Celula sup;
    public Celula inf;

    public Celula(){
       this(0);
    }
    public Celula(int x){
        this.elemento=x;
        this.dir= null;
        this.esq= null;
        this.sup= null;
        this.inf= null;
    }
}

class Matriz{
    private int linha;
    private int colunas;
    private Celula inicio;

    public Matriz( ){
        this(3 , 3);
    }

    public Matriz(int linha, int colunas){
        this.linha= linha;
        this.colunas =colunas;

        inicio = new Celula(); 
        Celula colNova = inicio;
        
        for(int i =0; i<linha; i++){
            if(i != 0){
                colNova.inf = new Celula();
                colNova.inf.sup = colNova;
                colNova= colNova.inf;
            }
            Celula linNova = colNova;
            for(int j=1; j<colunas; j++, linNova = linNova.dir ){
                linNova.dir = new Celula();
                linNova.dir.esq = linNova;
                if(i != 0){
                    linNova.dir.sup= linNova.sup.dir;
                    linNova.sup.dir.inf= linNova.dir;
                }
            }
        }
    }

    public void inserirElemento(Scanner scanner){
        System.out.println("Inserir elemento: ");
        for(Celula i = inicio ; i!= null  ; i = i.inf){
            for(Celula j=i; j!=null; j = j.dir ){
                j.elemento = scanner.nextInt();
            }
        }
    }

    public void mostrar(){
        System.out.println("Mostrar ");
        for(Celula i = inicio; i!= null; i = i.inf){

            for(Celula j=i; j!=null; j = j.dir ){
                System.out.print( " " + j.elemento );
            }
            System.out.println();
        }
    }

    public void mostrarDiagonal(){
    System.out.println("Mostrar Diagonal"); 
        for(Celula i = inicio; i!= null; ){
                System.out.print( i.elemento );
                System.out.println();
            if(i.inf!=null){
             i = i.inf.dir;
            }else{
                i=null;
            }
        }
    }

    public void inserirColunaDireita(Scanner scanner){
        System.err.println("Nova Coluna a Direita: ");
        colunas++;
        Celula tmp = inicio;
        for( ;tmp.dir!= null; tmp= tmp.dir){ }
        for( int i=0; tmp!=null ; tmp=tmp.inf, i++ ){
            tmp.dir = new Celula(scanner.nextInt());
            tmp.dir.esq = tmp;
            if(i!=0){
                tmp.sup.dir.inf= tmp.dir;
                tmp.dir.sup = tmp.sup.dir;
            }
        }
    }
    public void inserirColunaEsquerda(Scanner scanner){
        System.err.println("Nova Coluna a Esquerda: ");
        Celula tmp = inicio;
        for( int i=0; tmp!=null ; tmp=tmp.inf, i++ ){
            tmp.esq = new Celula(scanner.nextInt());
            tmp.esq.dir = tmp;
            if(i!=0){
                tmp.sup.esq.inf= tmp.esq;
                tmp.esq.sup = tmp.sup.esq;
            }
        }
        inicio = inicio.esq;
    }

    public void inserirLinhaSuperior(Scanner scanner){
        linha++;
        System.out.println("Inserir Linha superior");
        Celula tmp = inicio;
        for(int i=0 ; tmp!= null; tmp = tmp.dir ,i++){
            tmp.sup = new Celula(scanner.nextInt());
            tmp.sup.inf = tmp;
            if(i!=0){
                tmp.sup.esq = tmp.esq.sup;
                tmp.esq.sup.dir = tmp.sup;
            }
        } 
        inicio = inicio.sup;
    }
    
    public void inserirLinhaInferior(Scanner scanner){
        linha++;
        System.out.println("Inserir Linha Inferior");
        Celula tmp = inicio;
        for( ;tmp.inf!= null; tmp= tmp.inf){ }
        for(int i=0 ; tmp != null; tmp = tmp.dir ,i++){
            tmp.inf = new Celula(scanner.nextInt());
            tmp.inf.sup = tmp;
            if(i!=0){
                tmp.inf.esq = tmp.esq.inf;
                tmp.esq.inf.dir = tmp.inf;
            }
        } 
    }

    public void inserirColunaPos(int pos, Scanner scanner){
        if (pos < 0 || pos > colunas + 1) {
            System.err.println("Posição inválida!");
            return;
        } else if (pos == 0) {
            inserirColunaEsquerda(scanner);
            return;
        } else if (pos == colunas) { // último índice
            inserirColunaDireita(scanner);
            return;
        }
        
        Celula nova= new Celula(scanner.nextInt());
        Celula tmp =nova;
        for(int i=1; i<linha; i++, tmp = tmp.inf){
            tmp.inf= new Celula(scanner.nextInt());
            tmp.inf.sup = tmp;  
        }

        tmp = inicio;
        for(int i = 0; i<pos-1 && tmp!=null; i++){
            tmp =tmp.dir;
        }
        for( ; tmp!= null && nova!= null; tmp = tmp.inf , nova= nova.inf){
            tmp.dir.esq = nova;
            nova.dir = tmp.dir;
            tmp.dir= nova;
            nova.esq = tmp; 
        }
        colunas++;
    }

    public void inserirLinhaPos(int pos, Scanner scanner){
        if (pos < 0 || pos > linha + 1) {
            System.err.println("Posição inválida!");
            return;
        } else if (pos == 0) {
            inserirLinhaSuperior(scanner);
            return;
        } else if (pos == colunas) { // último índice
            inserirLinhaInferior(scanner);
            return;
        }
        
        Celula nova= new Celula(scanner.nextInt());
        Celula tmp =nova;
        for(int i=1; i<colunas; i++, tmp = tmp.dir){
            tmp.dir= new Celula(scanner.nextInt());
            tmp.dir.esq = tmp;  
        }
        tmp = inicio;
        for(int i = 0; i<pos-1 && tmp!=null; i++){
            tmp =tmp.inf;
        }
        for( ; tmp!= null && nova!= null; tmp = tmp.dir , nova= nova.dir){
            tmp.inf.sup = nova;
            nova.inf = tmp.inf;
            tmp.inf= nova;
            nova.sup = tmp; 
        }
        linha++;
    }

    public int[] removerColunaDireita(){
        if(colunas<2){
            System.err.println("Nao e possivel remover");
        }
        Celula tmp=inicio;
        int []array = new int[linha];
        for( ; tmp.dir!=null;tmp= tmp.dir){ }
        int j=0;
        
        for(Celula i =tmp ; i!=null; i = i.inf ,j++, tmp = tmp.inf){
            array[j]= i.elemento;
            tmp.esq.dir=null;
        }   
        colunas--;
        return array;
    }

    public int[] removerColunaEsquerda(){
        if(colunas<2){
            System.err.println("Nao e possivel remover");
        }
        Celula tmp=inicio;
        inicio = inicio.dir;
        int []array = new int[linha];
        int j=0;
        for(Celula i =tmp ; i!=null; i = i.inf,j++, tmp = tmp.inf){
            array[j]= i.elemento;
            tmp.dir.esq=null;
        }   
        colunas--;
        return array;
    }

    public int[] removerLinhaInferior(){
        if(linha<2){
            System.err.println("Nao e possivel remover");
        }
        Celula tmp=inicio;
        int []array = new int[colunas];
        for( ; tmp.inf!=null;tmp= tmp.inf){ }
        int j=0;
        for(Celula i =tmp ; i!=null; i = i.dir,j++, tmp = tmp.dir){
            array[j]= i.elemento;
            tmp.sup.inf=null;
        }   
        linha--;
        return array;
    }

    public int[] removerLinhaSuperior(){
        if(linha<2){
            System.err.println("Nao e possivel remover");
        }
        Celula tmp=inicio;
        inicio= inicio.inf;
        int []array = new int[colunas];
        int j=0;
        for(Celula i =tmp ; i!=null; i = i.dir,j++, tmp = tmp.dir){
            array[j]= i.elemento;
            tmp.inf.sup=null;
        }   
        linha--;
        return array;
    }
    
    public int[] removerLinhaPos(int pos){
        if((pos<0 || pos>linha) && linha < 2){
            System.err.println("Nao e possivel remover");
        }else if(pos==0){
            return removerLinhaSuperior();
        }else if(pos==linha-1){
            return removerLinhaInferior();
        }
        Celula tmp=inicio;
        int []array = new int[colunas];
        int j=0;
        for(int i=0; i<pos && tmp.inf!=null; tmp = tmp.inf, i++){}
        for(Celula i =tmp ; i!=null; i = i.dir,j++, tmp = tmp.dir){
            array[j]= i.elemento;
            tmp.sup.inf = tmp.inf;
            tmp.inf.sup= tmp.inf.sup;
        }   
        linha--;
        return array;
    }
    public int[] removerColunaPos(int pos){
        if((pos<0 || pos>colunas) && colunas < 2){
            System.err.println("Nao e possivel remover");
        }else if(pos==0){
            return removerColunaDireita();
        }else if(pos==linha-1){
            return removerColunaEsquerda();
        }
        Celula tmp=inicio;
        int []array = new int[colunas];
        int j=0;
        for(int i=0; i<pos && tmp.dir!=null; tmp = tmp.dir, i++){}
        for(Celula i =tmp ; i!=null; i = i.inf,j++, tmp = tmp.inf){
            array[j]= i.elemento;
            tmp.esq.dir = tmp.dir;
            tmp.dir.esq= tmp.esq;
        }   
        linha--;
        return array;
    }
    
    public void swap(Celula i, Celula j){
        int tmp = i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp;
   }

   public void booble(){
        for(Celula i = inicio ; i!=null; i = i.inf){
            for(Celula j = i; j!=null; j=j.dir){
                for(Celula k = i ; k!=null; k = k.inf){
                    for(Celula q = k; q!=null; q=q.dir){
                        if(j.elemento<q.elemento){
                            swap(j, q);
                        } 
                    }
                }
            }
        }
   }

}
public class MatrizMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Matriz matriz = new Matriz(3, 3);
        matriz.inserirElemento(scanner);
        matriz.mostrar();
        matriz.booble();
        matriz.mostrar();
        
    }
}
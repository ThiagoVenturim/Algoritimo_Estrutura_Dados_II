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
        if (pos < 1 || pos > colunas + 1) {
            System.err.println("Posição inválida!");
            return;
        } else if (pos == 0) {
            inserirColunaEsquerda(scanner);
            return;
        } else if (pos == colunas + 1) { // último índice
            inserirColunaDireita(scanner);
            return;
        }
        
        Celula nova= new Celula(scanner.nextInt());
        Celula tmp =nova;
        System.out.println("Carregando nova celula");
        for(int i=1; i<linha; i++, tmp = tmp.inf){
            tmp.inf= new Celula(scanner.nextInt());
            tmp.inf.sup = tmp;  
        }

        tmp = inicio;
        for(int i = 0; i<pos-1 && tmp!=null; i++){
            tmp =tmp.dir;
        }
        System.out.println("Passou");
        for( ; tmp!= null && nova!= null; tmp = tmp.inf , nova= nova.inf){
            tmp.dir.esq = nova;
            nova.dir = tmp.dir;
            tmp.dir= nova;
            nova.esq = tmp; 
        }
        colunas++;
    }



}
public class MatrizMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Matriz matriz = new Matriz(2, 3);
        matriz.inserirElemento(scanner);
        
        matriz.mostrar();
        matriz.inserirColunaPos(1, scanner);
        matriz.mostrar();
    }
}
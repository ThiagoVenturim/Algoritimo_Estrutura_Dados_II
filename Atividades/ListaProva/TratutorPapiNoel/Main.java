import java.util.*;

class No{
    public String elemento;
    public String feliz_natal;
    public No esq;
    public No dir;

    public No(){
        this(null, null);
    }

    
    public No(String elemento, String feliz_natal){
        this.elemento=elemento;
        this.feliz_natal=feliz_natal;
        this.esq=null;
        this.dir=null;
    }
    
}

class Arvore{
    public No raiz;

    public Arvore(){this.raiz=null;}

    private No inserir(String elemento, String feliz_natal, No raiz){
        if(raiz==null){
            raiz=new No(elemento, feliz_natal);
        }else if(raiz.elemento.compareToIgnoreCase(elemento) < 0){
            raiz.dir = inserir( elemento,  feliz_natal,  raiz.dir);
        }else if(raiz.elemento.compareToIgnoreCase(elemento) > 0){
            raiz.esq = inserir( elemento,  feliz_natal,  raiz.esq);
        }
        return raiz;
    }

    public void inserir(String elemento, String feliz_natal){
        raiz = inserir(elemento, feliz_natal, raiz);
    }

    private void mostrar(No raiz){
        if(raiz!=null){
            System.out.println(raiz.elemento + " => "+ raiz.feliz_natal);
            mostrar(raiz.esq);
            mostrar(raiz.dir);
        }
    }

    public void mostrar(){
        mostrar(raiz);
    }

    private boolean procurar(String procurar, No raiz){
        boolean resp= false;
        if(raiz==null){
            resp=false;
        }else if(raiz.elemento.compareToIgnoreCase(procurar)==0){
            System.out.println(raiz.feliz_natal);
            resp=true;
        }else if(raiz.elemento.compareToIgnoreCase(procurar) < 0){
           resp = procurar( procurar,  raiz.dir);
        }else if(raiz.elemento.compareToIgnoreCase(procurar) > 0){
            resp= procurar( procurar, raiz.esq);
        }
        return resp;
    }
    
    public void procurar(String procura){
        if(!procurar(procura, raiz)){
            System.out.println("--- NOT FOUND ---" );
        }
    }

}

public class Main{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Arvore av = new Arvore();
        av.inserir("brasil","Feliz Natal!");
        av.inserir("alemanha","Frohliche Weihnachten!");
        av.inserir("austria","Frohe Weihnacht!");
        av.inserir("coreia" ,"Chuk Sung Tan!");
        av.inserir("espanha","Feliz Navidad!");
        av.inserir("grecia" ,"Kala Christougena!");
        av.inserir("estados-unidos", "Merry Christmas!");
        av.inserir("inglaterra","Merry Christmas!");
        av.inserir("australia", "Merry Christmas!");
        av.inserir("portugal", "Feliz Natal!");
        av.inserir("suecia" ,"God Jul!");
        av.inserir("turquia" ,"Mutlu Noeller");
        av.inserir("argentina", "Feliz Navidad!");
        av.inserir("chile"  ,"Feliz Navidad!");
        av.inserir("mexico" ,"Feliz Navidad!");
        av.inserir("antardida", "Merry Christmas!");
        av.inserir("canada" , "Merry Christmas!");
        av.inserir("irlanda" ,"Nollaig Shona Dhuit!");
        av.inserir("belgica", "Zalig Kerstfeest!");
        av.inserir("italia", "Buon Natale!");
        av.inserir("libia" , "Buon Natale!");
        av.inserir("siria" ,"Milad Mubarak!");
        av.inserir("marrocos" ,"Milad Mubarak!");
        av.inserir("japao"  , "Merii Kurisumasu!");
        //av.mostrar();
        
        while(scanner.hasNextLine()){
            av.procurar(scanner.nextLine());
        }
        scanner.close();
    }
}
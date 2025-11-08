import java.util.*;


class Contato{
    public String telefone;
    public String nome;
    public String email;
    public int cpf;
        
    public Contato(){
        this(" "," "," ",0);
    }

    public Contato(String tel,String name,String email,int Cpf){
        this.telefone=tel;
        this.nome=name;
        this.email=email;
        this.cpf=Cpf;
    }

    public  void imprimirC(){
        System.out.println("Nome: "+this.nome+" Telefone: "+this.telefone+" Email: "+this.email+" Cpf: "+this.cpf);
    }
}

class Celula{
    public Contato contato;
    public Celula prox;

    public Celula(){
        this(null);
    }

    public Celula(Contato contato){
        this.contato=contato;
        this.prox=null;
    }
}

class No{
    public char letra;
    public No esq;
    public No dir;
    public Celula primeiro;
    public Celula ultimo;

    public No(){
        this(' ');
    }

    public No(char letra){
        this.letra=letra;
        this.dir=this.esq=null;
        this.primeiro=this.ultimo=null;
    }
}

class Agenda{
    No raiz;

        
    public Agenda(){
        for(int k=1,i=65,j=90;i<=j;i++,j--,k+=2){
            int meio=(j-i)/2;
                setArvore((char)(meio+65));
                setArvore((char)(k+meio+65));
            }
    }

    private No setArvore(char c,No raiz){
        if(raiz==null){
            raiz=new No(c);
        }else{
            if(c>raiz.letra ){
                raiz.dir=setArvore(c, raiz.dir);
            }else if(c<raiz.letra){
                raiz.esq=setArvore(c, raiz.esq);
            }
        }
        return raiz;
    }

    public void setArvore(char c){
        raiz=setArvore(c, raiz);
    }

    private void inserirC(char c, No no, Contato contato){
        if(no.letra==c){
            if( no.primeiro== null){
                no.primeiro = new Celula(contato);
                no.ultimo=no.primeiro;
            }else{
                no.ultimo.prox= new Celula(contato); 
                no.ultimo=no.ultimo.prox;
            }
        }
        else if(no.letra>c){
            inserirC(c, no.esq, contato);
        } else{
            inserirC(c, no.dir, contato);
        }
    }

    public void inserirC(Contato contato){
        char c=contato.nome.charAt(0);
        inserirC(c, raiz, contato);        
    }

    void imprimir(No raiz){
        if(raiz!=null){
            imprimir(raiz.esq);
            System.out.println(raiz.letra );
            for(Celula i=raiz.primeiro;i!=null;i=i.prox){
                i.contato.imprimirC();
            }
            imprimir(raiz.dir);
        }
    }
    

    void imprimir(){
        imprimir(raiz);
    }

   
}

public class AgendaMain {
    public static int tranformarInt(String linha){
        int numero = 0;
        for(int i=0; i<linha.length(); i++){
            numero = numero * 10 + (linha.charAt(i) - '0');
        }
        return numero;
    }   
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        Agenda a=new Agenda();

       while(scanner.hasNext()){
            String linha=scanner.nextLine();
            String tel="";
            String name="";
            String email="";
            String cpf="";
            for(int i=0, count = 0 ;i<linha.length();i++){
                if(linha.charAt(i)=='|' ){
                    count++;
                }else if(count==0){
                    tel+=linha.charAt(i);
                }else if(count==1){
                    name+=linha.charAt(i);
                }else if(count==2){
                    email+=linha.charAt(i);
                }else if(count==3){
                    cpf+=linha.charAt(i);
                }
            }
            
            Contato c= new Contato(tel,name,email,tranformarInt(cpf));
            a.inserirC(c);
        }
        a.imprimir();

        scanner.close();
    }
}

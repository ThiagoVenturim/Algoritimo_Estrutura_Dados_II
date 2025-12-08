import java.util.*;

class No{
    public int elemento; 
    public No esq, dir;
    public int nivel;

    public No(int elemento){
        this(elemento, null, null, 1);
    }

    public No(int elemento, No esq, No dir, int nivel){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel =nivel;
    }

    public void setNivel(){
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    public static  int getNivel(No no){
        return (no==null)? 0 : no.nivel;
    }
}

class AVL{
    private No raiz;

    public AVL(){raiz=null;}

    private No inserir(int x, No raiz){
        if(raiz==null){
            raiz = new No(x);
        }else if(x<raiz.elemento){
            raiz.esq = inserir(x, raiz.esq);
        }else if(x>raiz.elemento){
            raiz.dir = inserir(x, raiz.dir);
        }
        return balancear(raiz);

    }

    public void inserir(int x){ raiz = inserir(x, raiz);}

    private No balancear (No raiz){
        if(raiz!=null){
            int fator = No.getNivel(raiz.dir) - No.getNivel(raiz.esq);
            if(Math.abs(fator)<=1){
                raiz.setNivel();
            }else if(fator ==2){
                int  fatorFilhoDir = No.getNivel(raiz.dir.dir) -No.getNivel(raiz.dir.esq);
                if(fatorFilhoDir==-1){
                    raiz.dir = rotacionarDir(raiz.dir);
                }   
                raiz = rotacionarEsq(raiz);
            }else if(fator == -2){
                int  fatorFilhoEsq = No.getNivel(raiz.esq.dir) -No.getNivel(raiz.esq.esq);
                if(fatorFilhoEsq == 1){
                    raiz.esq = rotacionarEsq(raiz.esq);
                }
                raiz = rotacionarDir(raiz);
            }else{
                
            }
        }
        return raiz;
    }

    private No rotacionarDir(No raiz){
        No raizEsq = raiz.esq;
        No raizEsqDir= raiz.esq.dir;
        raizEsq.dir= raiz;
        raiz.esq = raizEsqDir;
        raiz.setNivel();
        raizEsq.setNivel();
        return raizEsq;
    }

    private No rotacionarEsq(No raiz){
        No raizDir = raiz.dir;
        No raizDisEsq = raiz.dir.esq;
        raizDir.esq= raiz;
        raiz.dir = raizDisEsq;
        raiz.setNivel();
        raizDir.setNivel();
        return raizDir;
    }

    private No remover(int x, No raiz){
        if(raiz!=null){
            if(x < raiz.elemento){
                raiz.esq = remover(x, raiz.esq); 
            }else if( x> raiz.elemento){
                raiz.dir = remover(x, raiz.dir);
            }else if(raiz.dir == null){
                raiz = raiz.esq;
            }else if(raiz.esq == null){
                raiz = raiz.dir;
            }else{
                raiz.esq = maiorEsq(raiz, raiz.esq);
            }
        }
        return balancear(raiz);
    }

    private No maiorEsq(No i, No j ){
        if(j.dir==null){
            i.elemento = j.elemento;
            j = j.esq;
        }else{
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    public void remover(int x){ raiz = remover(x, raiz); }
    
    private boolean pesquisar(int x, No raiz){
        boolean resp= false;
        if(raiz!=null){
            if(x==raiz.elemento){
                resp = true;
            }else if(x<raiz.elemento){
                resp = pesquisar( x,  raiz.esq);
            }else{
                resp = pesquisar( x,  raiz.dir);
            }
        }
        return resp;
    }

    public boolean pesquisar(int x){return pesquisar(x, raiz);} 
    
    private void caminharPre(No raiz){
        if(raiz!=null){
            System.out.print(raiz.elemento+ " ");
            caminharPre(raiz.esq);
            caminharPre(raiz.dir);
        }
    }
    private void caminharPos(No raiz){
        if(raiz!=null){
            caminharPos(raiz.esq);
            caminharPos(raiz.dir);
            System.out.print(raiz.elemento+ " ");
        }
    }
    private void caminharCentral(No raiz){
        if(raiz!=null){
            caminharCentral(raiz.esq);
            System.out.print(raiz.elemento + " ");
            caminharCentral(raiz.dir);
        }
    }

    public void caminharPre(){System.out.print("Caminhar Pre: " ); caminharPre(raiz); System.out.println();}
    public void caminharPos(){System.out.print("Caminhar Pos: " ); caminharPos(raiz); System.out.println();}
    public void caminharCentral(){System.out.print("Caminhar Central: " ); caminharCentral(raiz); System.out.println();}
}

public class Main {
    public static void main(String[] args){
        AVL avl = new AVL();
			int array[] = {1,2,3,4,5,6,7,8,9,10};
			for(int item: array){
				System.out.println("Inserindo -> " + item);
				avl.inserir(item);
				avl.caminharPre();
			}
    }
}

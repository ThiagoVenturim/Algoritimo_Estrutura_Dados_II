import java.util.Scanner;

class Lista{
    private int [] array;
    private int n;

    public Lista(){
        array = new int[6];
        n = 0;
    }

    public Lista(int tam){
        array = new int[tam];
        n = 0;
    }

    public void inserirInicio(int x){
        if(n>= array.length){ System.exit(0);}
        for(int i = n; i>0; i--){
            array[i] = array[i-1];
        }
        array[0] = x;
        n++;
    }
    public void inserirFim(int x){
        if(n>= array.length){ System.exit(0);}

        array[n] = x;
        n++;
    }

    public void inserirPos(int x, int pos){
        if( n>= array.length || pos<0 || pos>n){ System.exit(0);}
        for(int i=n; i>pos; i--){
            array[i] = array[i-1];
        }
        array[pos] = x;
        n++;
    }

    public int removerInicio(){
        if(n==0){System.exit(0);}

        int resp = array[0];
        n--;
        for(int i=0; i<n ; i++){
            array[i] = array[i+1];
        }
    return resp;
    }

    public int  removerFim(){
        if(n==0){System.exit(0);}
        return array[--n];
    }

    public int  removerPos(int pos){
        if(n == 0 || pos < 0 || pos >= n){System.exit(0);}
        int resp = array[pos];
        n--;
        for(int i=pos; i<n; i++){
            array[i] = array[i+1];
        }
        return resp;
    }

    public void mostrar(){
        for(int i=0; i<n; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

public class ListaMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Lista lista = new Lista();
        int x, pos, tamanho, escolha;
        String linha;

        System.out.print("Digite o tamanho da lista: ");
        tamanho = scanner.nextInt();
        lista = new Lista(tamanho);

        while(true){
             escolha = scanner.nextInt();
            switch(escolha){
                case 1:
                    System.out.print("Digite o valor a ser inserido no inicio: ");
                    x = scanner.nextInt();
                    lista.inserirInicio(x);
                    break;
                case 2:
                    System.out.print("Digite o valor a ser inserido no fim: ");
                    x = scanner.nextInt();
                    lista.inserirFim(x);
                    break;
                case 3:
                    System.out.print("Digite o valor e a posicao a ser inserida: ");
                    x = scanner.nextInt();
                    pos = scanner.nextInt();
                    lista.inserirPos(x, pos);
                    break;
                case 4:
                    lista.removerInicio();
                    break;
                case 5:
                    lista.removerFim();
                    break;
                case 6:
                    System.out.print("Digite a posicao a ser removida: ");
                    pos = scanner.nextInt();
                    lista.removerPos(pos);
                    break;
                case 7:
                    lista.mostrar();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
         
    }
}
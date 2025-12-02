
import java.util.*;

class Time{
    private String nome;
    private int pontos;

    public Time(){this("",0);}
    public Time(String nome, int pontos){
        this.nome=nome;
        this.pontos=pontos;
    }

    public void setNome(String nome){this.nome=nome;}
    public void setPontos(int pontos){this.pontos=pontos;}
    public String getNome(){return nome;}
    public int getPontos(){return pontos;}

    public void addPontos(int pontos){this.pontos+=pontos;}

}
class Campeonato{
    private Time times[];
    private int tamanho;

    public Campeonato(int tamanho){
        times =new Time[tamanho];
        this.tamanho=tamanho;
    }

    public void getGanhador(){
        ordenar();
        String nome = times[0].getNome();
        if(nome.compareTo("Sport")==0){
            System.out.println("O "+ nome + " foi o campeao com " + times[0].getPontos()+ " pontos :D");
        }else{
            System.out.println("O Sport nao foi o campeao. O time campeao foi o " + nome + " com " + times[0].getPontos() + " :(");
        }
       }

    public void Inserir(int i, String nome, int pontos){
        times[i]= new Time(nome, pontos);       
    }

    private void swap(int i, int j){
        Time tmp = times[i];
        times[i] =  times[j];
        times[j] = tmp;
    }

    private void quickSort(Time times[], int esq, int dir){
        int i = esq;
        int j = dir;
        Time pivo = times[(i+j)/2];
        if(i<=j){
            
            while(times[i].getPontos() > pivo.getPontos()){i++;}
            while(times[j].getPontos() < pivo.getPontos()){j--;}
            if(i<=j){
                swap(i, j);
                i++;
                j--;
            }
        }
        if(esq<j){quickSort(times, esq, j);}
        if(dir>i){quickSort(times, i, dir);}
    }

    public void ordenar(){ quickSort(times, 0, tamanho-1);}

    public int pesquisar(String time){
        int aux=-1;
        for(int i=0; i<tamanho; i++){
            if(time.compareToIgnoreCase(times[i].getNome())==0){
                aux=i;
            }
        }
        return aux;
    }

    public void jogos(String t1, String placar, String t2){
        int x = (placar.charAt(0) - '0');
        int y = (placar.charAt(2) - '0');
        x*=3;
        y*=3;
        if(x==y){x++; y++;
        }else if (x > y){ x+=5;
        }else{  y+=5;}
        
        times[pesquisar(t1)].addPontos(x);
        times[pesquisar(t2)].addPontos(y);;
    }

    
}

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        while(n > 0){
            Campeonato cm= new Campeonato(n);
            for(int i=0; i<n; i++){
                String nome = scanner.next();
                int pt = scanner.nextInt();
                scanner.nextLine();
                cm.Inserir(i, nome, pt);
            }
            for(int i=0; i<n/2; i++){
                String t1 = scanner.next();
                String pt = scanner.next();
                String t2 = scanner.next();
                scanner.nextLine();
                cm.jogos(t1, pt, t2);
                
            }
           cm.getGanhador();
            n= scanner.nextInt();
        }
        scanner.close();
    }
}
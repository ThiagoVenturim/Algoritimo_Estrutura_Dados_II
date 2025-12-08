package Algoritmo.Hash.IndiretoLista;
class Celula {
    public Celula prox, ant;
    public int elemento;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.prox = this.ant = null;
    }
}

class Lista {
    Celula primeiro, ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(int elemento) {
        Celula nova = new Celula(elemento);
        nova.prox = primeiro;
        primeiro.ant = nova;
        primeiro = nova;
    }

    public void inserirFim(int elemento) {
        ultimo.prox = new Celula(elemento);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserirPos(int elemento, int pos) {
        int tamanho = getTamanho();

        if (pos < 0 || pos > tamanho) {
            System.out.println("Posição inválida!");
        } else if (pos == 0) {
            inserirInicio(elemento);
        } else if (pos == tamanho) {
            inserirFim(elemento);
        } else {
            Celula tmp = primeiro;
            for (int i = 0; i < pos; i++, tmp = tmp.prox);

            Celula nova = new Celula(elemento);

            nova.prox = tmp.prox;
            nova.ant = tmp;
            tmp.prox.ant = nova;
            tmp.prox = nova;
        }
    }

    public int getTamanho() {
        int i = 0;
        for (Celula tmp = primeiro; tmp != null; tmp = tmp.prox) {
            i++;
        }
        return i;
    }

    public boolean pesquisar(int elemento) {
        for (Celula tmp = primeiro; tmp != null; tmp = tmp.prox) {
            if (tmp.elemento == elemento) return true;
        }
        return false;
    }

    public int remover(int elemento) {
        for (Celula tmp = primeiro; tmp != null; tmp = tmp.prox) {

            if (tmp.elemento == elemento) {

                if (tmp == primeiro && tmp == ultimo) {
                    primeiro = ultimo = null;
                } else if (tmp == primeiro) {
                    primeiro = primeiro.prox;
                    primeiro.ant = null;
                } else if (tmp == ultimo) {
                    ultimo = ultimo.ant;
                    ultimo.prox = null;
                } else {
                    tmp.ant.prox = tmp.prox;
                    tmp.prox.ant = tmp.ant;
                }

                return elemento;
            }
        }
        return -1;
    }
}

class HashIndiretoLista {
    private Lista[] tabela;
    private int tamanho;
    final int NULO = -1;

    public HashIndiretoLista() {
        this(7);
    }

    public HashIndiretoLista(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }

    public int h(int elemento) {
        return elemento % tamanho;
    }

    public boolean pesquisar(int elemento) {
        int pos = h(elemento);
        return tabela[pos].pesquisar(elemento);
    }

    public void inserirInicio(int elemento) {
        int pos = h(elemento);
        tabela[pos].inserirInicio(elemento);
    }

    public int remover(int elemento) {
        int pos = h(elemento);
        return tabela[pos].remover(elemento);
    }
}

public class Main {
    public static void main(String[] args) {

        HashIndiretoLista hash = new HashIndiretoLista(5);

        hash.inserirInicio(10);
        hash.inserirInicio(15);
        hash.inserirInicio(20);

        System.out.println(hash.pesquisar(15)); // true
        System.out.println(hash.remover(15));  // 15
        System.out.println(hash.pesquisar(15)); // false
    }
}
